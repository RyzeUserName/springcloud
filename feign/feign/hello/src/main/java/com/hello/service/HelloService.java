package com.hello.service;

import com.hello.config.FeignConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "githubSearch", url = "https://api.github.com", configuration = FeignConfig.class)
public interface HelloService {
    @GetMapping("/search/repositories")
    String search(@RequestParam("q") String s);
}
