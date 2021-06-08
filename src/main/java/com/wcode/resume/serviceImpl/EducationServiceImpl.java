package com.wcode.resume.serviceImpl;

import com.wcode.resume.model.data.Education;
import com.wcode.resume.model.data.Resume;
import com.wcode.resume.repository.EducationRepository;
import com.wcode.resume.repository.ResumeRepository;
import com.wcode.resume.service.EducationService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EducationServiceImpl implements EducationService {

    private EducationRepository educationRepository;
    private ResumeRepository resumeRepository;

    public EducationServiceImpl(EducationRepository educationRepository, ResumeRepository resumeRepository) {
        this.educationRepository = educationRepository;
        this.resumeRepository = resumeRepository;
    }

    @Override
    public Optional<Education> getEducationById(Long id) {
        return educationRepository.findById(id);
    }

    @Override
    public Optional<List<Education>> getAllEducationByIdUser(Long id_user) {
        Resume resume = resumeRepository.findByUser_Id(id_user).get();
        return educationRepository.
                findAllByResume(resume);
    }

    @Override
    public Optional<Education> insertEducation(Long id_user, Education education) {
        Optional<Resume> resume = resumeRepository.findByUser_Id(id_user);
        if(resume.isPresent()){
            education.setResume(resume.get());
            return Optional.ofNullable(educationRepository.save(education));
        }
        return Optional.empty();
    }

    @Override
    public Optional<Education> updateEducation(Long id, Education education) {
        Optional<Education> education1 = educationRepository.findById(id);

        if(education1.isPresent()){
            education1.get().setInstitutionName(education.getInstitutionName());
            education1.get().setTittle(education.getTittle());
            education1.get().setTypeEducation(education.getTypeEducation());
            education1.get().setStartDate(education.getStartDate());
            education1.get().setEndDate(education.getEndDate());
            education1.get().setEducationDescription(education.getEducationDescription());
            return Optional.ofNullable(educationRepository.save(education1.get()));
        }


        return Optional.empty();
    }

    @Override
    public void deleteEducation(Long id) {
        educationRepository.deleteById(id);
    }

}
