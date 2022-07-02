package com.telran.dogsowner.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
@Entity
@Table(name = "dog")

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class Dog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @ManyToOne
    @JoinColumn(name = "owner_id")
    private Owner owner;
    @Column(name = "nickname")
    private String nickname;
    @Column(name = "breed")
	private String breed;
    @Column(name = "dateOfBirth")
    private LocalDate dateOfBirth;
    @Column(name = "registrationDate")
    private LocalDate registrationDate ;





}
