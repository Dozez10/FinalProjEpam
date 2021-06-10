package com.example.FinalProjectI.web.command;

import com.example.FinalProjectI.web.view.View;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class LogoutAction implements Action{
    @Override
    public void execute(View view) throws Exception {
        HttpServletRequest request = view.getRequest();
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.removeAttribute("Login");
            session.invalidate();

        }
        view.setView(request.getContextPath()+"/pages/index");
    }
}
