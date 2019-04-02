package az.bank.mspayment.exceptions;

public class WrongPaymentChoiceException extends RuntimeException  {

    public WrongPaymentChoiceException() {
    }

    public WrongPaymentChoiceException(String message) {
        super(message);
    }
}
