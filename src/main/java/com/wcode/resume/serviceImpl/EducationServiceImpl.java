package com.wcode.resume.serviceImpl;

import com.wcode.resume.model.data.Education;
import com.wcode.resume.repository.EducationRepository;
import com.wcode.resume.service.EducationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

public class EducationServiceImpl implements EducationService {

    private static final Logger logger = LoggerFactory.getLogger(AuthServiceImpl.class);

    public EducationServiceImpl(EducationRepository educationRepository) {
        this.educationRepository = educationRepository;
    }

    private EducationRepository educationRepository;

    @Override
    public Optional<Education> insertEducation(Education education) {
        return Optional.ofNullable(educationRepository.save(education));
    }

    @Override
    public Optional<Education> updateEducation(Education education) {
        return Optional.ofNullable(educationRepository.save(education));
    }

    @Override
    public Optional<Education> deleteEducation(Education education) {
        return Optional.empty();
    }
}
