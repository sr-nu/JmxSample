package com.jmxtest;

public interface JmxDemoMBean {
    public void setName(String name);
    public String getName();
    public void setValue(int value);
    public int getValue();
}
