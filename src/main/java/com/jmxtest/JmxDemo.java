package com.jmxtest;

public class JmxDemo implements JmxDemoMBean {
    private String name ="default name";
    private int value = 999999;

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setValue(int value) {
        this.value = value;
    }

    @Override
    public int getValue() {
        return value;
    }
}
