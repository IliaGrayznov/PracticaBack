package inreco.vlgu.practic.dto;

import lombok.Data;

@Data

public class UpdateNewsDto {
    private String preview;
    private String content;
    private int cat_id;
    private  int id;

}
