package com.wcode.resume.controller;

import com.wcode.resume.exception.ApiRequestException;
import com.wcode.resume.model.data.Education;
import com.wcode.resume.model.response.ApiResponse;
import com.wcode.resume.serviceImpl.EducationServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/resume")
@Api(value = "Education Services")
public class EducationController {

    private EducationServiceImpl educationService;

    public EducationController(EducationServiceImpl educationService) {
        this.educationService = educationService;
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/education/{id}")
    @ApiOperation(value = "Education: get education")
    public ResponseEntity getEducationById(@PathVariable("id") long id) {
        return educationService.getEducationById(id)
                .map(resume -> ResponseEntity.ok(resume))
                .orElseThrow(() -> new ApiRequestException("Ocurrio un error encontrando estudio"));

    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/education/getAll/{id_user}")
    @ApiOperation(value = "Education: get education")
    public ResponseEntity getAllEducationByIdUser(@PathVariable("id_user") long id_user) {
        return educationService.getAllEducationByIdUser(id_user)
                .map(resume -> ResponseEntity.ok(resume))
                .orElseThrow(() -> new ApiRequestException("Ocurrio un error encontrando todos estudios"));

    }

    @PreAuthorize("hasRole('USER')")
    @PostMapping("/education/{id_user}")
    @ApiOperation(value = "Education: Creates new education")
    public ResponseEntity registerEducation(@PathVariable("id_user") long id_user, @Valid @RequestBody Education education) {
        return educationService.insertEducation(id_user, education)
                .map(resumes -> ResponseEntity.ok(new ApiResponse(true, resumes.toString())))
                .orElseThrow(() -> new ApiRequestException("Ocurrio un error creando estudio"));

    }

    @PreAuthorize("hasRole('USER')")
    @PutMapping("/education/{id}")
    @ApiOperation(value = "Education: update new Resume")
    public ResponseEntity updateEducation(@PathVariable("id") long id, @Valid @RequestBody Education education) {
        return educationService.updateEducation(id, education)
                .map(user -> ResponseEntity.ok(new ApiResponse(true, user.toString())))
                .orElseThrow(() -> new ApiRequestException("Ocurrio un error actualizando estudio"));

    }

    @PreAuthorize("hasRole('USER')")
    @DeleteMapping("/education/{id}")
    @ApiOperation(value = "Education: update new Resume")
    public ResponseEntity<HttpStatus> updateEducation(@PathVariable("id") long id) {
        try {
            educationService.deleteEducation(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

}
