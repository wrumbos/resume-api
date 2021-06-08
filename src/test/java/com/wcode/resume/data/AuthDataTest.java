package com.wcode.resume.data;

import com.wcode.resume.model.data.User;
import com.wcode.resume.repository.RoleRepository;
import com.wcode.resume.repository.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;


@ExtendWith(SpringExtension.class)
@DataJpaTest
public class AuthDataTest {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;

    private User userOne;
    private User userTwo;

    @BeforeEach
    public void setUp() {
        userOne = userRepository.findById(Long.valueOf(1)).get();
        userTwo = userRepository.findById(Long.valueOf(2)).get();
    }

    @AfterEach
    public void tearDown() {
        userRepository.deleteAll();
        userOne = null;
    }

    @Test
    @DisplayName("insert and select USER")
    public void insertFindUser(){
        userRepository.save(userOne);
        User userTest = userRepository.findById(userOne.getId()).get();
        assertEquals("userOne@userOne.com", userTest.getEmail());
    }

    @Test
    @DisplayName("find all USER")
    public void findAllUser(){
        userRepository.save(userOne);
        userRepository.save(userTwo);
        List<User> userList = (List<User>) userRepository.findAll();
        assertEquals(2, userList.size());
    }

    @Test
    @DisplayName("Delete User")
    public void deleteUser() {
        userRepository.save(userOne);
        userRepository.deleteById(userOne.getId());
        Optional optional = userRepository.findById(userOne.getId());
        assertEquals(Optional.empty(), optional);
    }

}
