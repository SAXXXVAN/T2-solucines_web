package com.lectorium.model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class AuthorBook {
    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idAuthorBook;

    @ManyToOne
    @JoinColumn(name="id_author", nullable = false,
            foreignKey = @ForeignKey(name="FK_AUTHORBOOK_AUTHOR"))
    private Author author;

    @ManyToOne
    @JoinColumn(name="id_book", nullable = false,
            foreignKey = @ForeignKey(name="FK_AUTHORBOOK_BOOK"))
    private Book book;

    private LocalDate publishedDate;
    private Integer nroEdition;

    @Column(nullable = false)
    private Boolean available;
}
