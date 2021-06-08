package com.wcode.resume.serviceImpl;

import com.wcode.resume.model.data.*;
import com.wcode.resume.model.response.ResumeConsolidate;
import com.wcode.resume.repository.*;
import com.wcode.resume.service.ResumeService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ResumeServiceImpl implements ResumeService {

    private UserRepository userRepository;
    private ResumeRepository resumeRepository;
    private EmploymentRespository employmentRespository;
    private EducationRepository educationRespository;
    private SkillRepository skillRepository;
    private PhotoRepository photoRepository;

    public ResumeServiceImpl(UserRepository userRepository, ResumeRepository resumeRepository,
                             EmploymentRespository employmentRespository, EducationRepository educationRespository,
                             SkillRepository skillRepository, PhotoRepository photoRepository) {
        this.userRepository = userRepository;
        this.resumeRepository = resumeRepository;
        this.employmentRespository = employmentRespository;
        this.educationRespository = educationRespository;
        this.skillRepository = skillRepository;
        this.photoRepository = photoRepository;
    }

    @Override
    public Optional<Resume> getResumeById(Long id) {
        return resumeRepository.findById(id);
    }

    @Override
    public Optional<Resume> insertResume(Resume resume, Long id_user) {
        Optional<User> user = userRepository.findById(id_user);
        resume.setUser(user.get());
        return Optional.ofNullable(resumeRepository.save(resume));
    }

    @Override
    public Optional<Resume> updateResume(Resume resume, Long id) {
        Optional<Resume> resume1 = resumeRepository.findById(id);

        if (resume1.isPresent()) {
            resume1.get().setFullname(resume.getFullname());
            resume1.get().setAddress(resume.getAddress());
            resume1.get().setZip(resume.getZip());
            resume1.get().setPhone(resume.getPhone());
            resume1.get().setAboutMe(resume.getAboutMe());
            return Optional.ofNullable(resumeRepository.save(resume1.get()));
        }
        return Optional.empty();

    }

    @Override
    public Optional<ResumeConsolidate> getConsolidate(String userName) {
        ResumeConsolidate resumeConsolidate = new ResumeConsolidate();
        Optional<User> user = userRepository.findByUsername(userName);

        if(user.isPresent()){
            Optional<Resume> resume1 = resumeRepository.findByUser_Id(user.get().getId());

            if (resume1.isPresent())
                resumeConsolidate.setResume(resume1.get());

            Optional<Photo> photo = photoRepository.findByResume(resume1.get());

            if (photo.isPresent())
                resumeConsolidate.setPhoto(photo.get());

            if (resume1.isPresent())
                resumeConsolidate.setResume(resume1.get());

            Optional<List<Employment>> employmentList = employmentRespository.findAllByResume(resume1.get());

            if (employmentList.isPresent())
                resumeConsolidate.setEmployment(employmentList.get());

            Optional<List<Education>> educationList = educationRespository.findAllByResume(resume1.get());

            if (educationList.isPresent())
                resumeConsolidate.setEducation(educationList.get());

            Optional<List<Skill>> skills = skillRepository.findAllByResume(resume1.get());

            if (skills.isPresent())
                resumeConsolidate.setSkill(skills.get());

        }

        return Optional.ofNullable(resumeConsolidate);
    }

}
