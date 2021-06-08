package com.wcode.resume.model.response;

import com.wcode.resume.model.data.Education;
import com.wcode.resume.model.data.Employment;
import com.wcode.resume.model.data.Resume;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResumeConsolidate {
    private Resume resume;
    private List<Education> education;
    private List<Employment> employment;
}
