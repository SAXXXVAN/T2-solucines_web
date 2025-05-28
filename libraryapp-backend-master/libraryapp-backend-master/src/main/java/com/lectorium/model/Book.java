package com.lectorium.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name="books")
public class Book {
    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idBook;

    @Column(nullable = false, length = 100)
    private String title;

    @Column(length = 100)
    private String subtitle;

    @Column(length = 250)
    private String description;

    // Relaciones
    @ManyToOne // FK
    @JoinColumn(name="id_publisher", nullable = false,
            foreignKey = @ForeignKey(name="FK_BOOK_PUBLISHER"))
    private Publisher publisher;

    @ManyToOne // FK
    @JoinColumn(name="id_category", nullable = false,
            foreignKey = @ForeignKey(name="FK_BOOK_CATEGORY"))
    private Category category;
}
