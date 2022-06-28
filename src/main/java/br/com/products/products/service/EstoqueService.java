package br.com.products.products.service;

import br.com.products.products.model.Estoque;
import br.com.products.products.repository.EstoqueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class EstoqueService {

    @Autowired
    EstoqueRepository repository;

    public Estoque findById(long Id) throws Exception {
        return repository.findById(Id).orElseThrow(() -> new Exception("Not Found"));
    }

    public Page<Estoque> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public Estoque save(Estoque estoque) { return repository.save(estoque); }

    public Estoque update(Estoque estoque) throws Exception {
        Estoque e = findById((estoque.getId()));
        e.setEstoqueName(estoque.getEstoqueName());
        return repository.save(e);
    }

    public void delete(long Id) throws Exception {
        Estoque estoque = findById(Id);
        repository.delete(estoque);
    }
}
