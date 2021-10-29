package com.project.autonomous.picture.service;

import com.project.autonomous.picture.dto.PictureInfoDto;
import com.project.autonomous.picture.entity.Picture;
import com.project.autonomous.picture.exception.FileStorageException;
import com.project.autonomous.picture.repository.PictureRepository;
import java.io.File;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class DBFileStorageService {

    private final PictureRepository pictureRepository;
    private final String IMAGE_PATH = "C:\\Users\\박범진\\Downloads";

    @Transactional
    public PictureInfoDto storeFile(MultipartFile file) {
        // Normalize file name
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        String filePath = IMAGE_PATH + "\\" + fileName;

        try {
            // Check if the file's name contains invalid characters
            if (fileName.contains("..")) {
                throw new FileStorageException(
                    "Sorry! Filename contains invalid path sequence " + fileName);
            }

            file.transferTo(new File(filePath));
            Picture picture = new Picture(fileName, file.getContentType(), filePath);

            return PictureInfoDto.from(pictureRepository.save(picture));
        } catch (IOException ex) {
            throw new FileStorageException("Could not store file " + fileName + ". Please try again!", ex);
        }
    }

    @Transactional
    public Picture makeImageUrl(Picture picture){
        String imageUrl = ServletUriComponentsBuilder.fromCurrentContextPath()
            .path("/file/downloadFile/")
            .path(picture.getId())
            .toUriString();

        picture.setImageUrl(imageUrl);
        return picture;
    }

//    public DBFile getFile(String fileId) {
//        return dbFileRepository.findById(fileId)
//            .orElseThrow(() -> new MyFileNotFoundException("File not found with id " + fileId));
//    }

}
