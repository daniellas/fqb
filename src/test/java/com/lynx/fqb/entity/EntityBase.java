package com.lynx.fqb.entity;

import java.util.Date;

import javax.persistence.MappedSuperclass;

import lombok.Getter;
import lombok.Setter;

@MappedSuperclass
public class EntityBase {
    @Getter
    @Setter
    private Date dateCreate;
}
