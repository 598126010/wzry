package com.bbs.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletRequest;
import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@WebListener
public class RequestListener implements ServletRequestListener {
    @Override
    public void requestDestroyed(ServletRequestEvent servletRequestEvent) {

    }

    @Override
    public void requestInitialized(ServletRequestEvent sre) {
        //获取请求对象
        HttpServletRequest request = (HttpServletRequest) sre.getServletRequest();
        //获取请求ip
        String ip = request.getRemoteAddr();
        System.out.println(ip);
        ServletContext sc = sre.getServletContext();
        //从ServletContext域中获取ipMap对象
        Map<String, List<HttpSession>> ipMap = (Map<String, List<HttpSession>>) sc.getAttribute("ipMap");
        //获取当前key为该ip的list对象
        List<HttpSession> list = ipMap.get(ip);

        if(list==null){
            list = new LinkedList<>();
        }

        //获取当前请求所关联的session
        HttpSession currentSession = request.getSession();

        //遍历List,如果存在请求所关联的session对象，则说明是同一个回话中的请求
        for (HttpSession s : list) {
             if(s == currentSession){
                 return;
             }
        }
        //当上面的条件不满足的时候，说明ip创建了一个新的session对象，需要将该对象放入list
        list.add(currentSession);
        //将list添加到map中
        ipMap.put(ip,list);
        //将ipMap重新放到ServletContext域中
        sc.setAttribute("ipMap",ipMap);

        //经ip放到session域中
        currentSession.setAttribute("ip",ip);

    }
}
