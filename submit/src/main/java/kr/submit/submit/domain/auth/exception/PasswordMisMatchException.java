package kr.submit.submit.domain.auth.exception;

public class PasswordMisMatchException {
    public static final SousoExxeception EXXECEPTION =
            new PasswordMisMatchException();

    private PasswordMisMatchException() {
        super(GlobalErrorCode.PASSWORD_MISMATCH);
    }
}
