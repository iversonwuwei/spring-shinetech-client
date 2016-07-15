package com.walden.controller;

import com.walden.action.IQuery;
import com.walden.configure.UserRequestParams;
import com.walden.entity.UserEntity;
import com.walden.enumeration.ActionEnum;
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
    private UserEntity user;
    private Map<String, Object> model;
    private List<UserEntity> users;
    private Iterator<JSONObject> iterator;

    @Autowired
    private IService userService;
    @Qualifier("userQuery")
    @Autowired
    private IQuery query;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login() {

        return "login";
    }

    @RequestMapping(value = "/registry", method = RequestMethod.GET)
    public ModelAndView registry(UserEntity userEntity) {
        users = new ArrayList<UserEntity>();
        model = new HashMap<String, Object>();
        UserRequestParams requestParam = new UserRequestParams();
        requestParam.setActionEnum(ActionEnum.userQuery);
        requestParam.setUsername(userEntity.getUser_name());
        JSONArray jsonArray = (JSONArray) userService.findBy(query, requestParam);
        iterator = jsonArray.iterator();
        while (iterator.hasNext()) {
            JSONObject jsonObject = iterator.next();
            user = (UserEntity) JSONObject.toBean(jsonObject, UserEntity.class);
            if (user.getUser_name().equals(userEntity.getUser_name())
                    && user.getUser_password().equals(userEntity.getUser_password())) {
                model.put("user", userEntity);
                return new ModelAndView("forward:/query/orderbyowner?owner=" + userEntity.getUser_name());
            }
        }
        model.put("error", "please try again!");
        return new ModelAndView("login", "error", model);
    }
}
