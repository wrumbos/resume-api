package com.wcode.resume.service;

import com.wcode.resume.model.data.Education;

import java.util.List;
import java.util.Optional;

public interface EducationService {
    Optional<Education> getEducationById(Long id);
    Optional<List<Education>> getAllEducationByIdUser(Long id);
    Optional<Education> insertEducation(Long id_user, Education education);
    Optional<Education> updateEducation(Long id, Education education);
    void deleteEducation(Long id);
}
