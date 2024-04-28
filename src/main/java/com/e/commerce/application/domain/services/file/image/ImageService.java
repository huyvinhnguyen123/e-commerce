package com.e.commerce.application.domain.services.file.image;

import com.e.commerce.application.domain.entities.Image;
import com.e.commerce.application.domain.entities.Type;
import com.e.commerce.application.domain.repositories.ImageRepository;
import com.e.commerce.application.domain.services.custom.GoogleDriveService;
import com.e.commerce.application.domain.services.file.TypeService;
import com.e.commerce.application.domain.utils.constant.Logger;
import com.google.api.services.drive.model.File;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class ImageService {
    private final ImageRepository imageRepository;
    private final TypeService typeService;
    private final GoogleDriveService googleDriveService;

    public Image findImageById(String imageId) {
        Image existImage = imageRepository.findById(imageId).orElseThrow(
                () -> new NullPointerException("Not found this image: " + imageId)
        );

        log.info(Logger.findObjectSuccess("image"));
        return existImage;
    }

    private Boolean checkImageByName(String imageName) {
        Optional<Image> image = imageRepository.findByImageName(imageName);
        return image.isPresent();
    }

    public Image createImage(MultipartFile file, String path, String type) throws GeneralSecurityException, IOException {
        String originalFilename = file.getOriginalFilename();
        File fileDrive = googleDriveService.uploadToDrive(file, path);

        Image image = new Image();
        image.setImagePathId(fileDrive.getId());
        image.setImagePath(fileDrive.getWebContentLink());

        boolean imageNameExists = false;
        for(Image imageRecord: imageRepository.findAll()) {
            if (imageRecord.getImageName().equals(originalFilename)) {
                imageNameExists = true;
                break;
            }
        }

        if (imageNameExists) {
            String baseName = FilenameUtils.getBaseName(originalFilename);
            String extension = FilenameUtils.getExtension(originalFilename);

            int counter = 1;
            String newFileName = originalFilename;
            while (Boolean.TRUE.equals(checkImageByName(newFileName))) {
                newFileName = String.format("%s(%d).%s", baseName, counter, extension);
                counter++;
            }

            image.setImageName(newFileName);
        } else {
            image.setImageName(originalFilename);
        }

        log.info(image.getImageId());

        Type existType = typeService.findTypeByName(type);
        image.setType(existType);

        imageRepository.save(image);
        log.info(image.getImageId());

        log.info("create image success!");
        return image;
    }
}
