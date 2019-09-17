package com.bbs.listener;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.util.List;
import java.util.Map;

public class SessionListener implements HttpSessionListener {
    @Override
    public void sessionCreated(HttpSessionEvent httpSessionEvent) {

    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        HttpSession currentSession = se.getSession();
        ServletContext sc = currentSession.getServletContext();

        Map<String, List<HttpSession>> ipMap = (Map<String, List<HttpSession>>) sc.getAttribute("ipMap");
        //获取ip
        String ip = (String) currentSession.getAttribute("ip");
        List<HttpSession> sessionList = ipMap.get(ip);

        //因为已经监听到session销毁所以从list中移除当前的currentSession
        sessionList.remove(currentSession);

        //如果list的长度是0,则说明ip所发出的对象全部失效
        if(sessionList.size() ==0){
            ipMap.remove(ip);
        }else{
            ipMap.put(ip,sessionList);
        }
        sc.setAttribute("ipMap",ipMap);
    }
}
