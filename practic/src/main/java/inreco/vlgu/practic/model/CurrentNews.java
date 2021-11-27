package inreco.vlgu.practic.model;

import javax.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "currentnews")
public class CurrentNews{
    @Id
    private int id;
    @ManyToOne
    @JoinColumn(name = "cat_id")
    private User user;
    @ManyToOne
    @JoinColumn(name = "news_id")
    private News news;
    @ManyToOne
    @JoinColumn(name = "author_id")
    private Author author;
}
