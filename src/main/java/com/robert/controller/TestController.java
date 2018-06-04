package com.robert.controller;

import com.alibaba.fastjson.JSON;
import com.robert.dao.ServersDAO;
import com.robert.entity.EmployeeEntity;
import com.robert.entity.ServersEntity;
import com.robert.model.ResponseData;
import com.robert.model.User;
import com.robert.model.Zoo;
import com.robert.util.JWTUtil;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.*;

import org.json.JSONObject;

@Controller
@Scope("prototype")
@RequestMapping(value = "",produces = "application/json;charset=UTF-8")
public class TestController extends BaseController {
    private static Log Log = LogFactory.getLog(TestController.class);
    @Autowired
    private Zoo zoo;
    @Autowired
    private ServersDAO serversDAO;

    @RequestMapping("/test")
    public String test() {
        return "success";
    }

    @RequestMapping(
            value = "/object2json",
            method = RequestMethod.GET,
            produces = "application/json;charset=UTF-8")
    @ResponseBody
    public User getUser() {
        User user = new User();
        user.setName("Robert");
        user.setAge(40);
        return user;
    }

    @RequestMapping(value = "/dao",method = RequestMethod.GET)
    @ResponseBody
    public ResponseData testDAO(){
        List<ServersEntity> list=serversDAO.findAll();
        Log.error("===============================\n"+list.toString());

        ResponseData responseData = new ResponseData();
        responseData.setData(list);
        return responseData;
    }

    @RequestMapping(
            value = "/login",
            method = RequestMethod.POST,
            produces = "application/json;charset=UTF-8")
    @ResponseBody
    public ResponseData login(@RequestParam("username") String username,
                              @RequestParam("password") String password) {
        User user = new User();
        user.setName("Robert");
        user.setAge(40);
        user.setId(UUID.randomUUID().toString());

        user.setToken(JWTUtil.token(user.getId()));
        Log.info("UserName:" + username);
        Log.info("Password:" + password);
        Log.info("Token:" + user.getToken());

        ResponseData responseData = new ResponseData();
        responseData.setData(user);
        return responseData;
    }

    @RequestMapping(value = "/loginInit")
    public String initLogin(Model model){
        EmployeeEntity ee=new EmployeeEntity();
        ee.setUsername("罗强");
        ee.setPassword("123456");
        model.addAttribute("employee",ee);
        return "login";
    }

    @RequestMapping(
            value = "/map2json",
            method = RequestMethod.POST,
            produces = "application/json;charset=UTF-8")
    @ResponseBody
    public Map getMap() {
        Map map = new HashMap();
        map.put("msg", "请求成功");
        map.put("code", 0);
        List<User> users = new ArrayList();
        User user = new User();
        user.setAge(45);

        user.setName("Cindy");

        users.add(user);
        user = new User();
        user.setAge(45);
        user.setName("Cathy");

        users.add(user);
        user = new User();
        user.setAge(45);
        user.setName("Tony");

        users.add(user);
        user = new User();
        user.setAge(45);
        user.setName("Jason");

        users.add(user);
        user = new User();
        user.setAge(45);
        user.setName("John");

        users.add(user);
        user = new User();
        user.setAge(45);
        user.setName("Richard");

        users.add(user);

        Map userList = new HashMap();
        userList.put("userList", users);

        map.put("data", userList);
        return map;
    }

    @RequestMapping(
            value = "/postReq",
            produces = "application/json;charset=UTF-8")
    public String postTest() {
        return "success";
    }

    @RequestMapping(value = "http", method = RequestMethod.GET)
    @ResponseBody
    public Map testOkHttp() {
        OkHttpClient client = new OkHttpClient();
        String url = "https://api.github.com/";

        Request request = new Request.Builder().url(url).build();

        try {
            Response response = client.newCall(request).execute();
            Map map = JSON.parseObject(response.body().string(), Map.class);
            JSONObject obj = new JSONObject(map);
            Log.error("\n" + obj.toString(4));
            return map;
        } catch (IOException e) {
            Log.error(e.toString());
            e.printStackTrace();
        }

        return new HashMap();
    }

}
