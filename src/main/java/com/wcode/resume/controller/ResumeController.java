package com.wcode.resume.controller;

import com.wcode.resume.exception.ApiRequestException;
import com.wcode.resume.model.data.Resume;
import com.wcode.resume.model.response.ApiResponse;
import com.wcode.resume.serviceImpl.ResumeServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1")
@Api(value = "Resume Services")
public class ResumeController {

    private ResumeServiceImpl resumeService;

    public ResumeController(ResumeServiceImpl resumeService) {
        this.resumeService = resumeService;
    }

    @GetMapping("/resume/{id}")
    @ApiOperation(value = "resume: get resume")
    public ResponseEntity getResumeById(@PathVariable("id") long id) {
        return resumeService.getResumeById(id)
                .map(resume -> ResponseEntity.ok(resume))
                .orElseThrow(() -> new ApiRequestException("Ocurrio un error encontrando el resume"));

    }

    @PostMapping("/resume/{id_user}")
    @ApiOperation(value = "resume: Creates new Resume")
    public ResponseEntity registerResume(@PathVariable("id_user") long id_user, @Valid @RequestBody Resume resume) {
        return resumeService.insertResume(resume, id_user)
                .map(resumes -> ResponseEntity.ok(new ApiResponse(true, resumes.toString())))
                .orElseThrow(() -> new ApiRequestException("Ocurrio un error creando el resume"));

    }

    @PutMapping("/resume/{id}")
    @ApiOperation(value = "resume: update new Resume")
    public ResponseEntity updateResume(@PathVariable("id") long id, @Valid @RequestBody Resume resume) {
        return resumeService.updateResume(resume, id)
                .map(user -> ResponseEntity.ok(new ApiResponse(true, user.toString())))
                .orElseThrow(() -> new ApiRequestException("Ocurrio un error actualizando el resume"));

    }

    @DeleteMapping("/resume/{id}")
    @ApiOperation(value = "resume: update new Resume")
    public ResponseEntity<HttpStatus> updateResume(@PathVariable("id") long id) {
        try {
            resumeService.delete(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

}
