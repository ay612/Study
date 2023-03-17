package kr.submit.submit.domain.user.service;

import kr.submit.submit.domain.user.domain.repository.UserRepository;
import kr.submit.submit.domain.user.fcade.UserFacade;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.User;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class UserWithdrawalService {
    private final UserFacade userFacade;
    private final UserRepository userRepository;

    @Transactional
    public void execute() {
        User user = userFacade.getCurrentUser();

        userRepository.delete(user);
    }
}
