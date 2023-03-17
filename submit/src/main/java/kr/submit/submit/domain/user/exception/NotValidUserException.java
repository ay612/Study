package kr.submit.submit.domain.user.exception;

public class NotValidUserException extends SousoException {
    public static final SousoException EXCEPTION =
            new NotValidUserException();
    private NotValidUserException() {
        super(GlobalErrorCode.NOT_VALID_USER);
    }
}