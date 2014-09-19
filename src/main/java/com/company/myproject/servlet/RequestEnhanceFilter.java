package com.company.myproject.servlet;

import java.io.IOException;
import java.util.Date;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Wraps the Http Request in our {@link EnhancedHttpRequest} class to provide additional info
 * including a request id, which is used by the messaging system
 */
public class RequestEnhanceFilter implements Filter{
    
    
    
    public void init(FilterConfig filterConfig) throws ServletException{
    }

    public void doFilter(ServletRequest req, ServletResponse response, FilterChain chain)
            throws IOException, ServletException{
        EnhancedHttpRequest request = new EnhancedHttpRequest((HttpServletRequest) req);
        
        String requestId = request.getHeader("request-id"); 
        if(requestId != null){
        	((HttpServletResponse) response).addHeader("Request-Id", requestId);
            try{
                request.setRequestId(Long.parseLong(requestId));
            }catch (NumberFormatException e) {}
        }
        if(request.getRequestId() == -1){
            long timestamp = new Date().getTime();
            request.setRequestId(timestamp);
        }
        
        chain.doFilter(request, response);
    }

    public void destroy(){
    }

}