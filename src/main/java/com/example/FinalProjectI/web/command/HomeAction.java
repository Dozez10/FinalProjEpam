package com.example.FinalProjectI.web.command;

import com.example.FinalProjectI.db.DAOFactory;
import com.example.FinalProjectI.db.MasterDao;
import com.example.FinalProjectI.db.MasterServiceDao;
import com.example.FinalProjectI.db.ServiceDao;
import com.example.FinalProjectI.db.entity.Master;
import com.example.FinalProjectI.db.entity.MasterService;
import com.example.FinalProjectI.db.entity.Service;
import com.example.FinalProjectI.db.exception.CustomApplicationException;
import com.example.FinalProjectI.db.mysql.MySQLDAOFactory;
import com.example.FinalProjectI.services.SalonServiceMaster;
import com.example.FinalProjectI.services.SalonServiceMasterService;
import com.example.FinalProjectI.services.SalonServiceOrder;
import com.example.FinalProjectI.services.SalonServiceService;
import com.example.FinalProjectI.web.view.View;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class HomeAction implements Action{
    private static final Logger LOGGER = LogManager.getLogger(HomeAction.class);
    @Override
    public void execute(View view) throws Exception {
        //sortBY[rating,masterName],orderingType[ASC|DESC],filterBYName filterBYService pageNumber
        HttpServletRequest request  = view.getRequest();
        String sortBY = request.getParameter("sortBY");
        String orderingType = request.getParameter("orderingType");
        String filterBYName = request.getParameter("filterBYName");
        String filterBYService = request.getParameter("filterBYService");

        int pageNumber = 1;
        int rowsPerPage = 6;
        int offset;
        int count;
        double tempCount;
        if(request.getParameter("pageNumber")!=null) {
            try {
                pageNumber = Integer.parseInt(request.getParameter("pageNumber"));

            } catch (NumberFormatException numberFormatException) {
                LOGGER.error(numberFormatException);
            }
        }
        if(pageNumber<=0)pageNumber = 1;

       if(sortBY==null||(sortBY.compareToIgnoreCase("rating")!=0&&sortBY.compareToIgnoreCase("masterName")!=0))
        {

            sortBY = "rating";


        }
        if(orderingType==null||(orderingType.compareToIgnoreCase("ASC")!=0&&orderingType.compareToIgnoreCase("DESC")!=0))
        {

            orderingType = "ASC";

        }

        offset = (pageNumber-1)*rowsPerPage;



        DAOFactory factory = (MySQLDAOFactory)request.getServletContext().getAttribute("factoryDao");

        MasterDao masterDao = factory.getMasterDao();
        MasterServiceDao masterServiceDao = factory.getMasterServiceDao();
        ServiceDao serviceDao = factory.getServiceDao();

        SalonServiceMaster salonServiceMaster = new SalonServiceMaster(masterDao);
        SalonServiceMasterService salonServiceMasterService = new SalonServiceMasterService(masterServiceDao);
        SalonServiceService salonServiceService = new SalonServiceService(serviceDao);

        List<Master> masterList;
       if(filterBYName!=null && filterBYService!=null)
       {

           masterList = salonServiceMaster.findMastersFilterByServiceByName(sortBY,orderingType,filterBYName,filterBYService,rowsPerPage,offset);
           count  = salonServiceMaster.findMastersFilterByServiceByNameCount(sortBY,orderingType,filterBYName,filterBYService);
           request.setAttribute("filterName",filterBYName);
           request.setAttribute("filterService",filterBYService);
       }
       else if(filterBYService!=null)
       {

           masterList = salonServiceMaster.findMastersFilterByService(sortBY,orderingType,filterBYService,rowsPerPage,offset);
           count = salonServiceMaster.findMastersFilterByServiceCount(sortBY,orderingType,filterBYService);
              request.setAttribute("filterService",filterBYService);
       }
      else if(filterBYName!=null)
      {

          masterList = salonServiceMaster.findMastersFilterByName(sortBY,orderingType,filterBYName,rowsPerPage,offset);
          count = salonServiceMaster.findMastersFilterByNameCount(sortBY,orderingType,filterBYName);
          request.setAttribute("filterName",filterBYName);
      }
      else
      {

          masterList = salonServiceMaster.findMastersOrdered(sortBY,orderingType,rowsPerPage,offset);
          count = salonServiceMaster.findMastersOrderedCount(sortBY,orderingType);

      }


        Map<Integer,List<Service>> servicesMap = new HashMap<>();
        List<Service> serviceList;

        for (Master master:masterList)
        {
            serviceList = new ArrayList<>();
            for(MasterService masterService:salonServiceMasterService.findAllServicesByMaster(master.getMasterId()))
            {
                serviceList.add(salonServiceService.findServiceById(masterService.getServiceId()));
            }
            servicesMap.put(master.getMasterId(),serviceList);
        }

        tempCount = count/6d;
        count = (int)tempCount;

        if(tempCount >count)
            count++;

        request.setAttribute("sort",sortBY);
        request.setAttribute("orderType",orderingType);
        request.setAttribute("pageNumber",pageNumber);
        request.setAttribute("pagesCount",count);
        request.setAttribute("mastersList",masterList);
        request.setAttribute("servicesMap",servicesMap);
        view.setView(request.getPathInfo());

    }
}
