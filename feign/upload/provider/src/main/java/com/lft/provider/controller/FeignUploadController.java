package com.lft.provider.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * 描述
 * @author Ryze
 * @date 2019/3/17 13:44
 */
@RestController
public class FeignUploadController {

    @PostMapping(value = "/uploadFile/server", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String fileUploadServer(MultipartFile file ) throws Exception{
        return file.getOriginalFilename();
    }
}
