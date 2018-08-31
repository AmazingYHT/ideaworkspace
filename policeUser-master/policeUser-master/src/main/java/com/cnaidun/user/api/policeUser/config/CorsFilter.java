package com.cnaidun.user.api.policeUser.config;

import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 项目名称：PoliceCloud
 * 类名称：CorsFilter
 * 类描述：
 * 创建人：JackJun
 * 创建时间：2018/7/25
 * 修改人：JackJun
 * 修改时间：2018/7/25
 * 修改备注：
 * 版权所有权：江苏艾盾网络科技有限公司
 *
 * @version V1.0
 */
@Component
public class CorsFilter implements Filter {

    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletResponse response = (HttpServletResponse) res;
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
        response.setHeader("Access-Control-Max-Age", "3600");

        response.setHeader("Access-Control-Allow-Headers","token,Origin, X-Requested-With, Content-Type, Accept");
        System.out.println("*********************************过滤器被使用**************************");

        chain.doFilter(req, res);
    }
    public void init(FilterConfig filterConfig) {}
    public void destroy() {}
}

