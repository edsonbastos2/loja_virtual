package com.exemploEcommerce.lojaVirtual.services;

import com.exemploEcommerce.lojaVirtual.Dto.ProductDto;
import com.exemploEcommerce.lojaVirtual.entities.Product;
import com.exemploEcommerce.lojaVirtual.repositories.ProductRepository;
import com.exemploEcommerce.lojaVirtual.services.exceptions.ResourceNotFoundException;
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
        var product = repository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Recurso não encontrado!"));
        return new ProductDto(product);
    }

    @Transactional(readOnly = true)
    public Page<ProductDto> findAll(Pageable pageable) {
        var result = repository.findAll(pageable);
        return result.map(ProductDto::new);
    }

    @Transactional
    public ProductDto insert(ProductDto dto) {
        var entity = new Product();
        copyDtoToEntity(dto, entity);
        entity = repository.save(entity);
        return new ProductDto(entity);
    }

    @Transactional
    public ProductDto update(Long id, ProductDto dto) {
        var entity = repository.getReferenceById(id);
        copyDtoToEntity(dto, entity);
        entity = repository.save(entity);
        return new ProductDto(entity);
    }

    @Transactional
    public void delete(Long id) {
        repository.deleteById(id);
    }

    private void copyDtoToEntity(ProductDto dto, Product entity) {
        entity.setName(dto.getName());
        entity.setDescription(dto.getDescription());
        entity.setPrice(dto.getPrice());
        entity.setImgUrl(dto.getImgUrl());
    }
}
