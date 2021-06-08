package com.wcode.resume.model.response;

import com.wcode.resume.model.data.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResumeConsolidate {
    private Resume resume;
    private Photo photo;
    private List<Education> education;
    private List<Employment> employment;
    private List<Skill> skill;


}
