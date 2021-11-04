package com.project.autonomous.picture.service;

import com.project.autonomous.common.exception.CustomException;
import com.project.autonomous.common.exception.ErrorCode;
import com.project.autonomous.picture.entity.Picture;
import com.project.autonomous.picture.exception.FileStorageException;
import com.project.autonomous.picture.repository.PictureRepository;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
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

    // application-dev.yml (로컬 환경에서 테스트시 다른 디렉토리에서 하고 싶다면 여기서 변경)
    @Value("${images.absolute.location}")
    private String IMAGE_PATH;

    @Transactional
    public Picture storeFile(MultipartFile file) {
        // 디렉토리 경로 확인 후 없으면 디렉토리 생성 (경로 설정을 확실히 한다면 불필요)
        Path directory = Paths.get(IMAGE_PATH);
        try {
            Files.createDirectories(directory);
        } catch (IOException e){
            e.printStackTrace();
        }

        // DB에 저장할 내용
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        String fileType = file.getContentType();
        String fileExtension;

        // 사진 파일만 받음
        if(fileType.contains("image/jpeg") || fileType.contains("image/jpg")){
            fileExtension = ".JPG";
        }else if(fileType.contains("image/png")){
            fileExtension = ".PNG";
        } else {
            throw new CustomException(ErrorCode.NOT_ALLOW_TYPE);
        }

        // 파일 저장 url
        String filePath = IMAGE_PATH + uuid + fileExtension;

        try {
            // Check if the file's name contains invalid characters
            if (fileName.contains("..")) {
                throw new FileStorageException(
                    "Sorry! Filename contains invalid path sequence " + fileName);
            }
            // 파일 저장
            file.transferTo(new File(filePath));
            return pictureRepository.save(Picture.of(uuid, fileName, fileType, filePath));
//            return PictureInfoDto.from(pictureRepository.save(picture));
        } catch (IOException ex) {
            throw new FileStorageException(
                "Could not store file " + fileName + ". Please try again!", ex);
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
