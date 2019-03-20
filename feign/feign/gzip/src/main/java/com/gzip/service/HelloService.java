package com.gzip.service;

import com.gzip.config.FeignConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "githubSearch", url = "https://api.github.com", configuration = FeignConfig.class)
public interface HelloService {
    @GetMapping("/search/repositories")
    ResponseEntity<byte[]> search(@RequestParam("q") String s);
}
