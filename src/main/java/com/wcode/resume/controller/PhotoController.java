package com.wcode.resume.controller;

import com.wcode.resume.exception.ApiRequestException;
import com.wcode.resume.model.data.Photo;
import com.wcode.resume.model.response.ApiResponse;
import com.wcode.resume.serviceImpl.PhotoServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;
import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/resume")
@Api(value = "Photo Services")
public class PhotoController {

    PhotoServiceImpl photoService;

    public PhotoController(PhotoServiceImpl photoService) {
        this.photoService = photoService;
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/photo/{id}")
    @ApiOperation(value = "Photo: get photo")
    public ResponseEntity getPhotoById(@PathVariable("id") long id) {
        Optional<Photo> photo = photoService.getPhotoById(id);
        if(photo.isPresent()){
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION,
                            "attachment; filename=\"" + photo.get().getName() + "\"")
                    .body(photo.get());
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/photo/getByUser/{id_user}")
    @ApiOperation(value = "Photo: get photo")
    public ResponseEntity getPhotoByIdUser(@PathVariable("id_user") long id_user) {
        Optional<Photo> photo = photoService.getPhotoByIdUser(id_user);
        if(photo.isPresent()){
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION,
                            "attachment; filename=\"" + photo.get().getName() + "\"")
                    .body(photo.get());
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PreAuthorize("hasRole('USER')")
    @PostMapping("/photo/{id_user}")
    @ApiOperation(value = "Photo: Creates new photo")
    public ResponseEntity registerPhoto(@PathVariable("id_user") long id_user,
                                        @RequestParam("photo") MultipartFile file) throws IOException{
        return photoService.insertPhoto(id_user, file)
                .map(photo -> ResponseEntity.ok(new ApiResponse(true, photo.toString())))
                .orElseThrow(() -> new ApiRequestException("Ocurrio un error creando Photo"));

    }

    @PreAuthorize("hasRole('USER')")
    @PutMapping("/photo/{id}")
    @ApiOperation(value = "Photo: update new photo")
    public ResponseEntity updatePhoto(@PathVariable("id") long id,
                                      @RequestParam("photo") MultipartFile file) throws IOException {
        return photoService.updatePhoto(id, file)
                .map(photo -> ResponseEntity.ok(new ApiResponse(true, photo.toString())))
                .orElseThrow(() -> new ApiRequestException("Ocurrio un error actualizando Photo"));

    }

}
