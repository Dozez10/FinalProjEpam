package com.example.FinalProjectI.web.command;

import com.example.FinalProjectI.web.view.View;

import javax.servlet.http.HttpServletRequest;

public class RegistrationAction implements Action{
    @Override
    public void execute(View view) throws Exception {
        HttpServletRequest request = view.getRequest();
        view.setView(request.getPathInfo());
    }
}
