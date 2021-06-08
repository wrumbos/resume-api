package com.wcode.resume.controller;

import com.wcode.resume.exception.ApiRequestException;
import com.wcode.resume.model.data.Employment;
import com.wcode.resume.model.response.ApiResponse;
import com.wcode.resume.serviceImpl.EmploymentServiceImpl;
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
@Api(value = "Employment Services")
public class EmploymentController {

    private EmploymentServiceImpl employmentService;

    public EmploymentController(EmploymentServiceImpl employmentService) {
        this.employmentService = employmentService;
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/employment/{id}")
    @ApiOperation(value = "Employment: get employment")
    public ResponseEntity getEmploymentById(@PathVariable("id") long id) {
        return employmentService.getEmploymentById(id)
                .map(employments -> ResponseEntity.ok(employments))
                .orElseThrow(() -> new ApiRequestException("Ocurrio un error encontrando trabajo"));

    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/employment/getAll/{id_user}")
    @ApiOperation(value = "Employment: get employment")
    public ResponseEntity getAllEmploymentByIdUser(@PathVariable("id_user") long id_user) {
        return employmentService.getAllEmploymentByIdUser(id_user)
                .map(employments -> ResponseEntity.ok(employments))
                .orElseThrow(() -> new ApiRequestException("Ocurrio un error encontrando todos trabajo"));

    }

    @PreAuthorize("hasRole('USER')")
    @PostMapping("/employment/{id_user}")
    @ApiOperation(value = "Employment: Creates new employment")
    public ResponseEntity registerEmployment(@PathVariable("id_user") long id_user, @Valid @RequestBody Employment employment) {
        return employmentService.insertEmployment(id_user, employment)
                .map(employments -> ResponseEntity.ok(new ApiResponse(true, employments.toString())))
                .orElseThrow(() -> new ApiRequestException("Ocurrio un error creando trabajo"));

    }

    @PreAuthorize("hasRole('USER')")
    @PutMapping("/employment/{id}")
    @ApiOperation(value = "Employment: update new employment")
    public ResponseEntity updateEmployment(@PathVariable("id") long id, @Valid @RequestBody Employment employment) {
        return employmentService.updateEmployment(id, employment)
                .map(employments -> ResponseEntity.ok(new ApiResponse(true, employments.toString())))
                .orElseThrow(() -> new ApiRequestException("Ocurrio un error actualizando trabajo"));

    }

    @PreAuthorize("hasRole('USER')")
    @DeleteMapping("/employment/{id}")
    @ApiOperation(value = "Employment: update new employment")
    public ResponseEntity<HttpStatus> deleteEmployment(@PathVariable("id") long id) {
        try {
            employmentService.deleteEmployment(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

}
