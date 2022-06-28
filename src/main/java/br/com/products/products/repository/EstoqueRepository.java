package br.com.products.products.repository;

import br.com.products.products.model.Estoque;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface EstoqueRepository extends JpaRepository<Estoque, Long> {
    @Query(value = "SELECT * FROM estoque ORDER BY id", nativeQuery = true)
    Page<Estoque> findAll(Pageable pageable);
}
