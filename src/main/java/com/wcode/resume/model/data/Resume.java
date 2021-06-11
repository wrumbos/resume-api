package com.wcode.resume.model.data;


import com.fasterxml.jackson.annotation.JsonIgnore;
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
    private Long id;

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

    @JsonIgnore
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @JsonIgnore
    @OneToOne(mappedBy = "resume", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private Photo photo;

    @JsonIgnore
    @OneToMany(mappedBy = "resume", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private Set<Employment> employments;

    @JsonIgnore
    @OneToMany(mappedBy = "resume", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private Set<Education> educations;

    @JsonIgnore
    @OneToMany(mappedBy = "resume", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private Set<Skill> skills;

    public Resume(@NotBlank String fullname, @NotBlank String address, @NotBlank String zip,
                  @NotBlank String phone, @NotBlank String aboutMe, User user) {
        this.fullname = fullname;
        this.address = address;
        this.zip = zip;
        this.phone = phone;
        this.aboutMe = aboutMe;
        this.user = user;
    }

    @Override
    public String toString() {
        return "Resume{" +
                "fullname='" + fullname + '\'' +
                ", address='" + address + '\'' +
                ", zip='" + zip + '\'' +
                ", phone='" + phone + '\'' +
                ", aboutMe='" + aboutMe + '\'' +
                '}';
    }
}
