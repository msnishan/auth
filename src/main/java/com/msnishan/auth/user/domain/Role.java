package com.msnishan.auth.user.domain;

import com.msnishan.auth.base.domain.BaseEntity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Entity
@Table(name = "AUTH_ROLE")
public class Role extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "ROLE_ID")
    @NotNull(message = "role id can not be null")
    private String roleId;

    @Column(name = "ROLE_DESCRIPTION")
    private String description;

    public Role() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Role role = (Role) o;
        return Objects.equals(id, role.id) &&
                Objects.equals(roleId, role.roleId) &&
                Objects.equals(description, role.description);
    }

    @Override
    public int hashCode() {

        return Objects.hash(super.hashCode(), id, roleId, description);
    }

    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", roleId='" + roleId + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
