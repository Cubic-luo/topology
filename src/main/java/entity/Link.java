/**
 * Copyright (c) 2005-2012 Fiberhome Technologies.
 */
package entity;

/**
 * 链路信息实体类
 */
public class Link {
    private String source;//链路起点，填设备名称
    private String target;//链路终点，填设备名称

    public Link() {
    }

    public Link(String source, String target) {
        this.source = source;
        this.target = target;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }


    @Override
    public String toString() {
        return "Link{" +
                "source='" + source + '\'' +
                ", target='" + target + '\'' +
                '}';
    }
}
