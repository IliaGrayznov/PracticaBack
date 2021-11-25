package inreco.vlgu.practic.model;

import javax.persistence.*;

@Entity
@Table(name = "service_type",schema = "public")
public class ServiceType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @ManyToOne
    ServiceSubType subType;

    public ServiceType() {
    }

    public ServiceType(int id, String name, ServiceSubType subType) {
        this.id = id;
        this.name = name;
        this.subType = subType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ServiceSubType getSubType() {
        return subType;
    }

    public void setSubType(ServiceSubType subType) {
        this.subType = subType;
    }
}

