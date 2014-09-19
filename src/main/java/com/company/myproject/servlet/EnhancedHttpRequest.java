package com.company.myproject.servlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;


public class EnhancedHttpRequest extends HttpServletRequestWrapper{
    
    private long requestId = -1L;
    
    public EnhancedHttpRequest(HttpServletRequest request){
        super(request);
    }

    public long getRequestId(){
        return requestId;
    }

    public void setRequestId(long requestId){
        this.requestId = requestId;
    }

}