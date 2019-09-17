package com.bbs.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.http.*;
import java.util.HashMap;
import java.util.List;

@WebListener
public class ServletContextListener implements javax.servlet.ServletContextListener,
        HttpSessionListener, HttpSessionAttributeListener {

    @Override
    public void contextInitialized(ServletContextEvent sre) {
        //map的key存放ip,value存放该ip地址生成的session对象的集合
        HashMap<String, List<HttpSession>> ipMap = new HashMap<>();
        //将ipMap放到ServletContext域中
        ServletContext servletContext = sre.getServletContext();
        servletContext.setAttribute("ipMap",ipMap);

    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }

    @Override
    public void attributeAdded(HttpSessionBindingEvent httpSessionBindingEvent) {

    }

    @Override
    public void attributeRemoved(HttpSessionBindingEvent httpSessionBindingEvent) {

    }

    @Override
    public void attributeReplaced(HttpSessionBindingEvent httpSessionBindingEvent) {

    }

    @Override
    public void sessionCreated(HttpSessionEvent httpSessionEvent) {

    }

    @Override
    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {

    }
}
