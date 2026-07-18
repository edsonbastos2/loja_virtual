package com.exemploEcommerce.lojaVirtual.services;

import com.exemploEcommerce.lojaVirtual.Dto.ProductDto;
import com.exemploEcommerce.lojaVirtual.entities.Product;
import com.exemploEcommerce.lojaVirtual.repositories.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepository repository;

    public ProductService(ProductRepository repository) {
        this.repository = repository;
    }


    @Transactional(readOnly = true)
    public ProductDto getById(Long id) {
        var product = repository.findById(id).get();
        return new ProductDto(product);
    }

    public Page<ProductDto> findAll(Pageable pageable) {
        var result = repository.findAll(pageable);
        return result.map(ProductDto::new);
    }
}
