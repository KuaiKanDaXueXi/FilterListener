package com.xian.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * @author Xian
 */
@WebListener
public class ListenerDemo1 implements ServletContextListener {
    /**
     * 监听ServletContext初始化执行方法
     * @param sce
     */
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        //加载资源文件（全局的文件）
        //1.获取ServletContext对象
        ServletContext servletContext = sce.getServletContext();
        //2，加载资源文件
        String xxx = servletContext.getInitParameter("xxx");
        //3.获取真实路径
        String realPath = servletContext.getRealPath(xxx);
        try {
            //4.加载进内存
            FileInputStream fis = new FileInputStream(realPath);
            System.out.println(fis);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * 监听ServletContext销毁执行方法
     * @param sce
     */
    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }
}
