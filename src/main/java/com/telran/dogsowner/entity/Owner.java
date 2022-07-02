package com.telran.dogsowner.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "owner")

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class Owner {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "firstName")
   private String firstName;

    @Column(name = "lastName")
    private String lastName;
    @Column(name = "dateOfBirth")
    private LocalDate dateOfBirth;









}
