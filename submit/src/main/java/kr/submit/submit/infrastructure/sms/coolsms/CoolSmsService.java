package kr.submit.submit.infrastructure.sms.coolsms;

import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.message.Message;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@RequiredArgsConstructor
@Component
public class CoolSmsService {
    private final CoolSmsProperties coolSmsProperties;

    @Override
    public void sendCode(String phoneNumber, String code) {
        Message message = new Message(coolSmsProperties.getKey(), coolSmsProperties.getSecret());

        HashMap<String, String> params = new HashMap<>();
        params.put("to", phoneNumber);
        params.put("from", coolSmsProperties.getPhoneNumber());
        params.put("type", "SMS");
        params.put("text", getBody(code));

        try {
            message.send(params);
        } catch (CoolsmsException e) {
            e.getStackTrace();
        }
    }

    private String getBody(String code) {
        return "[소소 본인확인] 인증번호" + " [ "+ code + " ] " + "를 " + "입력해 주세요. " + "(타인노출금지)";
    }
}
