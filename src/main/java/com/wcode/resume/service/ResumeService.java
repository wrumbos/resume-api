package com.wcode.resume.service;

import com.wcode.resume.model.data.Resume;

import java.util.Optional;

public interface ResumeService {
    Optional<Resume> getResumeById(Long id);
    Optional<Resume> insertResume(Resume resume, Long id_user);
    Optional<Resume> updateResume(Resume resume, Long id);
    void delete(Long id);
}
