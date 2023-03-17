package kr.submit.submit.infrastructure.image.s3;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@RequiredArgsConstructor
@Component
public class S3Service {
    private final S3Properties s3Properties;
    private final AmazonS3Client amazonS3Client;

    @Override
    public String uploadImage(MultipartFile image) {
        String fileName = s3Properties.getBucket() + "/" + UUID.randomUUID() + image.getOriginalFilename();

        try {
            amazonS3Client.putObject(new PutObjectRequest(s3Properties.getBucket(), fileName, image.getInputStream(), null)
                    .withCannedAcl(CannedAccessControlList.PublicRead));
        } catch (Exception e) {
            throw SaveImageFalseException.EXCEPTION;
        }

        return getFileUrl(fileName);
    }

    public String getFileUrl(String fileName) {
        return amazonS3Client.getUrl(s3Properties.getBucket(), fileName).toString();
    }
}
