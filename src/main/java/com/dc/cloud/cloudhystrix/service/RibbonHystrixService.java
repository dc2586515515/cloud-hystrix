package com.dc.cloud.cloudhystrix.service;

import com.dc.cloud.cloudhystrix.vo.User;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * @Description
 *   Hystrix业务类：RibbonHystrixService.java，
 *  使用@HystrixCommand注解指定当该方法发生异常时调用的方法
 * @Author DC
 * @Date 2019-11-05
 */
@Service
public class RibbonHystrixService {
    @Autowired
    private RestTemplate restTemplate;
    private static final Logger LOGGER = LoggerFactory.getLogger(RibbonHystrixService.class);

    /**
     * 使用@HystrixCommand注解指定当该方法发生异常时 转而 调用的方法
     * @param id
     * @return
     */
    @HystrixCommand(fallbackMethod = "fallback")
    public User findById(Long id){
        return restTemplate.getForObject("http://microservice-provider-user/"+id,
                User.class);
    }

    public User fallback(Long id){
        RibbonHystrixService.LOGGER.info("异常发生，进入fallback方法，接收的参数：id = {}", id);
        User user = new User();
        user.setId(-1L);
        user.setUsername("default username");
        user.setAge(0);
        return user;
    }
}
