package com.robert.util;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import java.io.IOException;
import java.io.PrintWriter;

public class MyResponseWrapper extends HttpServletResponseWrapper {
    private MyWriter myWriter;

    /**
     * Constructs a response adaptor wrapping the given response.
     *
     * @param response
     * @throws IllegalArgumentException if the response is null
     */
    public MyResponseWrapper(HttpServletResponse response) {
        super(response);
    }

    @Override
    public PrintWriter getWriter() throws IOException {
        myWriter = new MyWriter(super.getWriter());
        return myWriter;
    }

    public MyWriter getMyWriter() {
        return myWriter;
    }

}
