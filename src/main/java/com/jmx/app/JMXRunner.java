package com.jmx.app;

import com.jmxtest.JmxDemo;
import com.jmxtest.JmxDemoMBean;

import javax.management.InstanceAlreadyExistsException;
import javax.management.InstanceNotFoundException;
import javax.management.IntrospectionException;
import javax.management.JMX;
import javax.management.MBeanRegistrationException;
import javax.management.MBeanServer;
import javax.management.MalformedObjectNameException;
import javax.management.NotCompliantMBeanException;
import javax.management.ObjectName;
import javax.management.ReflectionException;
import java.lang.management.ManagementFactory;

public class JMXRunner {
    public static void main(String[] args) throws MalformedObjectNameException, NotCompliantMBeanException, InstanceAlreadyExistsException, MBeanRegistrationException, InterruptedException, IntrospectionException, InstanceNotFoundException, ReflectionException {
        JmxDemoMBean manage = new JmxDemo();
        manage.setName("First MBean");
        manage.setValue(1000);

        MBeanServer platformMBeanServer = ManagementFactory.getPlatformMBeanServer();
        final ObjectName name = new ObjectName("com.jmxtest:type=JmxDemo");
        platformMBeanServer.registerMBean(manage, name);

        System.out.println("Bean Registered? " + platformMBeanServer.isRegistered(name) +"\n\n");
        System.out.println("Bean info : " + platformMBeanServer.getMBeanInfo(name).toString() +"\n\n");

        JmxDemoMBean jmxDemo = JMX.newMBeanProxy(platformMBeanServer, name, JmxDemoMBean.class);
        System.out.println(jmxDemo.getName() + jmxDemo.getValue() + "\n\n");


        System.out.println("Server Running...");


        Thread.sleep(Integer.MAX_VALUE);
        //You can use JConsole to view/update MBean

    }
}
