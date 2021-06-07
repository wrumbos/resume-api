package com.wcode.resume.service;

import com.wcode.resume.model.data.Education;
import com.wcode.resume.model.data.User;
import com.wcode.resume.model.request.SignupRequest;

import java.util.Optional;

public interface EducationService {
    Optional<Education> insertEducation(Education education);
    Optional<Education> updateEducation(Education education);
    Optional<Education> deleteEducation(Education education);
}
