package com.example.demo.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;

/**
 * Created by taozeran on 2020/4/1/21:28
 */
@Order(1)
@WebFilter(filterName = "log filter",value = {"/*"})
@Slf4j
public class LogFilter implements Filter {
    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain filterChain) throws IOException, ServletException {
        //ServletInputStream is = req.getInputStream();
        //String encoding = req.getCharacterEncoding();
        //byte[] buffer = new byte[1024];
        //StringBuilder sb = new StringBuilder();
        //while (is.read(buffer)!=-1) {
        //    String s = new String(buffer, Charset.forName(encoding));
        //    sb.append(s);
        //}
        //log.info("req info: {}", sb.toString());
        filterChain.doFilter(req, res);
    }
}
