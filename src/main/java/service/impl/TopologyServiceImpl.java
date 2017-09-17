/**
 * Copyright (c) 2005-2012 Fiberhome Technologies.
 */
package service.impl;


import entity.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import service.TopologyService;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import java.util.concurrent.LinkedBlockingQueue;

@Service
public class TopologyServiceImpl implements TopologyService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private TopoMessageServiceImpl topoMessageService;

    public Topology calculation(TopoMessage topoMessage) {
        String traversalStarts = topoMessage.getTraversalStarts();
        Graph graph = topoMessageService.creatGraph(topoMessage);
        return search(traversalStarts, graph);
    }

    @Override
    public TopoMessage fileConfig(MultipartFile configFile, HttpServletRequest request) {
        logger.info("开始读取上传的配置文件");
        TopoMessage topoMessage = new TopoMessage();

        // 开始读取文件，判断文件是否为空
        String filePath = null;
        if (!configFile.isEmpty()) {
            try {
                // 文件保存路径
                filePath = request.getSession().getServletContext().getRealPath("/") + "resouce/upload/"
                        + System.currentTimeMillis() + configFile.getOriginalFilename();
                // 转存文件
                configFile.transferTo(new File(filePath));
            } catch (Exception e) {
                e.printStackTrace();
            }
            Properties properties = new Properties();
            try {
                InputStream inputStream = new FileInputStream(filePath);
                properties.load(inputStream);
                inputStream.close(); //关闭流
                int nodeNum = Integer.parseInt(properties.getProperty("nodeNum"));
                String TopyType = properties.getProperty("TopyType");
                String outputType = properties.getProperty("outputType");
                String traversalStarts = properties.getProperty("traversalStarts");
                topoMessage.setNodeNum(nodeNum);
                topoMessage.setOutputType(outputType);
                topoMessage.setTopoName(TopyType);
                topoMessage.setTraversalStarts(traversalStarts);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        logger.info("上传的配置文件读取完毕");
        return topoMessage;
    }

    /**
     * 根据指定起点，对指定图进行搜索
     *
     * @param traversalStarts
     * @param graph
     * @return
     */
    private Topology search(String traversalStarts, Graph graph) {
        logger.info("开始搜索");
        logger.info("开始搜索");
        List<Node> nodeList = graph.getNodeList();
        List<Traversal> traversalList = new ArrayList<>();
        List<Link> linkList = new ArrayList<>();
        StringBuilder depSb = new StringBuilder();
        StringBuilder breSb = new StringBuilder();
        Node startNode = null;

        //找到起始节点,支持多个起点
        String[] starArray = traversalStarts.split(",");
        for (String traversalStart : starArray) {
            for (Node node : nodeList) {
                if (node.getNode().equals(traversalStart)) {
                    startNode = node;
                }
            }
            Traversal traversal = new Traversal(traversalStart, null, null);

            //深度优先开始,由于有多个起点，所以要更新node标记
            for (Node n : nodeList) {
                n.setColor("WHITE");
            }
            depth(graph, startNode, depSb);//从指定起点开始
            for (Node node : nodeList) {
                if (node.getColor().equals("WHITE")) {
                    depth(graph, node, depSb);
                }
            }
            traversal.setDepth(depSb.toString());
            depSb.delete(0,depSb.length());

            //广度优先开始
            //恢复初始状态
            for (Node n : nodeList) {
                n.setColor("WHITE");
            }
            breadth(graph, startNode, breSb);//从指定起点开始
            for (Node node : nodeList) {
                if (node.getColor().equals("WHITE")) {
                    depth(graph, node, breSb);
                }
            }
            traversal.setBreadth(breSb.toString());
            breSb.delete(0,breSb.length());
            traversalList.add(traversal);
        }
        //生成link
        linkList = creatLink(graph);
        Topology topology = new Topology(nodeList, linkList, traversalList);
        logger.info("搜索完毕");
        return topology;
    }

    /**
     * Mesh 深度优先
     *
     * @param graph     存储邻接关系的图
     * @param startNode 起始节点
     * @param depSb     搜索的节点名称
     */
    private void depth(Graph graph, Node startNode, StringBuilder depSb) {
        logger.info("深度优先开始");
        startNode.setColor("Gray");//表示该节点被访问过
        //开始生成深度路径
        depSb.append(startNode.getNode() + "->");
        Map<Node, List<Node>> neighBors = graph.getNeighBors();
        List<Node> nodeList = neighBors.get(startNode);//取出此节点的邻居
        if (nodeList != null && nodeList.size() > 0) {
            for (Node node : nodeList) {
                if (node.getColor().equals("WHITE")) {
                    depth(graph, node, depSb);
                }
            }
            startNode.setColor("Black");//表示此节点的邻接节点已搜索完毕
        }
        logger.info("深度优先结束");
    }

    /**
     * Mesh 广度优先
     *
     * @param graph     存储邻接关系的图
     * @param startNode 起始节点
     * @param breSb     搜索的节点名称
     */
    private void breadth(Graph graph, Node startNode, StringBuilder breSb) {
        logger.info("广度优先开始");
        breSb.append(startNode.getNode() + "->");
        Map<Node, List<Node>> neighBors = graph.getNeighBors();
        Queue<Node> store = new LinkedBlockingQueue<>();
        startNode.setColor("Gray");
        store.add(startNode);
        for (int i = 0; i < 1; ) {
            if (store.size() > 0 && store != null) {
                Node node = store.poll();
                List<Node> nodeList = neighBors.get(node);//取出此节点的邻居
                for (Node node1 : nodeList) {
                    if (node1.getColor().equals("WHITE")) {
                        breSb.append(node1.getNode() + "->");
                        node1.setColor("Gray");
                        store.add(node1);
                    }
                }
            } else {
                break;
            }

        }
        logger.info("广度优先结束");
    }

    /**
     * 根据拓扑图生成link
     *
     * @param graph
     * @return
     */
    private List<Link> creatLink(Graph graph) {
        logger.info("开始生成link");
        List<Link> linkList = new ArrayList<>();
        List<Node> nodeList = new ArrayList<>();
        List<Node> neighBors = new ArrayList<>();
        nodeList = graph.getNodeList();
        for (Node node : nodeList) {
            neighBors = graph.getNeighBors().get(node);
            for (Node node1 : neighBors) {
                Link link = new Link(node.getNode(), node1.getNode());
                linkList.add(link);
            }
        }
        logger.info("link生成完毕");
        return linkList;
    }
}
