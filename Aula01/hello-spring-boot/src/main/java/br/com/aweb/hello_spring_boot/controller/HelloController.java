package br.com.aweb.hello_spring_boot.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
public class HelloController {
    
    @GetMapping
    public String sayHello(){
        return "Olá Mundo Spring Boot!";
    }

    @GetMapping("/ola")
    public String sayHelloCustom() {
        return "Olá endpoint especifico!";
    }

    @GetMapping("/greet")
    public String greet(@RequestParam("name") String userName) {
        return "olá, " + userName + "! Bem-vindo(a)!";
    }

    //exercicio1----------------------------
        @GetMapping("/calcular")
    public String calcular(
        @RequestParam Integer num1,
        @RequestParam Integer num2,
        @RequestParam(required = false, defaultValue = "Soma") String op
        ) {
        if (op.equals("subtracao"))
            return "O resultado é: " + (num1 + num2);
        return "O resultado é: " + (num1 + num2);
    }

    //exercicio2----------------------------
        @GetMapping("/mensagem")
    public String mensagem(
        @RequestParam(required = false, defaultValue = "visitante") String usuario,
        @RequestParam(required = false, defaultValue = "pt") String idioma) {
        if (idioma.equals("pt"))
            return "pt: Olá," + usuario + "!Bem-vindo(a)";
        return "en: Hello," + usuario + "!Welcome";
    }
    
}
