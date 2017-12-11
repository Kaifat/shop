package edu.karazin.shop.model;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class BaseEntity {
 
    private boolean deleted;
}
