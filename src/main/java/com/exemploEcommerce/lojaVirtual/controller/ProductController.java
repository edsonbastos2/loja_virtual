package com.exemploEcommerce.lojaVirtual.controller;


import com.exemploEcommerce.lojaVirtual.Dto.ProductDto;
import com.exemploEcommerce.lojaVirtual.entities.Product;
import com.exemploEcommerce.lojaVirtual.repositories.ProductRepository;
import com.exemploEcommerce.lojaVirtual.services.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping(value = "/products")
public class ProductController {

    private final ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }

    @GetMapping(value = "{id}")
    public ProductDto findById(@PathVariable Long id) {
        return service.getById(id);
    }

    @GetMapping
    public Page<ProductDto> listAll(Pageable pageable) {
        return service.findAll(pageable);
    }
}
