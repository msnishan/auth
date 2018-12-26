package com.msnishan.auth.user.domain;

import com.msnishan.auth.base.domain.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "AUTH_USER_GRANT",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"EMAIL", "GRANT_ID"})})
public class UserGrant extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "EMAIL", referencedColumnName = "EMAIL")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "GRANT_ID", referencedColumnName = "GRANT_ID")
    private Grant grant;

    public UserGrant(Long id) {
        super(id);
    }

    public UserGrant() {
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Grant getGrant() {
        return grant;
    }

    public void setGrant(Grant grant) {
        this.grant = grant;
    }

    @Override
    public String toString() {
        return "UserGrant{" +
                "user=" + user +
                ", grant=" + grant +
                '}';
    }
}
