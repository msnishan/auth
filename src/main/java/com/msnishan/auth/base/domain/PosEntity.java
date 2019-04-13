package com.msnishan.auth.base.domain;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;
import java.io.Serializable;
import java.time.LocalDateTime;

@MappedSuperclass
public abstract class PosEntity extends BaseEntity {

    @Column(name = "POS_ID", nullable = false)
    private String posId;

    public PosEntity() {
    }

    public PosEntity(Long id) {
        super(id);
    }


    public void setPosId(String posId) {
        this.posId = posId;
    }

    public String getPosId() {
        return posId;
    }
}
