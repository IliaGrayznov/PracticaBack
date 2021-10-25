package inreco.vlgu.practic.dto;


import javax.validation.constraints.NotBlank;

public class InputRequest {
    @NotBlank
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}