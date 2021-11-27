package inreco.vlgu.practic.model;

import javax.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "current_news")
public class Current-news{
    @Id
    private int id;
    @ManyToOne
    @JoinColumn(name = "cat_id")
    private User id;
    @ManyToOne
    @JoinColumn(name = "news_id")
    private News id;
    @ManyToOne
    @JoinColumn(name = "author_id")
    private Author id;
}