package com.wcode.resume.controller;

import com.wcode.resume.exception.ApiRequestException;
import com.wcode.resume.model.data.Employment;
import com.wcode.resume.model.data.Skill;
import com.wcode.resume.model.response.ApiResponse;
import com.wcode.resume.serviceImpl.SkillServiceImpl;
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
@Api(value = "Skill Services")
public class SkillController {

    SkillServiceImpl skillService;

    public SkillController(SkillServiceImpl skillService) {
        this.skillService = skillService;
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/skill/{id}")
    @ApiOperation(value = "Skill: get skill")
    public ResponseEntity getSkillById(@PathVariable("id") long id) {
        return skillService.getSkillById(id)
                .map(skills -> ResponseEntity.ok(skills))
                .orElseThrow(() -> new ApiRequestException("Ocurrio un error encontrando skill"));

    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/skill/getAll/{id_user}")
    @ApiOperation(value = "Skill: get skill")
    public ResponseEntity getAllSkillByIdUser(@PathVariable("id_user") long id_user) {
        return skillService.getAllSkillByIdUser(id_user)
                .map(skills -> ResponseEntity.ok(skills))
                .orElseThrow(() -> new ApiRequestException("Ocurrio un error encontrando todos skill"));

    }

    @PreAuthorize("hasRole('USER')")
    @PostMapping("/skill/{id_user}")
    @ApiOperation(value = "Skill: Creates new skill")
    public ResponseEntity registerSkill(@PathVariable("id_user") long id_user, @Valid @RequestBody Skill skill) {
        return skillService.insertSkill(id_user, skill)
                .map(skills -> ResponseEntity.ok(new ApiResponse(true, skills.toString())))
                .orElseThrow(() -> new ApiRequestException("Ocurrio un error creando skill"));

    }

    @PreAuthorize("hasRole('USER')")
    @PutMapping("/skill/{id}")
    @ApiOperation(value = "Skill: update skill")
    public ResponseEntity updateSkill(@PathVariable("id") long id, @Valid @RequestBody Skill skill) {
        return skillService.updateSkill(id, skill)
                .map(skills -> ResponseEntity.ok(new ApiResponse(true, skills.toString())))
                .orElseThrow(() -> new ApiRequestException("Ocurrio un error actualizando skill"));

    }

    @PreAuthorize("hasRole('USER')")
    @DeleteMapping("/skill/{id}")
    @ApiOperation(value = "Skill: update new employment")
    public ResponseEntity<HttpStatus> deleteSkill(@PathVariable("id") long id) {
        try {
            skillService.deleteSkill(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

}
