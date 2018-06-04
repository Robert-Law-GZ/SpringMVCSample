package com.robert.common;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.util.HashMap;
import java.util.Map;


@ControllerAdvice
@Component
public class MyResponseBodyAdvice implements ResponseBodyAdvice<Object>{

    private static Logger log = LogManager.getLogger(MyResponseBodyAdvice.class);

    @Override
    public boolean supports(MethodParameter returnType, Class converterType) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {

        log.error("Content Type:"+selectedContentType.getType());

        if (selectedContentType.equals(MediaType.APPLICATION_JSON_UTF8)) {
            JSONObject obj = new JSONObject(body);

            if (body instanceof HashMap) {
                obj = new JSONObject((Map) body);
            }

            ServletServerHttpRequest sshr = (ServletServerHttpRequest) request;
            Map<String, String[]> map = sshr.getServletRequest().getParameterMap();
            JSONObject paramsObj = new JSONObject(map);

            StringBuilder sb=new StringBuilder();
            sb.append("\n");
            sb.append("-----------------------------------------------------------");
            sb.append("-----------------------------------------------------------");
            sb.append("\n");
            sb.append("请求：");
            sb.append(request.getURI().toString());
            sb.append("\n");
            sb.append("方法：");
            sb.append(request.getMethod().name());
            sb.append("\n");
            sb.append("参数：");

            if (request.getMethod()!=HttpMethod.POST){
                if (sshr.getServletRequest().getQueryString()!=null) {
                    sb.append(sshr.getServletRequest().getQueryString());
                }else{
                    sb.append("无参");
                }
            }else{
                sb.append("\n");
                sb.append(paramsObj.toString(4));
            }

            sb.append("\n");
            sb.append("-----------------------------------------------------------");
            sb.append("-----------------------------------------------------------");
            sb.append("\n");
            sb.append("响应:");
            sb.append("\n");
            sb.append(obj.toString(4));
            sb.append("\n");
            sb.append("-----------------------------------------------------------");
            sb.append("-----------------------------------------------------------");

            log.info(sb.toString());
        }

        return body;
    }

}
