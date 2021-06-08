package com.wcode.resume.serviceImpl;

import com.wcode.resume.model.data.Resume;
import com.wcode.resume.model.data.User;
import com.wcode.resume.repository.ResumeRepository;
import com.wcode.resume.repository.UserRepository;
import com.wcode.resume.service.ResumeService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ResumeServiceImpl implements ResumeService {

    private UserRepository userRepository;
    private ResumeRepository resumeRepository;
    private EmploymentServiceImpl employmentService;

    public ResumeServiceImpl(UserRepository userRepository, ResumeRepository resumeRepository, EmploymentServiceImpl employmentService) {
        this.userRepository = userRepository;
        this.resumeRepository = resumeRepository;
        this.employmentService = employmentService;
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

}
