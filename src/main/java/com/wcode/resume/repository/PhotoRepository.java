package com.wcode.resume.repository;

import com.wcode.resume.model.data.Education;
import com.wcode.resume.model.data.Photo;
import com.wcode.resume.model.data.Resume;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PhotoRepository extends JpaRepository<Photo, Long> {
    Optional<Photo> findByResume(Resume resume);
}
