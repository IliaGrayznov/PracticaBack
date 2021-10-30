package inreco.vlgu.practic.dto.request;


import javax.validation.constraints.NotBlank;

public class InputRequestID {
    @NotBlank
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}