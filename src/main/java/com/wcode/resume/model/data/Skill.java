package com.wcode.resume.model.data;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "skill")
public class Skill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 30)
    @NotNull(message = "Please enter name")
    private String name;

    @NotNull(message = "Please enter selfAppraisal")
    private int selfAppraisal;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "resume_id", nullable = false)
    private Resume resume;

    public Skill(@NotNull(message = "Please enter name") String name,
                 @NotNull(message = "Please enter selfAppraisal") int selfAppraisal) {
        this.name = name;
        this.selfAppraisal = selfAppraisal;
    }

    @Override
    public String toString() {
        return "Skill{" +
                "name='" + name + '\'' +
                ", selfAppraisal=" + selfAppraisal +
                '}';
    }
}
