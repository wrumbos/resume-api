package com.wcode.resume.serviceImpl;

import com.wcode.resume.model.data.Photo;
import com.wcode.resume.model.data.Resume;
import com.wcode.resume.repository.PhotoRepository;
import com.wcode.resume.repository.ResumeRepository;
import com.wcode.resume.service.PhotoService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@Service
public class PhotoServiceImpl implements PhotoService {

    private PhotoRepository photoRepository;
    private ResumeRepository resumeRepository;

    public PhotoServiceImpl(PhotoRepository photoRepository, ResumeRepository resumeRepository) {
        this.photoRepository = photoRepository;
        this.resumeRepository = resumeRepository;
    }

    @Override
    public Optional<Photo> getPhotoById(Long id) {
        return photoRepository.findById(id);
    }

    @Override
    public Optional<Photo> getPhotoByIdUser(Long id_user) {
        Optional<Resume> resume = resumeRepository.findByUser_Id(id_user);
        if(resume.isPresent()){
            return photoRepository.findByResume(resume.get());
        }
        return Optional.empty();
    }

    @Override
    public Optional<Photo> insertPhoto(Long id_user, MultipartFile file) throws IOException {
        Optional<Resume> resume = resumeRepository.findByUser_Id(id_user);
        if(resume.isPresent()){
            String fileName = StringUtils.cleanPath(file.getOriginalFilename());
            Photo photo = new Photo(fileName, file.getContentType(), file.getBytes());
            photo.setResume(resume.get());
            return Optional.ofNullable(photoRepository.save(photo));
        }
        return Optional.empty();
    }

    @Override
    public Optional<Photo> updatePhoto(Long id, MultipartFile file) throws IOException{

        Optional<Photo> photo = photoRepository.findById(id);

        if(photo.isPresent()){
            String fileName = StringUtils.cleanPath(file.getOriginalFilename());
            photo.get().setName(fileName);
            photo.get().setType(file.getContentType());
            photo.get().setData(file.getBytes());
            return Optional.ofNullable(photoRepository.save(photo.get()));
        }


        return Optional.empty();
    }
}
