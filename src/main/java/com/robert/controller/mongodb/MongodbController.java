package com.robert.controller.mongodb;

import com.robert.entity.EmployeeEntity;
import com.robert.model.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping(value = "/mongo")
public class MongodbController {
    @Autowired
    @Qualifier("mongoTemplate")
    protected MongoTemplate mongoTemplate;

    @RequestMapping(value = "/addEmployee",produces = "application/json;charset=UTF-8")
    @ResponseBody
    public Object insertEmployee(@RequestParam("name") String name,@RequestParam("password") String password){
        EmployeeEntity ee=new EmployeeEntity();
        ee.setUsername(name);
        ee.setPassword(password);
        int num=(int)(Math.random()*100);
        ee.setId(num);
        mongoTemplate.insert(ee,"employee");
        ResponseData responseData=new ResponseData();
        responseData.setData(ee);
        return responseData;
    }

    @RequestMapping(value = "/employeeList",produces = "application/json;charset=UTF-8")
    @ResponseBody
    public Object findAll(){
        Query query=new Query(Criteria.where("_id").is(6));
//        List<EmployeeEntity> result=mongoTemplate.find(query,EmployeeEntity.class);
        List<EmployeeEntity> result=mongoTemplate.findAll(EmployeeEntity.class);
        ResponseData responseData=new ResponseData();
        responseData.setListData(result);
        return  responseData;
    }

}
