package com.lft.zuulserver.controller;

import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

/**
 * 描述
 * @author Ryze
 * @date 2019-03-29 9:46
 */
@RestController
public class UploadController {

    @PostMapping("/upload")
    public String upload(@RequestParam("file") MultipartFile file) throws IOException {
        byte[] bytes = file.getBytes();
        File file1 = new File(Objects.requireNonNull(file.getOriginalFilename()));
        FileCopyUtils.copy(bytes, file1);
        return file1.getAbsolutePath();
    }
}
