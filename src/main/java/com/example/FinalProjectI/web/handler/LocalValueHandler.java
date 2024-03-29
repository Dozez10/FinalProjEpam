package com.example.FinalProjectI.web.handler;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Locale;
import java.util.ResourceBundle;

public class LocalValueHandler extends SimpleTagSupport {
    private static final Logger LOGGER = LogManager.getLogger(LocalValueHandler.class);
    private String key;
    public void setKey(String key) {
        this.key = key;
    }
    @Override
    public void doTag()  {
        /*This is just to display a message, when
         * we will use our custom tag. This message
         * would be displayed
         */
        JspWriter out = getJspContext().getOut();
        PageContext pageContext = (PageContext)getJspContext();
        HttpServletRequest request = (HttpServletRequest)pageContext.getRequest();
        HttpSession session = request.getSession();
        Locale locale = (Locale)session.getAttribute("tagLocale");
        ResourceBundle bundle = ResourceBundle.getBundle((String) session.getAttribute("Bundle"),locale);
        try {

            out.print(new String(bundle.getString(key).getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8));
        } catch (IOException e) {
            LOGGER.error(e);
        }

    }
}
