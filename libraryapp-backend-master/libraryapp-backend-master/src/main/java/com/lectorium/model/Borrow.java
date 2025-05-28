package com.lectorium.model;

import java.time.LocalDateTime;

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
public class Borrow {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer idBorrow;

    @ManyToOne
    @JoinColumn(name="id_user", nullable = false,
            foreignKey = @ForeignKey(name="FK_BORROW_USER"))
    private User user;

    @ManyToOne
    @JoinColumn(name="id_member", nullable = false,
            foreignKey = @ForeignKey(name="FK_BORROW_MEMBER"))
    private Member member;

    @ManyToOne
    @JoinColumn(name="id_author_book", nullable = false,
            foreignKey = @ForeignKey(name="FK_BORROW_AUTHORBOOK"))
    private AuthorBook authorBook;

    @Column(nullable = false)
    private LocalDateTime fromDate;

    @Column(nullable = false)
    private LocalDateTime toDate;
}
