package com.mydemo.demo.bean;

/**
 *
 * @author ME
 */
public class Website {
    
    private int id;
    private String name;
    private String value;
    private String remark;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "Website{" + "id=" + id + ", name=" + name + ", value=" + value + ", remark=" + remark + '}';
    }
    
}




