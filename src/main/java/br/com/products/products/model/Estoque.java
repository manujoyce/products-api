package br.com.products.products.model;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import javax.persistence.*;
import java.io.Serializable;

@Relation(collectionRelation = "estoques")
@Entity
@Table(name = "estoque")
public class Estoque extends RepresentationModel<Estoque> implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "estoque_name", nullable = false, length = 50)
    private String estoqueName;

    public Estoque() {
    }

    public Estoque(long id, String estoqueName) {
        this.id = id;
        this.estoqueName = estoqueName;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEstoqueName() {
        return estoqueName;
    }

    public void setEstoqueName(String estoqueName) {
        this.estoqueName = estoqueName;
    }
}
