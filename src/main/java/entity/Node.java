/**
 * Copyright (c) 2005-2012 Fiberhome Technologies.
 */
package entity;

/**
 * 设备实体类
 */

public class Node {
    private int id;
    private String node;//node名称
    private String color;//标记是否访问过，gray表示访问过，white表示未访问过

    public Node() {
    }

    public Node(int id, String node, String color) {
        this.id = id;
        this.node = node;
        this.color = color;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNode() {
        return node;
    }

    public void setNode(String node) {
        this.node = node;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return "Node{" +
                "id=" + id +
                ", node='" + node + '\'' +
                ", color='" + color + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Node)) return false;

        Node node1 = (Node) o;

        if (id != node1.id) return false;
        if (node != null ? !node.equals(node1.node) : node1.node != null) return false;
        return color != null ? color.equals(node1.color) : node1.color == null;
    }

}
