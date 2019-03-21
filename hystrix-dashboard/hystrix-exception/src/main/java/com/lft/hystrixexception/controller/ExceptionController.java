package com.lft.hystrixexception.controller;

import com.lft.hystrixexception.service.PSFallbackBadRequestExpcetion;
import com.lft.hystrixexception.service.PSFallbackOtherExpcetion;
import com.lft.hystrixexception.service.ProviderServiceCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 描述
 * @author Ryze
 * @date 2019/3/20 22:10
 */
@RestController
public class ExceptionController {
    private Logger log = LoggerFactory.getLogger(this.getClass());

    @GetMapping("/getProviderServiceCommand")
    public String providerServiceCommand() {
        String result = new ProviderServiceCommand("World").execute();
        return result;
    }


    @GetMapping("/getPSFallbackBadRequestExpcetion")
    public String providerServiceFallback() {
        String result = new PSFallbackBadRequestExpcetion().execute();
        return result;
    }


    @GetMapping("/getPSFallbackOtherExpcetion")
    public String pSFallbackOtherExpcetion() {
        String result = new PSFallbackOtherExpcetion().execute();
        return result;
    }

    @GetMapping("/getFallbackMethodTest")
    @HystrixCommand(fallbackMethod = "fallback")
    public String getFallbackMethodTest(String id) {
        throw new RuntimeException("getFallbackMethodTest failed");
    }

    public String fallback(String id, Throwable throwable) {
        log.error(throwable.getMessage());
        return "this is fallback message";
    }
}
