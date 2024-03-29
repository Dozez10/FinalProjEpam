package com.example.FinalProjectI.web.handler;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;

public class BundleHandler extends SimpleTagSupport {
    private String basename;

    public void setBasename(String basename) {
        this.basename = basename;
    }
    @Override
    public void doTag() {
        PageContext pageContext = (PageContext)getJspContext();
        HttpServletRequest request = (HttpServletRequest)pageContext.getRequest();
        HttpSession session = request.getSession();
        String baseResourceName = basename;
        session.setAttribute("Bundle",baseResourceName);
    }
}
