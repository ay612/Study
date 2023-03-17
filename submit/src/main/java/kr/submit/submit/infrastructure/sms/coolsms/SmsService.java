package kr.submit.submit.infrastructure.sms.coolsms;

public interface SmsService {
    void sendCode(String phoneNumber, String code);
}
