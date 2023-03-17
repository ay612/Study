package kr.submit.submit.domain.user.exception;

public class UserAuthCodeNotFoundException extends SousoException {
    public static final SousoException EXCEPTION =
            new UserAuthCodeNotFoundException();
    private UserAuthCodeNotFoundException() {
        super(GlobalErrorCode.USER_AUTH_CODE_NOT_FOUND);
    }
}