package com.zby.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author XBird
 * @date 2021/7/10
 **/
@Api(value = "自动生成代码配置类")
@RestController
public class AutoGeneratorController {
    @GetMapping(value = "/fusssy")
    public String fusssy() {
        return "fusssy";
    }

    @ApiOperation(value = "生成代码并下载")
    @PostMapping(value = "/codeGenerate", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public void codeGenerate() {

    }
}
