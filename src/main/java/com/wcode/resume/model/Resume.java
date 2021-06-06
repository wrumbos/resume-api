package com.wcode.resume.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "resume")
public class Resume {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 40)
    @NotBlank
    private String fullname;
    @Column(length = 60)
    @NotBlank
    private String address;
    @Column(length = 10)
    @NotBlank
    private String zip;
    @Column(length = 20)
    @NotBlank
    private String phone;
    @Column(length = 500)
    @NotBlank
    private String aboutMe;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "photo_id")
    private Photo photo;

    @OneToMany(mappedBy = "resume", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private Set<Employment> employments;

    @OneToMany(mappedBy = "resume", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private Set<Education> educations;

    @OneToMany(mappedBy = "resume", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private Set<Skill> skills;

}
