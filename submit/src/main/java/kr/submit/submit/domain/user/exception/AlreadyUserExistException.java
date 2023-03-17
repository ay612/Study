package kr.submit.submit.domain.user.exception;

public class AlreadyUserExistException extends SousoException {
    public static final SousoException EXCEPTION =
            new AlreadyUserExistException();
    private AlreadyUserExistException() {
        super(GlobalErrorCode.ALREADY_NICKNAME_EXIST);
    }
}