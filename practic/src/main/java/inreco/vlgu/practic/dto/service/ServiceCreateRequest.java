package inreco.vlgu.practic.dto.service;


public class ServiceCreateRequest {
    private String name;
    private String description;
    private Double price;
    private Integer type_id;

    public ServiceCreateRequest(String name, String description, Double price, Integer type_id) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.type_id = type_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getType_id() {
        return type_id;
    }

    public void setType_id(Integer type_id) {
        this.type_id = type_id;
    }
}