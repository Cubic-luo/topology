/**
 * Copyright (c) 2005-2012 Fiberhome Technologies.
 */
package entity;

/**
 * 记录遍历路径
 */
public class Traversal {
    private String start;//起点
    private String breadth;//宽度，node1->node2->node3
    private String depth;//广度，node1->node2->node3

    public Traversal() {
    }

    public Traversal( String start, String breadth, String depth) {
        this.start = start;
        this.breadth = breadth;
        this.depth = depth;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getBreadth() {
        return breadth;
    }

    public void setBreadth(String breadth) {
        this.breadth = breadth;
    }

    public String getDepth() {
        return depth;
    }

    public void setDepth(String depth) {
        this.depth = depth;
    }

    @Override
    public String toString() {
        return "Traversal{" +
                "start='" + start + '\'' +
                ", breadth='" + breadth + '\'' +
                ", depth='" + depth + '\'' +
                '}';
    }
}
