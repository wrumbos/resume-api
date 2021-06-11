package com.wcode.resume.data;

import com.wcode.resume.model.data.Employment;
import com.wcode.resume.model.data.Resume;
import com.wcode.resume.repository.EmploymentRespository;
import com.wcode.resume.repository.ResumeRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.sql.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class EmploymentDataTest {

    @Autowired
    private ResumeRepository resumeRepository;
    @Autowired
    private EmploymentRespository employmentRespository;

    private Resume resume;

    @BeforeEach
    public void setUp() {
        resume = resumeRepository.findById(Long.valueOf(1)).get();
    }

    @AfterEach
    public void tearDown() {
        employmentRespository.deleteAll();
    }

    @Test
    @DisplayName("insert Education")
    public void insertEducation(){
        long now = System.currentTimeMillis();
        Employment employment = new Employment("companyName","jobTittle",
                new Date(now), new Date(now), "jobDesciprion");
        employment.setResume(resume);
        Employment employment1 = employmentRespository.save(employment);

        assertEquals(employment.getCompanyName(),employment1.getCompanyName());
    }

    @Test
    @DisplayName("find all Education")
    public void insertFindEducation(){
        long now = System.currentTimeMillis();
        Employment employment = new Employment("companyName","jobTittle",
                new Date(now), new Date(now), "jobDesciprion");
        employment.setResume(resume);

        employmentRespository.save(employment);
        assertEquals(1, employmentRespository.findAll().size());
    }

    @Test
    @DisplayName("Delete Education")
    public void deleteEducation() {
        long now = System.currentTimeMillis();
        Employment employment = new Employment("companyName","jobTittle",
                new Date(now), new Date(now), "jobDesciprion");
        employment.setResume(resume);
        Employment employment1 = employmentRespository.save(employment);
        employmentRespository.deleteById(employment1.getId());
        Optional optional = employmentRespository.findById(employment1.getId());
        assertEquals(Optional.empty(), optional);
    }

}
