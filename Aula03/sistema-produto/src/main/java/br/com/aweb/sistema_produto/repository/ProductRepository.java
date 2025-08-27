package br.com.aweb.sistema_produto.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.aweb.sistema_produto.model.Product;

import java.util.List;


public interface ProductRepository extends JpaRepository<Product, Long>{
    
    List<Product>findByNameContainingIgnoreCase(String name);

}
