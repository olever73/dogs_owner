package com.telran.dogsowner.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
@Table(name = "dogs")
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Dog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "nickName")
    private String nickName;

    @Column(name = "breed")
    private String breed;

    @Column(name = "dateOfBirth")
    private LocalDate dateOfBirth;

    @ManyToOne
    private Owner owner;

    @Column(name = "registrationDate")
    private LocalDate registrationDate;
}
