package com.wcode.resume.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "education")
public class Education {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 20)
    @NotBlank
    private String institutionName;

    @Column(length = 20)
    @NotBlank
    private String title;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    @NotBlank
    private TypeEducation typeEducation;

    @NotBlank
    private Date startDate;

    private Date endDate;

    @Column(length = 100)
    @NotBlank
    private String educationDescription;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "resume_id", nullable = false)
    private Resume resume;

}
