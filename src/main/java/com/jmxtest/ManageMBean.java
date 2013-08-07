package com.jmxtest;

public interface ManageMBean {
    public void setName(String name);
    public String getName();
    public void setValue(int value);
    public int getValue();
}
