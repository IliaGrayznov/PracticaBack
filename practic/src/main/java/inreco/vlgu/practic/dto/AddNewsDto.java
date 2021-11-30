package inreco.vlgu.practic.dto;

import lombok.Data;

@Data
public class AddNewsDto {
    private String preview;
    private String content;
    private int cat_id;
    private String token;
    private  boolean hot;
}
