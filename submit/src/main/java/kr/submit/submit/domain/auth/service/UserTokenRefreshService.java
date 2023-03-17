package kr.submit.submit.domain.auth.service;

import kr.submit.submit.domain.auth.domain.RefreshToken;
import kr.submit.submit.domain.auth.domain.repository.RefreshTokenRepository;
import kr.submit.submit.domain.auth.exception.RefreshTokenNotFoundException;
import kr.submit.submit.domain.auth.presentation.dto.response.UserTokenRefreshResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class UserTokenRefreshService {
    private final RefreshTokenRepository refreshTokenRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final JwtProperties jwtProperties;

    @Transactional
    public UserTokenRefreshResponse execute(String refreshToken){
        RefreshToken redisRefreshToken = refreshTokenRepository.findByToken(jwtTokenProvider.parseToken(refreshToken))
                .orElseThrow(() -> RefreshTokenNotFoundException.EXCEPTION);

        String acessToken = jwtTokenProvider.generateAcessToken(redisRefreshToken.getEmail());
        redisRefreshToken.updateToken(newRefreshToken, jwtProperties.getRefreshExp());

        String accessToken = jwtTokenProvider.generateAccessToken(redisRefreshToken.getEmail());
        return UserTokenRefreshResponse.builder()
                .accessToken(accessToken)
                .refreshToken(newRefreshToken)
                .expiredAt(jwtTokenProvider.getExpiredTime())
                .build();
    }
}
