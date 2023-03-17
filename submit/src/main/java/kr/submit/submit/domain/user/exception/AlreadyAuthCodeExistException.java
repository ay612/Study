package kr.submit.submit.domain.user.exception;

public class AlreadyAuthCodeExistException extends SousoException{
    public static final SousoException EXCEPTION =
            new AlreadyAuthCodeExistException();
    private AlreadyAuthCodeExistException(){
        super(GlobalErroeCode.ALREADY_AUTH_CODE_EXIST);
    }
}
