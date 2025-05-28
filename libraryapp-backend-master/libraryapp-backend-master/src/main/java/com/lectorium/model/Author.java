package com.lectorium.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name="authors")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer idAuthor;

    @Column(nullable = false, length = 60)
    private String lastName;

    @Column(nullable = false, length = 60)
    private String firstName;

    @Column(nullable  = true)
    private LocalDate birthdate;

    @Column(nullable = false, length = 100)
    private String placeBirthdate;
}
