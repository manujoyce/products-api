package br.com.products.products.model;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import javax.persistence.*;
import java.io.Serializable;

@Relation(collectionRelation = "products")
@Entity
@Table(name="product")
public class Product extends RepresentationModel<Product> implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "estoque_id")
    private Estoque estoque;

    @Column(name="product_name", nullable = false, length = 50)
    private String productName;

    @Column(name="product_value", nullable = false, length = 50)
    private Long productValue;

    public Product() {
    }

    public Product(long id, Estoque estoque, String productName, Long productValue) {
        this.id = id;
        this.estoque = estoque;
        this.productName = productName;
        this.productValue = productValue;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Estoque getEstoque() {
        return estoque;
    }

    public void setEstoque(Estoque estoque) {
        this.estoque = estoque;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Long getProductValue() {
        return productValue;
    }

    public void setProductValue(Long productValue) {
        this.productValue = productValue;
    }
}
