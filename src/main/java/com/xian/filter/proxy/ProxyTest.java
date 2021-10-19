package com.xian.filter.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class ProxyTest {
    public static void main(String[] args) {
        //创建真实对象
        final Lenovo lenovo = new Lenovo();
        //动态代理增强Lenovo对象
        /**
         * 三个参数：
         *  1.类加载器：真实对象.getClass().getClassLoader()
         *  2.接口数组：真实对象.getClass().getInterfaces()
         *  3.处理器：new InvocationHandler()
         */
        SaleComputer proxy = (SaleComputer) Proxy.newProxyInstance(lenovo.getClass().getClassLoader(), lenovo.getClass().getInterfaces(), new InvocationHandler() {
            /**
             * 代理逻辑编写的方法：代理对象的所有方法都会触发该方法执行
             * @param proxy 代理对象
             * @param method 代理对象调用的方法，被封装为的对象
             * @param args
             * @return
             * @throws Throwable
             */
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                if ("sale".equals(method.getName())) {
                    /* 增强参数类型 */
                    double a = 0.85 * ((Double) args[0]);
                    return method.invoke(lenovo,a);
                }
                return method.invoke(lenovo,args);
            }
        });

        String sale = proxy.sale(8000.0);
        System.out.println(sale);
    }
}
