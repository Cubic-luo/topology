/**
 * Copyright (c) 2005-2012 Fiberhome Technologies.
 */
package config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * springmvc根配置
 */
@Configuration
@ComponentScan(basePackages = {"service"})
public class RootConfig {

}
