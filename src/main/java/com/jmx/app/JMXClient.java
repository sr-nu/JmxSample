package com.jmx.app;

import javax.management.AttributeNotFoundException;
import javax.management.InstanceNotFoundException;
import javax.management.IntrospectionException;
import javax.management.MBeanAttributeInfo;
import javax.management.MBeanException;
import javax.management.MBeanServer;
import javax.management.MBeanServerFactory;
import javax.management.MalformedObjectNameException;
import javax.management.ObjectName;
import javax.management.ReflectionException;
import java.lang.management.ManagementFactory;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;


// JMX client that queries all MBean servers and prints values all attributes in all MBeans

public class JMXClient {
    public static void main(String[] args) throws MalformedObjectNameException, IntrospectionException, InstanceNotFoundException, ReflectionException, AttributeNotFoundException, MBeanException {
        final ObjectName name = new ObjectName("com.jmxtest:type=JmxDemo");
        MBeanServer platformMBeanServer = ManagementFactory.getPlatformMBeanServer();
        MBeanAttributeInfo[] attributes = platformMBeanServer.getMBeanInfo(name).getAttributes();

        for (MBeanAttributeInfo attribute : attributes) {
            System.out.println(attribute.getName()+ " : " + platformMBeanServer.getAttribute(name, attribute.getName()).toString());
        }
    }



    public static void getAllBeans(String[] args) throws IntrospectionException, InstanceNotFoundException, ReflectionException {

        final List<MBeanServer> servers = new LinkedList<MBeanServer>();
        servers.add(ManagementFactory.getPlatformMBeanServer());
        servers.addAll(MBeanServerFactory.findMBeanServer(null));

        System.out.println(String.format("There are %d MBean Servers accessible", servers.size()));
        for (MBeanServer server : servers) {
            System.out.println(server);
        }


        for (MBeanServer server : servers) {

            System.out.println("MBeans in Server :  "+server);
            System.out.println("+++++++++++++++++++++");


            Set<ObjectName> objectNames = server.queryNames(null, null);

            System.out.println("MBeans found : " + objectNames.size());
            System.out.println("----------------------");

            for (ObjectName objectName : objectNames) {
                System.out.println("Mbean Name :" + objectName);
            }

            for (ObjectName objectName : objectNames) {
                MBeanAttributeInfo[] attributes = server.getMBeanInfo(objectName).getAttributes();
                for (MBeanAttributeInfo attribute : attributes) {
                    try {
                        Object value = server.getAttribute(objectName, attribute.getName());
                        System.out.println(String.format("MBean Name: %s       Attribute Name: %s    Attribute type:%s     Attribute value: %s", objectName.toString(), attribute.getName(), attribute.getType().toString(), value.toString()));
                    } catch (Exception e) {
                        System.out.println(e);
                    }
                }
            }
        }

    }
}
