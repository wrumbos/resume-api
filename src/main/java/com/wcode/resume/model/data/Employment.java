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
@Table(name = "employment")
public class Employment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 50)
    @NotNull(message = "Please enter companyName")
    private String companyName;

    @Column(length = 50)
    @NotNull(message = "Please enter jobTitle")
    private String jobTitle;

    @NotNull(message = "Please enter startDate")
    private Date startDate;

    private Date endDate;

    @Column(length = 100)
    private String jobDescription;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "resume_id", nullable = false)
    private Resume resume;

    public Employment(@NotBlank String companyName, @NotBlank String jobTitle, Date endDate,
                      @NotBlank Date startDate, String jobDescription) {
        this.companyName = companyName;
        this.jobTitle = jobTitle;
        this.startDate = startDate;
        this.endDate = endDate;
        this.jobDescription = jobDescription;
    }

    @Override
    public String toString() {
        return "Employment{" +
                "companyName='" + companyName + '\'' +
                ", jobTitle='" + jobTitle + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", jobDescription='" + jobDescription + '\'' +
                '}';
    }
}
