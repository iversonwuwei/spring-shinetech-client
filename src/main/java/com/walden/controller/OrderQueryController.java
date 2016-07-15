package com.walden.controller;

import com.walden.action.IQuery;
import com.walden.configure.OrderRequestParams;
import com.walden.entity.OrderEntity;
import com.walden.enumeration.ActionEnum;
import com.walden.service.IService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.util.*;


/**
 * Created by walden on 16/7/5.
 */
@Controller
@Path("/query")
public class OrderQueryController {
    private Iterator<OrderEntity> iterator;
    private Map<String, Object> hashMap;

    @Qualifier("queryService")
    @Autowired
    private IService queryService;
    @Autowired
    private OrderRequestParams orderRequestParams;

    @Qualifier("orderQuery")
    @Autowired
    private IQuery orderQuery;

    @GET
    @Path("/order")
    @Produces(MediaType.APPLICATION_JSON)
    public String findOrderBy(@QueryParam(value = "id")String id){
        orderRequestParams.setActionEnum(ActionEnum.orderQuery);
        orderRequestParams.setOrderid(id);
        JSONArray jsonArray = (JSONArray) queryService.findBy(orderQuery, orderRequestParams);
        return jsonArray.toString();
    }
    @GET
    @Path("/orderbyowner")
    @Produces(MediaType.APPLICATION_JSON)
    public ModelAndView findOrderByOwner(@QueryParam(value = "owner")String owner){
        List<OrderEntity> orders = new ArrayList<OrderEntity>();
        orderRequestParams.setActionEnum(ActionEnum.ownerOrderQuery);
        orderRequestParams.setOwner(owner);
        JSONArray jsonArray = (JSONArray) queryService.findBy(orderQuery, orderRequestParams);
        iterator = jsonArray.iterator();
        while (iterator.hasNext()){
            JSONObject jsonObject = JSONObject.fromObject(iterator.next());
            OrderEntity orderEntity = (OrderEntity) JSONObject.toBean(jsonObject, OrderEntity.class);
            orders.add(orderEntity);
        }
        hashMap = new HashMap<String, Object>();
        hashMap.put("orders", jsonArray.toString());
        return new ModelAndView("orderShow", "orders", hashMap);
    }

    @GET
    @Path("/orders")
    @Produces(MediaType.APPLICATION_JSON)
    public String findOrders(){
        JSONArray jsonArray = (JSONArray) queryService.find(orderQuery);
        return jsonArray.toString();
    }
}