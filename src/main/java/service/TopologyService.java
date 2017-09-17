/**
 * Copyright (c) 2005-2012 Fiberhome Technologies.
 */
package service;


import entity.TopoMessage;
import entity.Topology;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

/**
 * topology构建服务接口
 */
public interface TopologyService {
    /**
     * 计算topology
     * @param topoMessage 拓扑配置信息
     * @return
     */
    Topology calculation(TopoMessage topoMessage);

    /**
     * 若通过上传配置文件构造，则调用此方法处理配置文件
     * @param configFile
     */
    TopoMessage fileConfig(MultipartFile configFile,HttpServletRequest request);
}
