package kr.submit.submit.domain.user.exception;

public class AlreadyNicknameExistException extends SousoException {
    public static final SousoException EXCEPTION =
            new AlreadyNicknameExistException();
    private AlreadyNicknameExistException() {
        super(GlobalErrorCode.ALREADY_NICKNAME_EXIST);
    }
}