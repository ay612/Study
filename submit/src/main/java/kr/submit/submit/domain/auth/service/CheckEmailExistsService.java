package kr.submit.submit.domain.auth.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CheckEmailExistsService {
    private final UserFcade userFcade;

    public void execute(String email) {
        userFcade.checkEmailExists(email);
    }
}
