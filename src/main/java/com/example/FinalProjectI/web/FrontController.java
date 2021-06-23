package com.example.FinalProjectI.web;

import com.example.FinalProjectI.web.command.Action;
import com.example.FinalProjectI.web.command.ActionFactory;
import com.example.FinalProjectI.web.command.HomeAction;
import com.example.FinalProjectI.web.view.View;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "FrontController", value = "/pages/*")
public class FrontController extends HttpServlet {
    private static final Logger LOGGER = LogManager.getLogger(FrontController.class);
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {
         
            View view = new View(req, resp);
            Action action = ActionFactory.getInstance().getAction(req);
            action.execute(view);
            view.navigate();
        } catch (Exception e) {
            LOGGER.error(e);


        }
    }
}
