package br.com.aweb.crud_no_db.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.aweb.crud_no_db.dto.ProductDTO;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PutMapping;



@RestController
@RequestMapping("/products")
public class ProductController {

    private Map<Long, ProductDTO> products = new HashMap<>();
    private Long nextId = 1L;//L para o long carregar com 64bts

    //Listar todos os Produtos----------->
    @GetMapping
    public List<ProductDTO> allProducts(){
        return new ArrayList<>(products.values());
    }

    //Buscar produto por ID-------------->
    @GetMapping("/{id}")
    public ProductDTO getProductById(@PathVariable Long id) {
        return products.get(id);
    }

    //Criar Produto---------------------->
    @PostMapping
    public ProductDTO createProduct(@RequestBody ProductDTO product){
        product.setId(nextId++);
        products.put(product.getId(), product);
        return product;
    }
    
    //Remover Produto-------------------->
    @DeleteMapping("/{id}")
    public String deleteProduct(@PathVariable Long id){
        if(products.remove(id) != null)
            return "Produto removido!";
        return "Produto não encontrado!";
    }

    //Atualizar Produto------------------>
    @PutMapping("/{id}")
    public ProductDTO updateProduct(
        @PathVariable Long id, //pega o id
        @RequestBody ProductDTO updateProduct) {

        if(products.containsKey(id)){//Verifica se contem o id selecionado
            updateProduct.setId(id);//Se sim ele seta o id e o atualiza
            products.put(id, updateProduct);
        }
        return null;
    }
}
