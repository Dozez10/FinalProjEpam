package com.example.FinalProjectI.web.command;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

public class ActionFactory {
    private static ActionFactory actionFactory;
    private Map<String,Action> actions;
    private ActionFactory(){
      actions = new HashMap<>();
      actions.put("GET/index",new HomeAction());
      actions.put("GET/guest/registration",new RegistrationAction());
      actions.put("POST/guest/makeRegistration",new DoRegistrationAction());
      actions.put("POST/guest/login",new LogInAction());
       actions.put("POST/user/logOut",new LogoutAction());
      // actions.put("GET/admin/records",new GetRecordsAction());
      //  actions.put("GET/admin/recordsJson",new GetRecordsAxiosAction());
       actions.put("GET/guest/masterRegistration",new MasterRegistrationAction());
       actions.put("POST/guest/masterMRegistration",new MasterMRegistration());

    }
    public static synchronized ActionFactory getInstance(){
        if(actionFactory == null) {actionFactory = new ActionFactory();}
        return actionFactory;
    }
    public Action getAction(HttpServletRequest request)
    {
        return  actions.get(request.getMethod() + request.getPathInfo());
    }
}
