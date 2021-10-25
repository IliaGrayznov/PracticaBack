package inreco.vlgu.practic.model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
@Table(name = "car")
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "number", unique = true)
    private String number;

    @Column(name = "mark")
    private String mark;

    @Column(name = "model")
    private String model;

    @ManyToOne
    @JsonIgnoreProperties(value = { "cars" ,"hibernateLazyInitializer", "handler" }, allowSetters = true)
    @JsonBackReference
    private User owner;

    public Car() {
    }

    public Car(Integer id, String number, String mark, String model, User user) {
        this.id = id;
        this.number = number;
        this.mark = mark;
        this.model = model;
        this.owner = user;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User user) {
        this.owner = user;
    }
}