package kr.submit.submit.domain.auth.exception;

public class RefreshTokenNotFoundException extends SousoException{ //참조

    public static final SousoException EXCEPTION =
            new RefreshTokenNotFoundException();

    private RefreshTokenNotFoundException() {
        super(GlobalErrorCode.REFRESH_TOKEN_NOT_FOUND); //SUPER
    }

}
