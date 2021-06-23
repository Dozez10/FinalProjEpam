package com.example.FinalProjectI.web.command;

import com.example.FinalProjectI.db.DAOFactory;
import com.example.FinalProjectI.db.OrderDao;
import com.example.FinalProjectI.db.mysql.MySQLDAOFactory;
import com.example.FinalProjectI.services.SalonServiceOrder;
import com.example.FinalProjectI.web.view.View;

import javax.servlet.http.HttpServletRequest;
/**
 * Apply order
 * @author Ivan Manuilenko
 */
public class ApplyOrderAction implements Action{
    @Override
    public void execute(View view) throws Exception {
        HttpServletRequest req = view.getRequest();
        DAOFactory daoFactory = (MySQLDAOFactory) req.getServletContext().getAttribute("factoryDao");
        OrderDao orderDao = daoFactory.getOrderDao();
        SalonServiceOrder salonServiceOrder = new SalonServiceOrder(orderDao);
        salonServiceOrder.updateOrderAppliedStatus(Integer.parseInt(req.getParameter("orderId")),true);
        view.setView(req.getContextPath()+"/pages/admin/records?pageNumber=1&lang="+req.getSession().getAttribute("lang"));
    }
}
