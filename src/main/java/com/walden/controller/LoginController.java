package com.walden.controller;

import com.walden.action.IQuery;
import com.walden.entity.UserEntity;
import com.walden.service.IService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.*;

/**
 * Created by walden on 16/7/13.
 */
@Controller
@RequestMapping("/user")
public class LoginController {
    private JSONArray jsonArray;
    private List<UserEntity> userList;
    private Iterator<UserEntity> iterator;
    private UserEntity userEntity;
    private Map<String, Object> model;

    @Autowired
    private IService userService;
    @Qualifier("userQuery")
    @Autowired
    private IQuery query;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(){
        jsonArray = (JSONArray) userService.find(query);
        userList = new ArrayList<UserEntity>();
        iterator = jsonArray.iterator();
        while(iterator.hasNext()){
            JSONObject jsonObject = JSONObject.fromObject(iterator.next());
            userList.add((UserEntity) JSONObject.toBean(jsonObject, UserEntity.class));
        }
        System.out.println(userList.size());
        return "login";
    }

    @RequestMapping("/registry")
    public ModelAndView registry(UserEntity userEntity){
        model = new HashMap<String, Object>();
        this.userEntity = userEntity;
        for (UserEntity user : userList){
            if (user.getUser_name().equals(userEntity.getUser_name())
                    && user.getUser_password().equals(userEntity.getUser_password())){
                model.put("user", userEntity);
                return new ModelAndView("loginSuccess", "user", model);
            }
            break;
        }
        model.put("error","please try again!");
        return new ModelAndView("login", "error", model);
    }
}
