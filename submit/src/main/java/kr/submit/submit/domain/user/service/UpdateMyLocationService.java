package kr.submit.submit.domain.user.service;

import kr.submit.submit.domain.user.fcade.UserFacade;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class UpdateMyLocationService {
    public final UserFacade userFacade;

    @Transactional
    public void execute(UpdateMyLocationRequest request) {
        User user = userFacade.getCurrentUser();

        user.updateMyLocation(request);
    }
}
