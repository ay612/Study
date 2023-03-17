package kr.submit.submit.domain.user.service;

import kr.submit.submit.domain.auth.presentation.dto.response.UserTokenResponse;
import kr.submit.submit.domain.user.domain.repository.UserRepository;
import kr.submit.submit.domain.user.fcade.UserFacade;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.ZonedDateTime;

@RequiredArgsConstructor
@Service
public class UserSignUpService {
    private final UserFacade userFacade;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final JwtProperties jwtProperties;

    @Transactional
    public UserTokenResponse execute(UserSignUpRequest request) {
        userFacade.checkUserExists(request.getEmail());

        User user = userRepository.save(User.builder()
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .name(request.getName())
                .phoneNumber(request.getPhoneNumber())
                .birth(request.getBirth())
                .location(request.getLocation())
                .nickname(request.getNickname())
                .role(UserRole.USER)
                .profileImageUrl(DefaultImage.USER_PROFILE_IMAGE)
                .build());

        String accessToken = jwtTokenProvider.generateAccessToken(user.getEmail());
        String refreshToken = jwtTokenProvider.generateRefreshToken(user.getEmail());

        return UserTokenResponse.builder()
                .accessToken(accessToken)
                .expiredAt(ZonedDateTime.now().plusSeconds(jwtProperties.getAccessExp()))
                .refreshToken(refreshToken)
                .build();
    }
}
