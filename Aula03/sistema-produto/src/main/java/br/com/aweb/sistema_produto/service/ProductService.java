package br.com.aweb.sistema_produto.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.aweb.sistema_produto.model.Product;
import br.com.aweb.sistema_produto.repository.ProductRepository;

@Service                                           // Atribui a classe como um service.
public class ProductService {
    
    @Autowired                                     // Atribui automatiza a injeção de dependências em classes Java.
    private ProductRepository productRepository;

    // Buscar todos os produtos---------------------->
    public List<Product> listAll(){
        return productRepository.findAll();
    }

    //Buscar produto por ID-------------------------->
    public Product findProduct(Long id){ //Entrega ao metodo um id.
        // Busca um produto no repositório com o ID especificado e armazena o resultado em um objeto Optional(retorna nulo ou optional).
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (optionalProduct.isPresent()) // Se o optionalProduct estiver presente.
            return optionalProduct.get();//Retorna optionalProduct
        throw new RuntimeException("produto não encontrado!");//se nao exibe uma exceção.
    }

    // Inserir ou atualizar produto------------------>
    public Product createProduct(Product product){ //Entrega ao metodo um Product.
        return productRepository.save(product); //Salva produto no banco utilizando save(entity).
    }

    // Excluir produto------------------------------->
    public void deleteproduct(Long id){ //Entrega ao metodo um id.
        if(!productRepository.existsById(id)) //Se produto for diferente de existsById.
            throw new RuntimeException("produto não encontrado!"); //se nao existir o produto.
        productRepository.deleteById(id); // Deleta o produto por ID.
    }

}
