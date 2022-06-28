package br.com.products.products.controller;

import br.com.products.products.model.Estoque;
import br.com.products.products.model.Product;
import br.com.products.products.service.EstoqueService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.*;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static io.swagger.models.properties.PropertyBuilder.toModel;

@Api(value = "This service manipulates the Estoque resource", tags = {"estoque, api, service,"})
@RestController
@RequestMapping("/estoque/v1")
public class EstoqueController {

    public void buildEntityLink(Estoque category) throws Exception {
        category.add(
                WebMvcLinkBuilder.linkTo(
                        WebMvcLinkBuilder.methodOn(
                                EstoqueController.class).findById(category.getId()
                        )
                ).withSelfRel()
        );
    }

    private void buildEntityLink (Product product) throws Exception {
        product.add(WebMvcLinkBuilder.linkTo(
                WebMvcLinkBuilder.methodOn(
                        ProductController.class).findById(product.getId())
                ).withSelfRel());
        if(!product.getEstoque().hasLinks()){
            Link productLink = WebMvcLinkBuilder.linkTo(
                    WebMvcLinkBuilder.methodOn(
                            ProductController.class).findById(product.getEstoque().getId())).withSelfRel();
            product.getEstoque().add(productLink);
        }
    }

    @Autowired
    private EstoqueService service;

    @ApiOperation(value = "Get all registered estoques")
    @GetMapping(value = "/",  produces = {"application/json", "application/xml", "application/x-yaml"})
    public ResponseEntity<PagedModel<Estoque>> findAll (@RequestParam(value = "page", defaultValue = "0") int page,
                                                        @RequestParam(value = "size", defaultValue = "10") int size,
                                                        @RequestParam(value = "direction", defaultValue = "asc") String direction,
                                                        PagedResourcesAssembler<Estoque> assembler) throws Exception {
        var sortDirection = "desc".equalsIgnoreCase(direction) ? Sort.Direction.DESC: Sort.Direction.ASC;

        Pageable pageable = PageRequest.of(page, size, Sort.by(sortDirection, "estoque_name"));

        Page<Estoque> estoques = service.findAll(pageable);
        for (Estoque estoque:estoques) {
            buildEntityLink(estoque);
        }
        return new ResponseEntity(assembler.toModel(estoques), HttpStatus.OK);
    }

    @ApiOperation(value = "Find a estoque by id.", response = Estoque.class)
    @GetMapping(value = "/{id}", produces = {"application/json", "application/xml", "application/x-yaml"})
    public Estoque findById ( @PathVariable("id") long Id) throws Exception {
        Estoque estoque = service.findById(Id);
        buildEntityLink(estoque);
        return estoque;
    }

    @ApiOperation(value = "Store a newly Estoque", consumes = "application/json, application/xml", produces = "application/json, application/xml")
    @PostMapping(consumes = {"application/json", "application/xml", "application/x-yaml"}, produces = {"application/json", "application/xml", "application/x-yaml"})
    public Estoque save(@RequestBody Estoque estoque) { return service.save(estoque); }

    @ApiOperation(value = "Update a estoque by ID")
    @PutMapping(consumes = {"application/json", "application/xml", "application/x-yaml"}, produces = {"application/json", "application/xml", "application/x-yaml"})
    public Estoque update(@RequestBody Estoque estoque) throws Exception {
        return service.update(estoque);
    }

    @ApiOperation(value = "Delete a estoque by ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") long Id) throws Exception {
        service.delete(Id);
        return ResponseEntity.ok().build();
    }
}
