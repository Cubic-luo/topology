/**
 * Copyright (c) 2005-2012 Fiberhome Technologies.
 */
package web;

import entity.TopoMessage;
import entity.Topology;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import service.impl.TopologyServiceImpl;

import javax.servlet.http.HttpServletRequest;

/**
 * 拓扑控制类
 */
@Controller
@RequestMapping("/")
public class TopologyController {
    private final Logger logger= LoggerFactory.getLogger(this.getClass());
    @Autowired
    TopologyServiceImpl topoService;

    /**
     * 跳转到首页
     *
     * @return
     */
    @RequestMapping()
    public String index() {
        return "index";
    }

    /**
     * 参数在线配置，按照指定条件构建拓扑
     *
     * @return
     */
    @RequestMapping(value = "topologys", method = RequestMethod.POST, produces = {"application/json;charset=utf-8"})
    @ResponseBody
    public Topology creatTopology(TopoMessage topoMessage) {
        logger.info("在线配置拓扑参数的方式-构建拓扑开始");
        return topoService.calculation(topoMessage);
    }

    /**
     * 参数以文件形式上传，按照指定条件构建拓扑
     *
     * @return
     */
    @RequestMapping(value = "topologys2", method = RequestMethod.POST, produces = {"application/json;charset=utf-8"})
    @ResponseBody
    public Topology creatTopology2(@RequestPart("configFile") MultipartFile configFile, HttpServletRequest request) {
        logger.info("上传配置文件的方式-构建拓扑开始");
        TopoMessage topoMessage = topoService.fileConfig(configFile, request);
        return topoService.calculation(topoMessage);
    }
}
