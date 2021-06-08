package com.wcode.resume.service;

import com.wcode.resume.model.data.Skill;

import java.util.List;
import java.util.Optional;

public interface SkillService {
    Optional<Skill> getSkillById(Long id);
    Optional<List<Skill>> getAllSkillByIdUser(Long id_user);
    Optional<Skill> insertSkill(Long id_user, Skill education);
    Optional<Skill> updateSkill(Long id, Skill education);
    void deleteSkill(Long id);
}
