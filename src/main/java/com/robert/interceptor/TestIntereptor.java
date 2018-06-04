package com.robert.interceptor;

import com.alibaba.fastjson.JSON;
import com.robert.model.ResponseData;
import com.robert.util.JWTUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.JSONObject;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

public class TestIntereptor implements HandlerInterceptor {

    private static Log log= LogFactory.getLog(TestIntereptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token=request.getHeader("token");

        if (token!=null){
            log.info("token:"+token);

            if(JWTUtil.verifyToken(token)){
                return true;
            }
        }

        response.setContentType("application/json; charset=utf-8");

        PrintWriter writer = response.getWriter();
        ResponseData responseData=new ResponseData();
        responseData.setCode(403);
        responseData.setMsg("Token失效");
        responseData.setData(new HashMap<>());
        String rsp=JSON.toJSONString(responseData);
        writer.print(rsp);
        writer.close();

        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.err.println("======post:");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.err.println("======after");
    }
}
