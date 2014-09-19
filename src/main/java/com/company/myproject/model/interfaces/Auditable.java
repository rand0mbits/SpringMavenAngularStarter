package com.company.myproject.model.interfaces;

import java.util.Date;

public interface Auditable {
	public void setCreateDate(Date date);
	public Date getCreateDate();
	public void setLastChangeDate(Date date);
	public Date getLastChangeDate();
	public void setCreateUserId(Integer userId);
	public Integer getCreateUserId();
	public void setLastChangeUserId(Integer userId);
	public Integer getLastChangeUserId();
	public void setRecordChangeDate(Date date);
	public Date getRecordChangeDate();
}
