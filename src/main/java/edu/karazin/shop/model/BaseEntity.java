package edu.karazin.shop.model;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class BaseEntity {
 
    private boolean deleted;
    
    public boolean getDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}
}
