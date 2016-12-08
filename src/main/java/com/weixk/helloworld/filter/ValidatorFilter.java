package com.weixk.helloworld.filter;

import com.sun.xml.internal.messaging.saaj.util.ByteInputStream;
import com.sun.xml.internal.messaging.saaj.util.ByteOutputStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import java.io.*;

/**
 *
 * Created by weixk on 16/12/6.
 */
public class ValidatorFilter implements Filter {
    private static final Logger log = LoggerFactory.getLogger(ValidatorFilter.class);
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("ValidatorFilter初始化");
    }


    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        log.info("====================before validator======================");
//        HttpServletResponse httpResponse = (HttpServletResponse) response;
//        TempResponseWrapper wrapper = new TempResponseWrapper(httpResponse);
//        log.info("重写response");
        log.info("====================before validator======================");
//        chain.doFilter(request, wrapper);
        chain.doFilter(request, response);
        log.info("====================after validator======================");
//        String content = wrapper.toString();
//        log.info(content);
//        response.getWriter().println(content);
        log.info("====================after validator======================");
    }

    @Override
    public void destroy() {
        log.info("ValidatorFilter销毁");
    }

    private class TempResponseWrapper extends HttpServletResponseWrapper {
        private ByteOutputStream out;
        private ServletOutputStream outputStream;
        public TempResponseWrapper(HttpServletResponse response) {
            super(response);
            out = new ByteOutputStream();
            outputStream = new ServletOutputStream() {
                @Override
                public boolean isReady() {
                    return true;
                }

                @Override
                public void setWriteListener(WriteListener listener) {

                }

                @Override
                public void write(int b) throws IOException {
                    out.write(b);
                }
            };
        }

        @Override
        public PrintWriter getWriter() throws IOException {
            log.info("getWriter()");
            return super.getWriter();
        }

        @Override
        public String toString() {
            return out.toString();
        }

        @Override
        public ServletOutputStream getOutputStream() throws IOException {
            log.info("getOutputStream()");
            return outputStream;
        }
    }
}