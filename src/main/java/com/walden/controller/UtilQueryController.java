package com.walden.controller;

import com.walden.action.IQuery;
import com.walden.action.implement.query.UtilQuery;
import com.walden.service.IService;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * Created by walden on 16/7/13.
 */
@Controller
@Path("/query")
public class UtilQueryController {

    @Autowired
    private IService utilService;

    @Qualifier(value = "utilQuery")
    @Autowired
    private IQuery utilQuery;

    @GET
    @Path("/utils")
    @Produces(MediaType.APPLICATION_JSON)
    public JSONArray findUtils(){
        JSONArray jsonArray = (JSONArray) utilService.find(utilQuery);
        return jsonArray;
    }
}
