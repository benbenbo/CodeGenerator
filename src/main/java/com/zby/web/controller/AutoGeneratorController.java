package com.zby.web.controller;

import cn.hutool.core.util.ZipUtil;
import com.zby.service.CodeGenerateService;
import com.zby.web.model.request.CodeGenerateRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

/**
 * @author XBird
 * @date 2021/7/10
 **/
@Api(tags = "自动生成代码配置类")
@RestController
@RequiredArgsConstructor
public class AutoGeneratorController {
    private final CodeGenerateService codeGenerateService;

    @ApiOperation(value = "生成代码并下载", notes = "郑炳元")
    @PostMapping(value = "/codeGenerate", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public HttpEntity codeGenerate(@RequestBody @Validated CodeGenerateRequest request) throws IOException {
        String projectPath = System.getProperty("user.dir");
        String outputDir = projectPath + File.separator + "temp" + File.separator + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss", Locale.CHINA));
        request.setOutputDir(outputDir);
        codeGenerateService.execute(request, outputDir);
//        String packageName = request.getParentPackage() + "." + request.getModuleName();
        String srcPath = outputDir + File.separator + request.getParentPackage().substring(0, request.getParentPackage().indexOf("."));
        String fileName = "code.zip";
        String zipPath = outputDir + File.separator + fileName;
        ZipUtil.zip(srcPath, zipPath);
        download(zipPath, fileName);
        return new ResponseEntity(HttpStatus.OK);
    }


    public void download(String filePath, String fileName) throws IOException {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletResponse response = requestAttributes.getResponse();
        try (InputStream is = new FileInputStream(filePath)) {
            response.reset();
            response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName, "utf-8"));
            response.addHeader("Content-Length", "" + is.available());
            response.setContentType("application/zip");
            IOUtils.copy(is, response.getOutputStream());
        }
    }
}
