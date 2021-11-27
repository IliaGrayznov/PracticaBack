package inreco.vlgu.practic.model;

import javax.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "author")
public class Author{
    @Id
    private int id;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User id;
    private String status;
}