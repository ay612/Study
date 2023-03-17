package kr.submit.submit.domain.auth.service;

import kr.submit.submit.domain.auth.presentation.dto.request.CheckAuthCodeRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class CheckAuthCodeExistsService {

    private final UserAuthCodeRepository userAuthCodeRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional(readOnly = true)
    public void execute(CheckAuthCodeRepository request) {
        UserAuthCode authCode = userAuthCodeRepository.findById(request.getPhoneNumber())
                .orElseThrow(() -> userAuthCodeFoundException.EXCEPTION);

        if(!passwordEncoder.matches(request.getAuthCode(), authCode.getCode())) {
            throw InvalidCodeException.EXCEPTION;
        }
    }
}
