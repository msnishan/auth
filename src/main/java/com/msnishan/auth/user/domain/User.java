package com.msnishan.auth.user.domain;

import com.msnishan.auth.base.domain.BaseEntity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "AUTH_USER")
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "USERNAME")
    @NotNull(message = "username can not be null")
    private String username;

    @Column(name = "PASSWORD")
    @NotNull
    private String password;

    @Column(name = "EMAIL")
    @NotNull
    private String email;

    @Embedded
    @AttributeOverrides(value = {
            @AttributeOverride(name = "address1", column = @Column(name = "ADDRESS_1")),
            @AttributeOverride(name = "address2", column = @Column(name = "ADDRESS_2")),
            @AttributeOverride(name = "address3", column = @Column(name = "ADDRESS_3")),
            @AttributeOverride(name = "city", column = @Column(name = "CITY")),
            @AttributeOverride(name = "pinCode", column = @Column(name = "PIN_CODE")),
            @AttributeOverride(name = "state", column = @Column(name = "STATE")),
            @AttributeOverride(name = "country", column = @Column(name = "COUNTRY"))
    })
    private Address address;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "USER_ROLE", joinColumns = @JoinColumn(name = "USER_ID"), inverseJoinColumns = @JoinColumn(name = "ROLE_ID"))
    private List<Role> roles = new ArrayList<>();

    public User() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) &&
                Objects.equals(username, user.username) &&
                Objects.equals(password, user.password) &&
                Objects.equals(email, user.email) &&
                Objects.equals(address, user.address);
    }

    @Override
    public int hashCode() {

        return Objects.hash(super.hashCode(), id, username, password, email, address);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", address=" + address +
                '}';
    }
}
