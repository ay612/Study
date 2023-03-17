package kr.submit.submit.domain.user.service;

import kr.submit.submit.domain.auth.domain.RefreshToken;
import kr.submit.submit.domain.auth.domain.repository.RefreshTokenRepository;
import kr.submit.submit.domain.auth.exception.RefreshTokenNotFoundException;
import kr.submit.submit.domain.user.domain.User;
import kr.submit.submit.domain.user.fcade.UserFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class UserLogoutService {
    private final UserFacade userFacade;
    private final RefreshTokenRepository refreshTokenRepository;

    @Transactional
    public void execute() {
        User user = userFacade.getCurrentUser();

        RefreshToken refreshToken = refreshTokenRepository.findById(user.getEmail())
                .orElseThrow(() -> RefreshTokenNotFoundException.EXCEPTION);

        refreshTokenRepository.delete(refreshToken);
    }
}
