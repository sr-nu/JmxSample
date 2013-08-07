package com.jmx.app;

import com.jmxtest.Manage;
import com.jmxtest.ManageMBean;

import javax.management.InstanceAlreadyExistsException;
import javax.management.MBeanRegistrationException;
import javax.management.MBeanServer;
import javax.management.MalformedObjectNameException;
import javax.management.NotCompliantMBeanException;
import javax.management.ObjectName;
import java.lang.management.ManagementFactory;

public class JMXRunner {
    public static void main(String[] args) throws MalformedObjectNameException, NotCompliantMBeanException, InstanceAlreadyExistsException, MBeanRegistrationException, InterruptedException {
        ManageMBean manage = new Manage();

        MBeanServer platformMBeanServer = ManagementFactory.getPlatformMBeanServer();
        ObjectName name = new ObjectName("com.jmxtest:type=ManageMBean");
        platformMBeanServer.registerMBean(manage, name);

        Thread.sleep(Integer.MAX_VALUE);
        //You can use JConsole to view/update MBean

    }
}
