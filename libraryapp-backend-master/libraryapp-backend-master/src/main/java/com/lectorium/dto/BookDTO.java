package com.lectorium.dto;

import com.lectorium.model.Category;
import com.lectorium.model.Publisher;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookDTO {
    private Integer idBook;
    private String title;
    private String subtitle;
    private String description;
    private Publisher publisher;
    private Category category;
}
