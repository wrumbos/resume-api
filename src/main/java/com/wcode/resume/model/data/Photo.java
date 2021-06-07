package com.wcode.resume.model.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "photo")
public class Photo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private String type;

    @Lob
    private byte[] data;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "resume_id")
    private Resume resume;

}
