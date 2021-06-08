package com.wcode.resume.data;

import com.wcode.resume.model.data.Resume;
import com.wcode.resume.model.data.User;
import com.wcode.resume.repository.ResumeRepository;
import com.wcode.resume.repository.RoleRepository;
import com.wcode.resume.repository.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class ResumeDataTest {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private ResumeRepository resumeRepository;

    private User user;

    @BeforeEach
    public void setUp() {
        user = userRepository.findById(Long.valueOf(1)).get();
    }

    @AfterEach
    public void tearDown() {
        resumeRepository.deleteAll();
    }

    @Test
    @DisplayName("insert Resume")
    public void insertResume(){
        Resume resumeSave =
                resumeRepository.save(
                        new Resume(
                                "fullName", "address", "zip", "phone", "aboutMe", user));
        assertEquals("fullName", resumeSave.getFullname());
    }

    @Test
    @DisplayName("find all resume")
    public void insertFindResume(){
        resumeRepository.save(
                new Resume("fullName", "address", "zip", "phone", "aboutMe", user));

        assertEquals(1, resumeRepository.findAll().size());
    }

    @Test
    @DisplayName("Delete User")
    public void deleteResume() {
        Resume resume = resumeRepository.save(
                new Resume("fullName", "address", "zip", "phone", "aboutMe", user));
        resumeRepository.deleteById(resume.getId());
        Optional optional = resumeRepository.findById(resume.getId());
        assertEquals(Optional.empty(), optional);
    }

}
