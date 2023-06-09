package kr.submit.submit.domain.user.service;

import kr.submit.submit.domain.user.domain.UserAuthCode;
import kr.submit.submit.domain.user.domain.repository.UserAuthCodeRepository;
import kr.submit.submit.domain.user.fcade.UserFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
@EnableAsync
public class UserAuthCodeService {
    private final CoolSmsService coolSmsService;
    private final UserFacade userFacade;
    private final UserAuthCodeRepository userAuthCodeRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional(readOnly = true)
    public void execute(UserAuthCodeRequest request) {
        String phoneNumber = request.getPhoneNumber();
        String code = RandomCodeUtil.generateRandomNumber(6);

        userFacade.checkUserPhoneNumber(phoneNumber);
        if (userAuthCodeRepository.findById(phoneNumber).isPresent()) {
            throw AlreadyAuthCodeExistException.EXCEPTION;
        }
        sendCode(phoneNumber, code);
    }

    @Async
    @Transactional
    public void sendCode(String phoneNumber, String code) {
        coolSmsService.sendCode(phoneNumber, code);

        userAuthCodeRepository.save(UserAuthCode.builder()
                .phoneNumber(phoneNumber)
                .code(passwordEncoder.encode(code))
                .build());
    }
}
