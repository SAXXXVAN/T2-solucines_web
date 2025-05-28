package com.lectorium.service.impl;

import com.lectorium.model.Category;
import com.lectorium.repo.ICategoryRepo;
import com.lectorium.repo.IGenericRepo;
import com.lectorium.service.ICategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryService extends GenericService<Category, Integer> implements ICategoryService {
    private final ICategoryRepo repo;

    @Override
    protected IGenericRepo<Category, Integer> getRepo() {
        return repo;
    }
}
