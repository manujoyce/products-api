package br.com.products.products.controller;

import br.com.products.products.model.Product;
import br.com.products.products.service.ProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(value = "This service manipulates the Product resource", tags = {"product, api, service,"})
@RestController
@RequestMapping("/product/v1")
public class ProductController {

    @Autowired
    private ProductService service;

    @ApiOperation(value = "Get all registered products")
    @GetMapping(produces = {"application/json", "application/xml", "application/x-yaml"})
    public List<Product> findAll() { return service.findAll(); }

    @ApiOperation(value = "Find a product by id", response = Product.class)
    @GetMapping(value = "/{id}", produces = {"application/json", "application/xml", "application/x-yaml"})
    public Product findById(@PathVariable("id") long Id) throws Exception {
        return service.findById(Id);
    }

    @ApiOperation(value = "Store a newly Product", consumes = "application/json, application/xml", produces = "application/json, application/xml")
    @PostMapping(consumes = {"application/json", "application/xml", "application/x-yaml"}, produces = {"application/json", "application/xml", "application/x-yaml"})
    public Product save(@RequestBody Product product){
        return service.save(product);
    }

    @ApiOperation(value = "Update a product by ID")
    @PutMapping(consumes = {"application/json", "application/xml"}, produces = {"application/json", "application/xml"})
    public Product update(@RequestBody Product product) throws Exception {
        return service.update(product);
    }

    @ApiOperation(value = "Delete a product by ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") long Id) throws Exception {
        service.delete(Id);
        return ResponseEntity.ok().build();
    }
}
