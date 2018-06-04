package com.robert.filter;

import com.robert.util.MyResponseWrapper;
import com.robert.util.MyWriter;

import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class TestFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {
        MyResponseWrapper responseWrapper = new MyResponseWrapper((HttpServletResponse) response);
        chain.doFilter(request, responseWrapper);
        MyWriter myWriter = responseWrapper.getMyWriter();

        if (myWriter != null) {
            String content = myWriter.getContent();
            System.out.println("content="+content);
        }

    }

    @Override
    public void init(FilterConfig arg0) throws ServletException {
    }

    @Override
    public void destroy() {

    }

}
