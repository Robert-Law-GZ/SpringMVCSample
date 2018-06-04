package com.robert.interceptor;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.JSONObject;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.support.HttpSessionHandshakeInterceptor;

import javax.servlet.http.HttpSession;
import java.util.Map;

public class HandshakeInterceptor extends HttpSessionHandshakeInterceptor{
    private static Log log= LogFactory.getLog(HandshakeInterceptor.class);
    @Override
    public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Map<String, Object> attributes) throws Exception {
        log.info("-----websocket----握手开始");

        if (request instanceof ServletServerHttpRequest) {
            ServletServerHttpRequest serverHttpRequest = (ServletServerHttpRequest) request;
            Map<String, String[]> map = serverHttpRequest.getServletRequest().getParameterMap();
            HttpSession session = serverHttpRequest.getServletRequest().getSession();
            session.setAttribute("name",serverHttpRequest.getServletRequest().getParameter("name"));
            JSONObject json=new JSONObject(map);
            log.error("=========\n"+json.toString(4));
        }

        return super.beforeHandshake(request, response, wsHandler, attributes);
    }

    @Override
    public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Exception ex) {
        log.info("-----websocket----握手结束");
        super.afterHandshake(request, response, wsHandler, ex);
    }

}
