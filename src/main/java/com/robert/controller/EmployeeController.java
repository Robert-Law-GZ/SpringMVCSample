package com.robert.controller;

import com.robert.dao.EmployeeDAO;
import com.robert.entity.EmployeeEntity;
import com.robert.model.ResponseData;
import com.robert.model.RspList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@Scope("prototype")
@RequestMapping(value = "/test",
        produces = "application/json;charset=UTF-8")
public class EmployeeController {
    @Autowired
    private EmployeeDAO employeeDAO;

    /**
     * 成功列表接口
     * @return   返回成员列表
     */
    @RequestMapping(value = "/employeeList",
            method = RequestMethod.GET)
    @ResponseBody
    public Object employeeList() {
        ResponseData responseData = new ResponseData();
        RspList rl = new RspList(employeeDAO.findAll());
        responseData.setData(rl);
        return responseData;
    }

    /**
     * 添加成员接口
     * @param username  用户名
     * @param password  密码
     * @return   Employee对象
     */
    @RequestMapping(value = "/addEmployee",
            method = RequestMethod.POST)
    @ResponseBody
    public Object addEmployee(
            @RequestParam(name = "username") String username,
            @RequestParam(name = "password") String password) {
        ResponseData responseData = new ResponseData();
        EmployeeEntity employeeEntity = new EmployeeEntity();
        employeeEntity.setId((int) System.currentTimeMillis());
        employeeEntity.setPassword(password);
        employeeEntity.setUsername(username);
        responseData.setData(employeeEntity);
        employeeDAO.add(employeeEntity);
        return responseData;
    }

}
