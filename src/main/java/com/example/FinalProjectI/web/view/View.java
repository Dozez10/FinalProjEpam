package com.example.FinalProjectI.web.view;


import org.apache.commons.io.FilenameUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class View {
    private HttpServletRequest request;
    private HttpServletResponse response;
    private String view;

    public HttpServletRequest getRequest() {
        return request;
    }

    public HttpServletResponse getResponse() {
        return response;
    }

    public String getView() {
        return view;
    }
    public void setView(String view) {
        this.view = view;
    }
    public View(HttpServletRequest request, HttpServletResponse response){
        this.request = request;
        this.response = response;
    }
    public void navigate() throws IOException, ServletException {

        if (view.equals(request.getPathInfo())) {
            request.getRequestDispatcher("/WEB-INF/view/" + FilenameUtils.getName(view) + ".jsp").forward(request, response);
        }
        else {
            response.sendRedirect(view);
        }
    }
}
