package com.example.FinalProjectI.web.filter;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(filterName = "OrderFilter")
public class OrderFilter implements Filter {
    public void init(FilterConfig config) throws ServletException {
    }

    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        if(req.getParameter("startTime").equals("")||req.getParameter("endTime").equals(""))
        {
            String message = "timeNot";
            res.sendRedirect(req.getContextPath()+req.getServletPath()+"/client/makeRecord?lang="+req.getParameter("lang")+"&masterId="+req.getParameter("masterId")+"&userId="+req.getParameter("userId")+"&errorMessageTo="+message);
        }
        else {

        chain.doFilter(request, response);
        }
    }
}
