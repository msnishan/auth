package com.msnishan.auth.user.domain;

import com.msnishan.auth.base.domain.BaseEntity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "AUTH_GROUP")
public class Group extends BaseEntity {

    @Column(name = "GROUP_ID", nullable = false, unique = true)
    private String groupId;

    @Column(name = "DESCRIPTION", nullable = false)
    private String description;

    @OneToMany(
            mappedBy = "group",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            orphanRemoval = true
    )
    private Set<Grant> grants = new HashSet<>();

    public Group(Long id) {
        super(id);
    }

    public Group() {
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<Grant> getGrants() {
        return grants;
    }

    public void addGrant(Grant grant) {
        grants.add(grant);
        grant.setGroup(this);
    }

    public void removeGrant(Grant grant) {
        grants.remove(grant);
        grant.setGroup(null);
    }

    @Override
    public String toString() {
        return "Group{" +
                "groupId='" + groupId + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
