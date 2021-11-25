package inreco.vlgu.practic.model;


import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;

@Entity
@Table(name = "car")
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "number", unique = true)
    private String number;

    @Column(name = "mark")
    private String mark;

    @Column(name = "model")
    private String model;

    @ManyToOne
    @JsonManagedReference
    private User owner;

    public Car() {
    }

    public Car(Long id, String number, String mark, String model, User user) {
        this.id = id;
        this.number = number;
        this.mark = mark;
        this.model = model;
        this.owner = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

   /* @Override
    public String toString() {
        return  "Car{" +
                "id=" + id +
                ", number='" + number + '\'' +
                ", mark='" + mark + '\'' +
                ", model='" + model + '\'' +
                '}';
    }*/

}