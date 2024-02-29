package com.zhl.dt.gateway.controller;

import com.alibaba.nacos.shaded.com.google.common.collect.ImmutableMap;
import com.zhl.dt.util.rest.RestResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Tag(name = "服务检测")
@Slf4j
@RestController
@RequestMapping("/support/")
public class CheckController {

    public static Map<String,String> map = new ConcurrentHashMap<>();

    @ResponseBody
    @RequestMapping(value="check")
    public RestResponse<String> check(){
        if(map!=null && map.size()>0){
            return RestResponse.error(HttpStatus.NOT_FOUND.getReasonPhrase());
        }else{
            return RestResponse.ok(HttpStatus.OK.getReasonPhrase());
        }
    }

    @ResponseBody
    @RequestMapping(value="online")
    public Map<String, String> online(){
        log.info("应用上线，流量开始切入....");
        map.clear();
        return responseOk();
    }

    @ResponseBody
    @RequestMapping(value="offline")
    public Map<String, String> offline(){
        log.info("应用下线，流量不再切入....");
        map.put("status", "offline");
        return responseOk();
    }

    public static Map<String, String> responseOk() {
        return ImmutableMap.of("code", "0", "msg", "ok");
    }

}
