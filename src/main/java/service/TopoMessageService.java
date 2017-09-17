/**
 * Copyright (c) 2005-2012 Fiberhome Technologies.
 */
package service;

import entity.Graph;
import entity.TopoMessage;

/**
 * 拓扑配置参数处理服务接口
 */
public interface TopoMessageService {
    /**
     * 按照拓扑类型生成图
     * @param topoMessage 构建拓扑的参数信息
     * @return
     */
   Graph creatGraph(TopoMessage topoMessage);
}
