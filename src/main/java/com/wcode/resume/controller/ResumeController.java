package com.wcode.resume.controller;

import com.wcode.resume.exception.ApiRequestException;
import com.wcode.resume.model.data.Resume;
import com.wcode.resume.model.response.ApiResponse;
import com.wcode.resume.serviceImpl.AuthServiceImpl;
import com.wcode.resume.serviceImpl.ResumeServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static com.wcode.resume.constant.ConferenceConstant.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1")
@Api(value = "Resume Services")
public class ResumeController {

    private ResumeServiceImpl resumeService;

    public ResumeController(ResumeServiceImpl resumeService) {
        this.resumeService = resumeService;
    }

    private static final Logger logger = LoggerFactory.getLogger(AuthServiceImpl.class);

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/resume/{id}")
    @ApiOperation(value = "resume: get resume")
    public ResponseEntity getResumeById(@PathVariable("id") long id) {
        logger.info(LOG_FIND_RESUME);
        return resumeService.getResumeById(id)
                .map(resume -> ResponseEntity.ok(resume))
                .orElseThrow(() -> new ApiRequestException(ERROR_FIND_RESUME));

    }

    @PreAuthorize("hasRole('USER')")
    @PostMapping("/resume/{id_user}")
    @ApiOperation(value = "resume: Creates new Resume")
    public ResponseEntity registerResume(@PathVariable("id_user") long id_user, @Valid @RequestBody Resume resume) {
        logger.info(LOG_CREATE_RESUME);
        return resumeService.insertResume(resume, id_user)
                .map(resumes -> ResponseEntity.ok(new ApiResponse(true, resumes.toString())))
                .orElseThrow(() -> new ApiRequestException(ERROR_CREATE_RESUME));

    }

    @PreAuthorize("hasRole('USER')")
    @PutMapping("/resume/{id}")
    @ApiOperation(value = "resume: update new Resume")
    public ResponseEntity updateResume(@PathVariable("id") long id, @Valid @RequestBody Resume resume) {
        logger.info(LOG_UPDATE_RESUME);
        return resumeService.updateResume(resume, id)
                .map(resumes -> ResponseEntity.ok(new ApiResponse(true, resumes.toString())))
                .orElseThrow(() -> new ApiRequestException(ERROR_UPDATE_RESUME));

    }

    @GetMapping("/resume/consolidate/{userName}")
    @ApiOperation(value = "resume: get consolidate resume by user name")
    public ResponseEntity consolidate(@PathVariable("userName") String userName) {
        logger.info(LOG_CONSOLIDATE_RESUME);
        return resumeService.getConsolidate(userName)
                .map(resume -> ResponseEntity.ok(resume))
                .orElseThrow(() -> new ApiRequestException(ERROR_CONSOLIDATE_RESUME));

    }


}
