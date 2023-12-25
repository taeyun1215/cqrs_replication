package com.example.demo.category;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepo categoryRepo;

    @Override
    public void categoryRegister(CategoryRegisterRequest categoryRegisterRequest) {
        Category category = Category.builder()
                .name(categoryRegisterRequest.getName())
                .description1(categoryRegisterRequest.getDescription1())
                .description2(categoryRegisterRequest.getDescription2())
                .description3(categoryRegisterRequest.getDescription3())
                .build();
        categoryRepo.save(category);
    }

    @Override
    public Category getCategory(Long id) {
        return categoryRepo.findById(id).orElseThrow();
    }

}
