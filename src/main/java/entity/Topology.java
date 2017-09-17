/**
 * Copyright (c) 2005-2012 Fiberhome Technologies.
 */
package entity;

import java.util.List;

/**
 * 生成的拓扑，用于输出
 */
public class Topology {
    private List<Node> nodes;
    private List<Link> links;
    private List<Traversal> traversals;

    public Topology() {
    }

    public Topology(List<Node> nodes, List<Link> links, List<Traversal> traversals) {
        this.nodes = nodes;
        this.links = links;
        this.traversals = traversals;
    }

    public List<Node> getNodes() {
        return nodes;
    }

    public void setNodes(List<Node> nodes) {
        this.nodes = nodes;
    }

    public List<Link> getLinks() {
        return links;
    }

    public void setLinks(List<Link> links) {
        this.links = links;
    }

    public List<Traversal> getTraversals() {
        return traversals;
    }

    public void setTraversals(List<Traversal> traversals) {
        this.traversals = traversals;
    }

    @Override
    public String toString() {
        return "Topology{" +
                "nodes=" + nodes +
                ", links=" + links +
                ", traversals=" + traversals +
                '}';
    }
}
