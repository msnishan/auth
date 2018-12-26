package com.msnishan.auth.user.domain;

import com.msnishan.auth.base.domain.BaseEntity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "AUTH_GRANT")
public class Grant extends BaseEntity {

    @Column(name = "GRANT_ID", nullable = false, unique = true)
    private String grantId;

    @Column(name = "DESCRIPTION")
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "GROUP_ID", referencedColumnName = "GROUP_ID")
    private Group group;

    @OneToMany(
            mappedBy = "grant",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private Set<UserGrant> userGrants = new HashSet<>();

    public Grant(Long id) {
        super(id);
    }

    public Grant() {
    }

    public String getGrantId() {
        return grantId;
    }

    public void setGrantId(String grantId) {
        this.grantId = grantId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public void addUserGrant(UserGrant userGrant) {
        userGrants.add(userGrant);
        userGrant.setGrant(this);
    }

    public void removeUserGrant(UserGrant userGrant) {
        userGrants.remove(userGrant);
        userGrant.setUser(null);
    }

    @Override
    public String toString() {
        return "Grant{" +
                "grantId='" + grantId + '\'' +
                ", description='" + description + '\'' +
                ", group=" + group +
                '}';
    }
}
