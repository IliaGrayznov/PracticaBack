package inreco.vlgu.practic.dto.service;


import javax.validation.constraints.NotBlank;

public class ServiceRequestID {
    @NotBlank
    private Long id;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


}