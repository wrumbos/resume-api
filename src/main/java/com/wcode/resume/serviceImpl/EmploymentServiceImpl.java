package com.wcode.resume.serviceImpl;

import com.wcode.resume.model.data.Employment;
import com.wcode.resume.model.data.Resume;
import com.wcode.resume.repository.EmploymentRespository;
import com.wcode.resume.repository.ResumeRepository;
import com.wcode.resume.service.EmploymentService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmploymentServiceImpl implements EmploymentService {

    private ResumeRepository resumeRepository;
    private EmploymentRespository employmentRespository;


    public EmploymentServiceImpl(ResumeRepository resumeRepository, EmploymentRespository employmentRespository) {
        this.resumeRepository = resumeRepository;
        this.employmentRespository = employmentRespository;
    }

    @Override
    public Optional<Employment> getEmploymentById(Long id) {
        return employmentRespository.findById(id);
    }

    @Override
    public Optional<List<Employment>> getAllEmploymentByIdUser(Long id_user) {
        Resume resume = resumeRepository.findByUser_Id(id_user).get();
        return employmentRespository.
                findAllByResume(resume);
    }

    @Override
    public Optional<Employment> insertEmployment(Long id_user, Employment employment) {
        Optional<Resume> resume = resumeRepository.findByUser_Id(id_user);
        if(resume.isPresent()){
            employment.setResume(resume.get());
            return Optional.ofNullable(employmentRespository.save(employment));
        }
        return Optional.empty();
    }

    @Override
    public Optional<Employment> updateEmployment(Long id, Employment employment) {
        Optional<Employment> employment1 = employmentRespository.findById(id);

        if(employment1.isPresent()){
            employment1.get().setCompanyName(employment.getCompanyName());
            employment1.get().setJobTitle(employment.getJobTitle());
            employment1.get().setJobDescription(employment.getJobDescription());
            employment1.get().setStartDate(employment.getStartDate());
            employment1.get().setEndDate(employment.getEndDate());
            return Optional.ofNullable(employmentRespository.save(employment1.get()));
        }


        return Optional.empty();
    }

    @Override
    public void deleteEmployment(Long id) {
        employmentRespository.deleteById(id);
    }
}
