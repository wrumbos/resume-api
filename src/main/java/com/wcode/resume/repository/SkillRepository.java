package com.wcode.resume.repository;

import com.wcode.resume.model.data.Resume;
import com.wcode.resume.model.data.Skill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SkillRepository extends JpaRepository<Skill, Long> {
    Optional<List<Skill>> findAllByResume(Resume resume);
}
