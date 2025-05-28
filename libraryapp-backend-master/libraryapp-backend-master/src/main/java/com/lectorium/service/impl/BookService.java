package com.lectorium.service.impl;

import com.lectorium.model.Book;
import com.lectorium.repo.IBookRepo;
import com.lectorium.repo.IGenericRepo;
import com.lectorium.service.IBookService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookService extends GenericService<Book, Integer> implements IBookService {
    private final IBookRepo repo;

    @Override
    protected IGenericRepo<Book, Integer> getRepo() {
        return repo;
    }
}
