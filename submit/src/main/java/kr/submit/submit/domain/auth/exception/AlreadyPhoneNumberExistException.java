package kr.submit.submit.domain.auth.exception;


public class AlreadyPhoneNumberExistException extends SousoException{

    public static final SousoException EXCEPTION =
            new AlreadyPhoneNumberExistException();

    private AlreadyPhoneNumberExistException() {
        super(GlobalErrorCode.ALREADY_PHONE_NUMBER_EXIST);
    }

}
