/**
 * Copyright (c) 2005-2012 Fiberhome Technologies.
 */
package entity;

public class TopoMessage {
    private int nodeNum;//节点数量
    private String topoName;//拓扑类型名称
    private String outputType;//输出结构化数据类型
    private String traversalStarts;//指定起点,可多选
    /*
    relations，储存节点之间的关系,第一位储存父节点，
    其余各位储存其子节点，目前暂时未用
    备用于对前端的扩展支持，后续可以在前端指定各个节点的名称及其字节点
    后端可根据此信息构建指定网络
     */
    private String[] relations;

    public int getNodeNum() {
        return nodeNum;
    }

    public void setNodeNum(int nodeNum) {
        this.nodeNum = nodeNum;
    }

    public String getTopoName() {
        return topoName;
    }

    public void setTopoName(String topoName) {
        this.topoName = topoName;
    }

    public String getOutputType() {
        return outputType;
    }

    public void setOutputType(String outputType) {
        this.outputType = outputType;
    }

    public String getTraversalStarts() {
        return traversalStarts;
    }

    public void setTraversalStarts(String traversalStarts) {
        this.traversalStarts = traversalStarts;
    }

    public String[] getRelations() {
        return relations;
    }

    public void setRelations(String[] relations) {
        this.relations = relations;
    }
}
