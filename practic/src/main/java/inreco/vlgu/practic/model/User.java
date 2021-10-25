package inreco.vlgu.practic.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "user",schema = "public")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    @Column(name = "username", unique = true)
    private String username;
    @Column(name = "password",nullable = false)
    private String password;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(	name = "user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

    @OneToMany (fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id")
    @JsonBackReference
    private List<Car> cars;

    @OneToMany (fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id")
    @JsonBackReference
    private List<Request> clientRequests;

    @OneToMany (fetch = FetchType.LAZY)
    @JoinColumn(name = "master_id")
    @JsonBackReference
    private List<Request> masterRequests;

    public User() {
    }

    public User( String username, String password) {
        this.username = username;
        this.password = password;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public List<Car> getCars() {
        return cars;
    }

    public void setCars(List<Car> cars) {
        this.cars = cars;
    }

    public List<Request> getClientRequests() {
        return clientRequests;
    }

    public void setClientRequests(List<Request> clientRequests) {
        this.clientRequests = clientRequests;
    }

    public List<Request> getMasterRequests() {
        return masterRequests;
    }

    public void setMasterRequests(List<Request> masterRequests) {
        this.masterRequests = masterRequests;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}

