package kr.submit.submit.domain.user.exception;

public class InvalidCodeException extends SousoException{
    public static final SousoException EXCEPTION =
            new InvalidCodeException();

    private InvalidCodeException() {
        super(GlobalErrorcode.INVALID_CODE);
    }
}
