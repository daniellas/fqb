package com.lynx.fqb.entity;

import java.util.Date;

import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;

import lombok.Getter;
import lombok.Setter;

@MappedSuperclass
public class EntityBase {
    @Getter
    @Setter
    private Date dateCreate;

    @PrePersist
    public void prePersist() {
        dateCreate = new Date();
    }
}
