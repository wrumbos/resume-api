package com.wcode.resume.service;

import com.wcode.resume.model.data.Employment;

import java.util.List;
import java.util.Optional;

public interface EmploymentService {
    Optional<Employment> getEmploymentById(Long id);
    Optional<List<Employment>> getAllEmploymentByIdUser(Long id);
    Optional<Employment> insertEmployment(Long id_user, Employment education);
    Optional<Employment> updateEmployment(Long id, Employment education);
    void deleteEmployment(Long id);
}
