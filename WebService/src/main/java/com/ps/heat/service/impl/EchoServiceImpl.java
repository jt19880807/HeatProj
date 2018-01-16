package com.ps.heat.service.impl;

import com.ps.heat.service.EchoService;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.jws.WebService;

/**
 * Created by xbc on 2016/8/24.
 */
@RestController
public class EchoServiceImpl implements EchoService {
    @Override
    @RequestMapping("/echo")
    public String echo(@RequestParam(value="valve", defaultValue="00")String content) {
        return content;
    }
}
