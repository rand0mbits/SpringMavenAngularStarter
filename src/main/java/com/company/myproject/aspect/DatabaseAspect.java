package com.company.myproject.aspect;

import java.util.Date;

import org.apache.log4j.Logger;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

import com.company.myproject.model.interfaces.Auditable;

@Aspect
public class DatabaseAspect {
	private static final Logger LOGGER = Logger.getLogger(DatabaseAspect.class);
	/**
     * Sets last change date and user id to the current user and current time.
     * Also sets the create date/user id if none has been set yet.
     */
    @Before(value="(execution(* com.company.myproject.dao.mappers..*.insert*(com.company.myproject.model.interfaces.Auditable+)) && args(entry))")
    public void insertChangable(Auditable entry){
        Integer activeUserId = null;
        
        Date now = new Date();
        
        if(entry.getCreateDate() == null){
            entry.setCreateDate(now);
        }
        if(entry.getCreateUserId() == null){
            entry.setCreateUserId(activeUserId);
        }
        
        entry.setLastChangeDate(now);
        entry.setLastChangeUserId(activeUserId);
        entry.setRecordChangeDate(now);
    }
    /**
     * Sets last change date and user id to the current user and current time.
     * Also sets the create date/user id if none has been set yet.
     */
    @Before(value="(execution(* com.company.myproject.dao.mappers..*.update*(com.company.myproject.model.interfaces.Auditable+)) && args(entry))")
    public void updateChangable(Auditable entry){
        Date now = new Date();
        entry.setLastChangeDate(now);
//        entry.setLastChangeUserId(user.getUserId());
        entry.setRecordChangeDate(now);
    }
}
