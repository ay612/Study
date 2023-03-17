package kr.submit.submit.domain.image.service;

import kr.submit.submit.domain.image.response.ImageUrlResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class UploadImageService {
    private final S3Service s3Service;

    public ImageUrlResponse execute(List<MultipartFile> images) {
        List<String> imageUrl;
        imageUrl = images.stream()
                .map(s3Service::uploadImage)
                .collect(Collectors.toList());

        return new ImageUrlResponse(imageUrl);
    }
}
