package com.jamersc.springboot.demo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@Table(name = "students")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Getter
@Setter
@ToString
// @Builder
public class Student {

//    @Id
//    @SequenceGenerator(
//            name = "student_sequence",
//            sequenceName = "student_sequence",
//            allocationSize = 1
//    )
//    @GeneratedValue(
//            generator = "student_sequence",
//            strategy = GenerationType.SEQUENCE
//    )
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "Name must not be blank")
    @Column(nullable = false)
    private String name;
    @Email(message = "Email should be valid")
    @Column(nullable = false, unique = true)
    private String email;
    @NotNull(message = "Gender must not be null")
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Gender gender;

    public Student(String john, String mail, Gender gender) {
    }

    //optional
//    @PrePersist
//    @PreUpdate
//    public void prePersist() {
//        if (name != null) name = name.trim();
//        if (email != null) email = email.trim().toLowerCase();
//    }
}
