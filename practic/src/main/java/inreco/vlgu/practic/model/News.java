package inreco.vlgu.practic.model;

import javax.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "news")
public class News {
    @Id
    private int id;
    private String preview;
    private String content;
}