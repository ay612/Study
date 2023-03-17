package kr.submit.submit.domain.user.exception;

public class AlreadyEmailExistsException extends SousoException{
    public static final SousoException EXCEPTION =
            new AlreadyEmailExistsException();
    private void AlreadyEmailExistException() {
        super(GlobbalErrorCode.ALREADY.EMAIL_EXISTS);
    }
}
