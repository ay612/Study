package kr.submit.submit.domain.user.presentation;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/users")
@RestController
public class UserController {
    private final QueryMyProfileService queryMyProfileService;
    private final QueryMyFeedPagesService queryMyFeedPagesService;
    private final

}
