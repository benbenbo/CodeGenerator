package com.zby.web.model.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author XBird
 * @date 2021/8/2
 **/
@Data
@ApiModel("代码生成请求类")
public class CodeGenerateRequest {
    @NotBlank(message = "数据库链接不能为空")
    @ApiModelProperty(value = "数据库链接", example = "jdbc:mysql://localhost:3306/eblog?useUnicode=true&useSSL=false&characterEncoding=utf8", required = true)
    private String jdbcUrl;

    @NotBlank(message = "数据库驱动不能为空")
    @ApiModelProperty(value = "数据库驱动", example = "com.mysql.jdbc.Driver", required = true)
    private String driverName;

    @NotBlank(message = "数据库用户名不能为空")
    @ApiModelProperty(value = "数据库用户名", example = "root", required = true)
    private String userName;

    @NotBlank(message = "数据库密码不能为空")
    @ApiModelProperty(value = "数据库密码", example = "123456", required = true)
    private String password;

    @ApiModelProperty(value = "表前缀", example = "m_")
    private String tablePrefix;

    @NotBlank(message = "作者不能为空")
    @ApiModelProperty(value = "作者", example = "zhengbingyuan", required = true)
    private String author;

    @NotBlank(message = "父级包名不能为空")
    @ApiModelProperty(value = "父级包名", example = "com.baomidou.ant")
    private String parentPackage;

    @NotBlank(message = "模块名不能为空")
    @ApiModelProperty(value = "模块名", example = "module", required = true)
    private String moduleName;

    @NotBlank(message = "服务类包名不能为空")
    @ApiModelProperty(value = "服务类包名", example = "service", required = true)
    private String serviceName;

    @NotBlank(message = "实体类包名不能为空")
    @ApiModelProperty(value = "实体类包名", example = "model", required = true)
    private String modelPackage;

    @NotBlank(message = "mapper类包名不能为空")
    @ApiModelProperty(value = "mapper类包名", example = "dao", required = true)
    private String mapperPackage;

    @NotBlank(message = "mapper的xml包名不能为空")
    @ApiModelProperty(value = "mapper的xml包名", example = "mapper", required = true)
    private String mapperXmlPackage;

    @NotBlank(message = "表名不能为空")
    @ApiModelProperty(value = "表名，可用逗号分隔", example = "m_user", required = true)
    private String tableNames;
}
