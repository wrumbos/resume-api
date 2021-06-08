package com.wcode.resume.data;


import com.wcode.resume.model.data.Employment;
import com.wcode.resume.model.data.Resume;
import com.wcode.resume.model.data.Skill;
import com.wcode.resume.repository.EmploymentRespository;
import com.wcode.resume.repository.ResumeRepository;
import com.wcode.resume.repository.SkillRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class SkillDataTest {

    @Autowired
    private ResumeRepository resumeRepository;
    @Autowired
    private SkillRepository skillRepository;

    private Resume resume;

    @BeforeEach
    public void setUp() {
        resume = resumeRepository.findById(Long.valueOf(1)).get();
    }

    @AfterEach
    public void tearDown() {
        skillRepository.deleteAll();
    }

    @Test
    @DisplayName("insert Education")
    public void insertEducation(){
        Skill skill = new Skill("companyName",5);
        skill.setResume(resume);
        Skill skill1 = skillRepository.save(skill);

        assertEquals(skill1.getSelfAppraisal(), skill.getSelfAppraisal());
    }

    @Test
    @DisplayName("find all Education")
    public void insertFindEducation(){
        Skill skill = new Skill("companyName",5);
        skill.setResume(resume);

        skillRepository.save(skill);
        assertEquals(1, skillRepository.findAll().size());
    }

    @Test
    @DisplayName("Delete Education")
    public void deleteEducation() {
        Skill skill = new Skill("companyName",5);
        skill.setResume(resume);
        Skill skill1 = skillRepository.save(skill);

        skillRepository.deleteById(skill1.getId());
        Optional optional = skillRepository.findById(skill1.getId());
        assertEquals(Optional.empty(), optional);
    }

}
