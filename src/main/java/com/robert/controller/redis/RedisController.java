package com.robert.controller.redis;

import com.robert.entity.EmployeeEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import redis.clients.jedis.ShardedJedisPool;

@Controller
@RequestMapping(value = "/test",
        produces = "application/json;charset=UTF-8")
public class RedisController {

    @Autowired
    protected ShardedJedisPool shardedJedisPool;

    @RequestMapping(value = "/redis",method = RequestMethod.GET)
    @ResponseBody
    public Object test() {

        EmployeeEntity ee = new EmployeeEntity();
        ee.setPassword("123456 ");
        ee.setUsername("强哥");
        shardedJedisPool.getResource().set("name", ee.getUsername());
        String n = shardedJedisPool.getResource().get("name");
        System.err.println("-----------------\n" + n);

        return ee;
    }

}
