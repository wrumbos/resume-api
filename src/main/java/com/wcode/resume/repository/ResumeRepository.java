package com.wcode.resume.repository;

import com.wcode.resume.model.data.Resume;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ResumeRepository extends JpaRepository<Resume, Long> {
    Optional<Resume> findById(long id);
    Optional<Resume> findByUser_Id(long id);
}
