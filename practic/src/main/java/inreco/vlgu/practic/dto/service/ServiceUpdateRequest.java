package inreco.vlgu.practic.dto.service;


import javax.validation.constraints.NotBlank;

public class ServiceUpdateRequest {
    @NotBlank
    private Long id;

    @NotBlank
    private Double price;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}