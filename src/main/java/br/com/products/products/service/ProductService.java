package br.com.products.products.service;

import br.com.products.products.model.Product;
import br.com.products.products.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    ProductRepository repository;

    public Product findById(long Id) throws Exception {
        return repository.findById(Id).orElseThrow(() -> new Exception("Not Found"));
    }

    public List<Product> findAll() { return repository.findAll(); }

    public Product save(Product product) { return repository.save(product); }

    public Product update(Product product) throws Exception {
        Product p = findById(product.getId());
        p.setProductName(product.getProductName());
        p.setProductValue(product.getProductValue());
        return repository.save(p);
    }

    public void delete(long Id) throws Exception {
        Product product = findById(Id);
        repository.delete(product);
    }
}
