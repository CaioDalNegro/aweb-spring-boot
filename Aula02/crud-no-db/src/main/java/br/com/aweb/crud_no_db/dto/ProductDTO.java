package br.com.aweb.crud_no_db.dto;

public class ProductDTO {
    private Long id;
    private String name;
    private Double price;

    //construtor------------------------------
    public ProductDTO() {
    }

    //Getter and Setters----------------------
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    
    
}
