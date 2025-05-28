package com.lectorium.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthorDTO {
    private Integer idAuthor;
    private String lastName;
    private String firstName;
    private LocalDate birthdate;
    private String placeBirthdate;
}
