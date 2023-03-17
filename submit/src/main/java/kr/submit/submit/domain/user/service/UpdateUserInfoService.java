package kr.submit.submit.domain.user.service;

import kr.submit.submit.domain.user.fcade.UserFacade;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@RequiredArgsConstructor
@Service
public class UpdateUserInfoService {
    private final UserFacade userFacade;
    private final S3Service s3Service;

    @Transactional
    public void execute(MultipartFile image, UpdateUserInfoRequest request) {
        User user = userFacade.getCurrentUser();

        if (image != null) {
            String profileImage = s3Service.uploadImage(image);
            user.updateProfileImageUrl(profileImage);
        }

        if (request.getIsDefaultProfile()) {
            user.updateProfileImageUrl(DefaultImage.USER_PROFILE_IMAGE);
        }

        user.updateUser(request)
    }
}
