package az.bank.mspayment.service;

import az.bank.mspayment.exceptions.WrongPaymentChoiceException;
import az.bank.mspayment.mapper.PaymentMapper;
import az.bank.mspayment.model.dto.PaymentDto;
import az.bank.mspayment.model.entity.AccountEntity;
import az.bank.mspayment.model.entity.PaymentEntity;
import az.bank.mspayment.repository.AccountRepository;
import az.bank.mspayment.repository.PaymentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneId;

@Service
@AllArgsConstructor
public class PaymentService {
    private final PaymentRepository paymentRepository;
    private final AccountRepository accountRepository;

    public void newPayment(PaymentDto paymentDto, String toppedUpAccountId) {
        PaymentEntity chargedPayment = PaymentMapper.INSTANCE.dtoToEntity(paymentDto);
        PaymentEntity toppedUpPayment = PaymentMapper.INSTANCE.dtoToEntity(paymentDto);
        AccountEntity chargedAccount = accountRepository.getByAccountId(chargedPayment.getAccountId());
        AccountEntity toppedUpAccount = accountRepository.getByAccountId(toppedUpAccountId);
        if (chargedAccount.getAccountId().equals( toppedUpAccount.getAccountId())) {
            throw new WrongPaymentChoiceException("You can not send payment to the same account");
        }
        else if (chargedAccount.getAmount().compareTo(chargedPayment.getAmount()) < 0) {
            throw new WrongPaymentChoiceException("You do not have enough amount on your account balance for this payment");
        }
        else if (!chargedAccount.getCurrency().equals(toppedUpAccount.getCurrency())) {
            throw new WrongPaymentChoiceException("You can make payments only between accounts with same currencies");
        } else {
            LocalDateTime newPaymentDate= LocalDateTime.now();
            chargedPayment.setCreatedAt(newPaymentDate);
            chargedPayment.setCategory(paymentDto.getCategory());
            chargedPayment.setDescription(paymentDto.getDescription());
            paymentRepository.save(chargedPayment);


            chargedAccount.setAmount(chargedAccount.getAmount().subtract(chargedPayment.getAmount()));
            toppedUpPayment.setCreatedAt(newPaymentDate);
            toppedUpPayment.setCategory(paymentDto.getCategory());
            toppedUpPayment.setDescription("from Account No " + toppedUpPayment.getAccountId());
            toppedUpPayment.setAccountId(toppedUpAccountId);
            toppedUpPayment.setIncreased(!toppedUpPayment.getIncreased());
            paymentRepository.save(toppedUpPayment);
            // TODO change customer id for another account payment
            //AccountEntity toppedUpAccount = accountRepository.getByAccountId(toppedUpAccountId);
            toppedUpAccount.setAmount(toppedUpAccount.getAmount().add(toppedUpPayment.getAmount()));
            accountRepository.save(toppedUpAccount);
        }
    }
}
