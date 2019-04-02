package az.bank.mspayment.controller;

import az.bank.mspayment.model.dto.PaymentDto;
import az.bank.mspayment.service.PaymentService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@AllArgsConstructor
@RestController
@RequestMapping("/accounts/payments")
public class PaymentController {

    private final PaymentService paymentService;

    @PostMapping("/new/{toppedUpAccountId}")
    public void newPayment(@RequestBody PaymentDto paymentDto,
                           @PathVariable String toppedUpAccountId) {
        paymentService.newPayment(paymentDto, toppedUpAccountId);
    }
}
