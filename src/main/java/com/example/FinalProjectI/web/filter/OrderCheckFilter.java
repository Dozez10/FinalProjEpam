package com.example.FinalProjectI.web.filter;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(filterName = "OrderCheckFilter")
public class OrderCheckFilter implements Filter {
    public void init(FilterConfig config) throws ServletException {
    }

    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {

        HttpServletRequest req = (HttpServletRequest)request;
        HttpServletResponse res = (HttpServletResponse)response;
        HttpSession session = req.getSession();

        if(session.getAttribute("Login")!=null && ((String)session.getAttribute("userType")).compareToIgnoreCase("client")==0) {

            chain.doFilter(request, response);
          }
     else {
            String mess ;
            if(session.getAttribute("Login")!=null){
                mess="userType";
            }
            else
            {
                mess="NotEntered";
            }

        res.sendRedirect(req.getContextPath() + req.getServletPath() + "/index?lang=" + session.getAttribute("lang")+"&pageNumber="+req.getParameter("pageN")+"&errorMessageTo="+mess);
        }

    }
}
