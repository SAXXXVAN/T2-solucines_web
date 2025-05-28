package com.lectorium.service.impl;

import com.lectorium.model.Author;
import com.lectorium.repo.IAuthorRepo;
import com.lectorium.repo.IGenericRepo;
import com.lectorium.service.IAuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthorService extends GenericService<Author, Integer> implements IAuthorService {
    private final IAuthorRepo repo;

    @Override
    protected IGenericRepo<Author, Integer> getRepo() {
        return repo;
    }
}
