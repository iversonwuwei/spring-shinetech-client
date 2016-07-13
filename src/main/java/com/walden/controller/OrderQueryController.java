package com.walden.controller;

import com.walden.action.IQuery;
import com.walden.configure.OrderRequestParams;
import com.walden.service.IService;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;




/**
 * Created by walden on 16/7/5.
 */
@Controller
@Path("/query")
public class OrderQueryController {
    @Qualifier("queryService")
    @Autowired
    private IService queryService;
    @Autowired
    private OrderRequestParams orderRequestParams;
    @Autowired
    private IQuery orderQuery;

    @GET
    @Path("/order")
    @Produces(MediaType.APPLICATION_JSON)
    public String findOrderBy(@QueryParam(value = "id")String id){
        orderRequestParams.setOrderid(id);
        JSONArray jsonArray = (JSONArray) queryService.findBy(orderQuery, orderRequestParams);
        return jsonArray.toString();
    }

    @GET
    @Path("/orders")
    @Produces(MediaType.APPLICATION_JSON)
    public String findOrders(){
        JSONArray jsonArray = (JSONArray) queryService.find(orderQuery);
        return jsonArray.toString();
    }
}