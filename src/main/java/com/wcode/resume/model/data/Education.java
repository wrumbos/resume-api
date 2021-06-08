package com.wcode.resume.model.data;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "education")
public class Education {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 50)
    @NotBlank
    private String institutionName;

    @Column(length = 50)
    @NotBlank
    private String tittle;

    @Enumerated(EnumType.STRING)
    @Column(length = 30)
    @NotNull(message = "Please enter TypeEducation")
    private TypeEducation typeEducation;

    @NotNull(message = "Please enter startDate")
    private Date startDate;

    private Date endDate;

    @Column(length = 100)
    @NotBlank
    private String educationDescription;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "resume_id", nullable = false)
    private Resume resume;

    public Education(@NotBlank String institutionName, @NotBlank String title,
                     @NotNull(message = "Please enter TypeEducation") TypeEducation typeEducation,
                     @NotNull(message = "Please enter startDate") Date startDate, Date endDate,
                     @NotBlank String educationDescription, Resume resume) {
        this.institutionName = institutionName;
        this.tittle = title;
        this.typeEducation = typeEducation;
        this.startDate = startDate;
        this.endDate = endDate;
        this.educationDescription = educationDescription;
        this.resume = resume;
    }

    @Override
    public String toString() {
        return "Education{" +
                "institutionName='" + institutionName + '\'' +
                ", title='" + tittle + '\'' +
                ", typeEducation=" + typeEducation +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", educationDescription='" + educationDescription + '\'' +
                '}';
    }
}
