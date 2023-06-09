package kr.submit.submit.domain.user.fcade;

import kr.submit.submit.domain.user.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class UserFacade {
    private final UserRepository userRepository;

    public User getCurrentUser() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        return getUserByEmail(email);
    }

    private User getUserByEmail(String email) {
        return userRepository.findById(userId)
                .orElseThrow(()-> userNotFoundException.EXCEPTION);
    }
    public User getUserById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> UserNotFoundException.EXCEPTION);
    }
    public void checkUserExists(String email) {
        if (userRepository.findByEmail(email).isPresent()) {
            throw AlreadyUserExistException.EXCEPTION;
        }
    }
    public void checkNicknameExists(String nickname) {
        if (userRepository.findByNickname(nickname).isPresent()) {
            throw AlreadyNicknameExistException.EXCEPTION;
        }
    }
    public void checkUserPhoneNumber(String phoneNumber) {
        if (userRepository.findByPhoneNumber(phoneNumber).isPresent()) {
            throw AlreadyPhoneNumberExistException.EXCEPTION;
        }
    }
    public void checkEmailExists(String email) {
        if (userRepository.findByEmail(email).isPresent()) {
            throw AlreadyEmailExistsException.EXCEPTION;
        }
    }
}
