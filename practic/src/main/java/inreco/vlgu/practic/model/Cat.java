package inreco.vlgu.practic.model;

import javax.persistence.*;
import lombok.Data;


@Entity
@Data
@Table(name = "cat")
public class Cat {
    @Id
    private int id;
    private String name;
    private String description;
}