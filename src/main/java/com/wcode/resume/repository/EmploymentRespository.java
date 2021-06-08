package com.wcode.resume.repository;

import com.wcode.resume.model.data.Employment;
import com.wcode.resume.model.data.Resume;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EmploymentRespository extends JpaRepository<Employment, Long> {
    Optional<List<Employment>> findAllByResume(Resume resume);
}
