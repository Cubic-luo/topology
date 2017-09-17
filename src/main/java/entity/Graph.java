/**
 * Copyright (c) 2005-2012 Fiberhome Technologies.
 */
package entity;

import java.util.*;

/**
 * 储存节点之间的相邻关系
 */
public class Graph {
    private List<Node> nodeList = new ArrayList<>();//记录所有节点
    private Map<Node, List<Node>> neighBors = new HashMap<>();//记录邻居节点

    public Graph() {
    }

    public Graph(List<Node> nodeList, Map<Node, List<Node>> neighBors) {
        this.nodeList = nodeList;
        this.neighBors = neighBors;
    }

    public List<Node> getNodeList() {
        return nodeList;
    }

    public void setNodeList(List<Node> nodeList) {
        this.nodeList = nodeList;
    }

    public Map<Node, List<Node>> getNeighBors() {
        return neighBors;
    }

    public void setNeighBors(Map<Node, List<Node>> neighBors) {
        this.neighBors = neighBors;
    }

    @Override
    public String toString() {
        return "Graph{" +
                "nodeList=" + nodeList +
                ", neighBors=" + neighBors +
                '}';
    }
}
