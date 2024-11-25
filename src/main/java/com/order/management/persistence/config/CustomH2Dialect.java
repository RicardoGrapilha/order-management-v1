package com.order.management.persistence.config;

import org.hibernate.dialect.H2Dialect;

public class CustomH2Dialect extends H2Dialect{
	 @Override
	    public boolean dropConstraints() {
	        return true;
	    }

	    @Override
	    public boolean supportsIfExistsAfterAlterTable() {
	        return true;
	    }

}
