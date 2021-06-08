package com.wcode.resume.data;

import com.wcode.resume.model.data.Education;
import com.wcode.resume.model.data.Resume;
import com.wcode.resume.model.data.TypeEducation;
import com.wcode.resume.model.data.User;
import com.wcode.resume.repository.EducationRepository;
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
public class EducationDataResume {

    @Autowired
    private ResumeRepository resumeRepository;
    @Autowired
    private EducationRepository educationRepository;

    private Resume resume;

    @BeforeEach
    public void setUp() {
        resume = resumeRepository.findById(Long.valueOf(1)).get();
    }

    @AfterEach
    public void tearDown() {
        educationRepository.deleteAll();
    }

    @Test
    @DisplayName("insert Education")
    public void insertEducation(){
        long now = System.currentTimeMillis();
        Education education = new Education("institutionName","tittle",
                TypeEducation.EDUCATION_CERTIFICATIONS, new Date(now),new Date(now),
                "descripcion", resume);

        Education educationSave = educationRepository.save(education);

        assertEquals(educationSave.getTittle(),education.getTittle());
    }

    @Test
    @DisplayName("find all Education")
    public void insertFindEducation(){
        long now = System.currentTimeMillis();
        Education education = new Education("institutionName","tittle",
                TypeEducation.EDUCATION_CERTIFICATIONS, new Date(now),new Date(now),
                "descripcion", resume);
        educationRepository.save(education);
        assertEquals(1, educationRepository.findAll().size());
    }

    @Test
    @DisplayName("Delete Education")
    public void deleteEducation() {
        long now = System.currentTimeMillis();
        Education education = new Education("institutionName","tittle",
                TypeEducation.EDUCATION_CERTIFICATIONS, new Date(now),new Date(now),
                "descripcion", resume);
        Education educationSave = educationRepository.save(education);
        educationRepository.deleteById(educationSave.getId());
        Optional optional = educationRepository.findById(educationSave.getId());
        assertEquals(Optional.empty(), optional);
    }

}
