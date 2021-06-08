package com.wcode.resume.service;

import com.wcode.resume.model.data.Photo;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

public interface PhotoService {
    Optional<Photo> getPhotoById(Long id);
    Optional<Photo> getPhotoByIdUser(Long id_user);
    Optional<Photo> insertPhoto(Long id_user, MultipartFile file) throws IOException;
    Optional<Photo> updatePhoto(Long id, MultipartFile file) throws IOException;
}
