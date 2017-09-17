/**
 * Copyright (c) 2005-2012 Fiberhome Technologies.
 */
package service.impl;

import entity.Graph;
import entity.Node;
import entity.TopoMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import service.TopoMessageService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class TopoMessageServiceImpl implements TopoMessageService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public Graph creatGraph(TopoMessage topoMessage) {
        String topoName = topoMessage.getTopoName().toLowerCase();
        int nodeNum = topoMessage.getNodeNum();

        /**
         * 判断拓扑参数中是否指定节点关系
         * 1.存在：按照节点关系构造图
         * 2.不存在：按照拓扑名字构造默认图
         * 此用于后续扩展功能
         */
        if (topoMessage.getRelations() != null) {
            //TODO
            return null;
        } else {
            switch (topoName) {
                case "chain":
                    return creatChainGraph(nodeNum);
                case "ring":
                    return creatRingGraph(nodeNum);
                case "mesh":
                    return creatMeshGraph(nodeNum);
                default:
                    return null;
            }
        }
    }

    /**
     * 生成默认全Mesh拓扑图
     *
     * @param nodeNum 节点数量
     * @return
     */

    private Graph creatMeshGraph(int nodeNum) {
        logger.info("生成默认mesh拓扑");

        //生成指定数量的node节点
        List<Node> nodeList = new ArrayList<>();
        for (int i = 0; i < nodeNum; i++) {
            Node node = new Node(i, "node" + i, "WHITE");
            nodeList.add(node);
        }

        //构建相邻关系
        Map<Node, List<Node>> neighbors = new HashMap<>();
        for (int i = 0; i < nodeList.size(); i++) {
            Node n = nodeList.get(i);
            List<Node> result = nodeList.stream().filter(node -> node != n).collect(Collectors.toList());
            neighbors.put(n, result);
        }
        Graph graph = new Graph(nodeList, neighbors);
        return graph;
    }

    /**
     * 生成默认Chain拓扑图
     *
     * @param nodeNum 指定节点数量
     * @return
     */
    private Graph creatChainGraph(int nodeNum) {
        logger.info("生成默认chain");

        //生成指定数量的node节点
        List<Node> nodeList = new ArrayList<>();
        for (int i = 0; i < nodeNum; i++) {
            Node node = new Node(i, "node" + i, "WHITE");
            nodeList.add(node);
        }

        //构建相邻关系
        Map<Node, List<Node>> neighbors = new HashMap<>();
        for (int i = 0; i < nodeList.size(); i++) {
            List<Node> result = new ArrayList<>();
            Node n = nodeList.get(i);
            if (i != 0 && i != nodeList.size() - 1) {
                result.add(nodeList.get(i - 1));
                result.add(nodeList.get(i + 1));
                neighbors.put(n, result);
            } else if (i == 0) {
                result.add(nodeList.get(i + 1));
                neighbors.put(n, result);
            } else if (i == (nodeList.size() - 1)) {
                result.add(nodeList.get(i - 1));
                neighbors.put(n, result);
            }
        }
        Graph graph = new Graph(nodeList, neighbors);
        return graph;
    }

    /**
     * 生成默认Ring拓扑图
     *
     * @param nodeNum
     * @return
     */
    private Graph creatRingGraph(int nodeNum) {
        logger.info("生成默认ring");

        //生成指定数量的node节点
        List<Node> nodeList = new ArrayList<>();
        for (int i = 0; i < nodeNum; i++) {
            Node node = new Node(i, "node" + i, "WHITE");
            nodeList.add(node);
        }

        //构建相邻关系
        Map<Node, List<Node>> neighbors = new HashMap<>();
        for (int i = 0; i < nodeList.size(); i++) {
            List<Node> result = new ArrayList<>();
            Node n = nodeList.get(i);
            if (i != 0 && i != nodeList.size() - 1) {
                result.add(nodeList.get(i - 1));
                result.add(nodeList.get(i + 1));
                neighbors.put(n, result);
            } else if (i == 0) {
                result.add(nodeList.get(i + 1));
                result.add(nodeList.get(nodeList.size() - 1));
                neighbors.put(n, result);
            } else if (i == nodeList.size() - 1) {
                result.add(nodeList.get(i - 1));
                result.add(nodeList.get(0));
                neighbors.put(n, result);
            }
        }
        Graph graph = new Graph(nodeList, neighbors);
        return graph;
    }
}
