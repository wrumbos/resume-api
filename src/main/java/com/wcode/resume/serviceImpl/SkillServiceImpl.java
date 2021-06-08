package com.wcode.resume.serviceImpl;

import com.wcode.resume.model.data.Resume;
import com.wcode.resume.model.data.Skill;
import com.wcode.resume.repository.ResumeRepository;
import com.wcode.resume.repository.SkillRepository;
import com.wcode.resume.service.SkillService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class SkillServiceImpl implements SkillService {

    private ResumeRepository resumeRepository;
    private SkillRepository skillRepository;

    public SkillServiceImpl(ResumeRepository resumeRepository, SkillRepository skillRepository) {
        this.resumeRepository = resumeRepository;
        this.skillRepository = skillRepository;
    }

    @Override
    public Optional<Skill> getSkillById(Long id) {
        return skillRepository.findById(id);
    }

    @Override
    public Optional<List<Skill>> getAllSkillByIdUser(Long id_user) {
        Resume resume = resumeRepository.findByUser_Id(id_user).get();
        return skillRepository.
                findAllByResume(resume);
    }

    @Override
    public Optional<Skill> insertSkill(Long id_user, Skill skill) {
        Optional<Resume> resume = resumeRepository.findByUser_Id(id_user);
        if(resume.isPresent()){
            skill.setResume(resume.get());
            return Optional.ofNullable(skillRepository.save(skill));
        }
        return Optional.empty();
    }

    @Override
    public Optional<Skill> updateSkill(Long id, Skill skill) {
        Optional<Skill> skill1 = skillRepository.findById(id);

        if(skill1.isPresent()){
            skill1.get().setName(skill.getName());
            skill1.get().setSelfAppraisal(skill.getSelfAppraisal());
            return Optional.ofNullable(skillRepository.save(skill1.get()));
        }


        return Optional.empty();
    }

    @Override
    public void deleteSkill(Long id) {
        skillRepository.deleteById(id);
    }
}
