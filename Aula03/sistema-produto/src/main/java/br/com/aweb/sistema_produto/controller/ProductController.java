package br.com.aweb.sistema_produto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.aweb.sistema_produto.model.Product;
import br.com.aweb.sistema_produto.service.ProductService;
import org.springframework.web.bind.annotation.GetMapping;


@Controller                          //Define a classe como Controller.
@RequestMapping("/products")         //Define uma rota para a classe.
public class ProductController {
    
    @Autowired
    private ProductService productService;

    // Listar produtos----------------------------------------------------->
    @GetMapping
    public String list(Model model) { // Recebe o 'model' que vai carregar os dados para a view.

        /*
         - Adiciona ao 'model' uma lista de produtos com o nome 'products'.
         - O 'productService.listAll()' chama o serviço que retorna todos os produtos do banco ou de alguma fonte de dados.
        */
        model.addAttribute("products", productService.listAll()); 
        
        /* 
         - Retorna o nome da view que será renderizada.
         - O Spring vai procurar por um arquivo 'list.html' no diretório 'product'.
        */
        return "product/list"; 
    }


    // Método que exibe o formulário para criar ou editar um produto--------->
    @GetMapping("/new") // Anotação que mapeia requisições GET para o caminho "/new". Esse método será chamado quando o usuário acessar esse URL.
    public String showForm(Model model) { // Recebe um 'model' que vai ser usado para passar dados para a view.

        /* 
        - Adiciona ao 'model' um novo objeto 'Product'. 
        - Esse objeto estará disponível na view e será usado para preencher o formulário de cadastro/edição.
        */
        model.addAttribute("product", new Product()); 
        
        /* 
        - Retorna o nome da view (HTML) que será renderizada.
        - O Spring buscará o arquivo 'form.html' na pasta 'products'.
        */
        return "products/form"; 
    }
}