package br.com.aweb.sistema_produto.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.aweb.sistema_produto.model.Product;
import br.com.aweb.sistema_produto.service.ProductService;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller                          // Define a classe como Controller. Classe responsável pelas interações com o usuário (via páginas web).
@RequestMapping("/products")         // Define o mapeamento de URL base para a classe. Esse mapeamento vai ser usado para todas as rotas relacionadas ao 'products'.
public class ProductController {
    
    @Autowired
    private ProductService productService;  // Injeção de dependência do serviço que vai lidar com as operações relacionadas ao Produto.

    // Listar produtos ------------------------------------------------------>
    @GetMapping
    public String list(Model model) {  // Método para listar todos os produtos. Recebe 'model' que será usado para passar dados à view.

        /*
         - Adiciona ao 'model' uma lista de produtos com o nome 'products'.
         - O 'productService.listAll()' chama o serviço que retorna todos os produtos do banco de dados ou outra fonte de dados.
        */
        model.addAttribute("products", productService.listAll()); 
        
        /* 
         - Retorna o nome da view que será renderizada. O Spring buscará o arquivo 'list.html' dentro do diretório 'product' para renderizar a lista de produtos.
        */
        return "list"; 
    }


    // Método que exibe o formulário para criar ou editar um produto -----------> 
    @GetMapping("/new")  // Anotação GET para mapear a URL "/new". Esse método será chamado quando o usuário acessar essa URL para criar um novo produto.
    public String create(Model model) {  // Método que prepara um novo produto para ser inserido. Recebe o 'model' para passar o produto à view.

        /* 
        - Adiciona ao 'model' um novo objeto 'Product'. 
        - Esse objeto será usado para preencher o formulário de criação de um novo produto na view.
        */
        model.addAttribute("product", new Product()); 
        
        /* 
         - Retorna o nome da view (HTML) que será renderizada.
         - O Spring buscará o arquivo 'form.html' no diretório 'products'.
        */
        return "form"; 
    }

    // Método que exibe o formulário para editar um produto existente -----------> 
    @GetMapping("/edit/{id}")  // Anotação GET para mapear a URL "/edit/{id}", onde o ID é passado como parâmetro para editar um produto específico.
    public String edit(@PathVariable Long id, Model model, RedirectAttributes attributes) {  // O ID é passado através da URL para buscar o produto a ser editado.

        try {
            // Recupera o produto do banco de dados através do ID e o adiciona ao 'model' para ser usado na view.
            model.addAttribute("product", productService.findProduct(id)); 
            return "form";  // Retorna a view 'form.html' para editar o produto.
        } catch (Exception e) {
            // Caso ocorra algum erro (por exemplo, se o produto não for encontrado), redireciona o usuário com uma mensagem de erro.
            attributes.addFlashAttribute("error", e.getMessage());
            return "redirect:/products";  // Redireciona para a lista de produtos em caso de erro.
        }
    }

    // Método para salvar ou atualizar um produto -------------------------->
    @PostMapping  // Anotação POST para mapear o envio de um formulário.
    public String save(@Valid Product product, BindingResult result, RedirectAttributes attributes) {
        if(result.hasErrors())  // Verifica se existem erros de validação nos dados do produto.
            return "form";  // Se houver erros, retorna o formulário para o usuário corrigir os dados.

        productService.createProduct(product);  // Chama o serviço para salvar ou atualizar o produto no banco de dados.
        
        // Adiciona uma mensagem de sucesso que será exibida na próxima requisição.
        attributes.addFlashAttribute("message", "Produto salvo com sucesso!");
        
        // Redireciona o usuário para a página de listagem de produtos.
        return "redirect:/products";
    }

    // Método para excluir um produto -------------------------------------->
    @GetMapping("/delete/{id}")  // Anotação GET para mapear a URL "/delete/{id}" onde o ID do produto a ser excluído será passado.
    public String delete(@PathVariable Long id, RedirectAttributes attributes) {  // Recebe o ID do produto que deve ser excluído.

        try {
            // Chama o serviço para excluir o produto baseado no ID fornecido.
            productService.deleteproduct(id);
            // Adiciona uma mensagem de sucesso que será exibida após o redirecionamento.
            attributes.addFlashAttribute("message", "Produto excluído com sucesso!");
        } catch (Exception e) {
            // Se ocorrer algum erro (por exemplo, produto não encontrado), uma mensagem de erro é adicionada ao redirecionamento.
            attributes.addFlashAttribute("error", e.getMessage());
        }
        // Redireciona para a lista de produtos após tentar excluir o produto.
        return "redirect:/products";
    }


    // Buscar produto por nome ------------------------------------------------------>
    @GetMapping("/search")
    public String search(@RequestParam(name = "name", required = false) String name, Model model) {
        if(name != null && !name.isBlank()){
            List<Product> products = productService.findByName(name);
            model.addAttribute("products", products);
        }
        return "search";
    }
}