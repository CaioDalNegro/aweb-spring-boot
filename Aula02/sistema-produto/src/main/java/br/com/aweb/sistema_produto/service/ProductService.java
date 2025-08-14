package br.com.aweb.sistema_produto.service;

import java.util.List;
import java.util.Optional;

import javax.management.RuntimeErrorException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.aweb.sistema_produto.model.Product;
import br.com.aweb.sistema_produto.repository.ProductRepository;
import jakarta.persistence.Id;

@Service
public class ProductService {
    
    @Autowired
    private ProductRepository productRepository;

    // Buscar todos os produtos
    public List<Product> listAll(){
        return productRepository.findAll();
    }

    //Buscar produto por ID
    public Product finProduct(Long id){
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (optionalProduct.isPresent())
            return optionalProduct.get();
        throw new RuntimeException("produto não encontrado!");
    }

    // Inserir ou atualizar produto
    public Product createProduct(Product product){
        return productRepository.save(product);
    }

    // Excluir produto
    public void deleteproduct(Long id){
        if(!productRepository.existsById(id))
            throw new RuntimeException("produto não encontrado!");
        productRepository.deleteById(id);
    }

}
