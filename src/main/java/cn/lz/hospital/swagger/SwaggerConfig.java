package cn.lz.hospital.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author zhuwc
 * @ClassName SwaggerConfig
 * @Description TODO SwaggerConfig api 配置
 * @Date 2019/1/16 10:12
 * @Version 1.0
 **/
@EnableWebMvc
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket swaggerSpringMvcPlugin() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .enable(true)
                .groupName("lz-hospital-service-api")
                .select()   // 选择那些路径和api会生成document
                .apis(RequestHandlerSelectors.basePackage("cn.lz.hospital.controller"))
                .paths(PathSelectors.any())   // 对所有路径进行监控
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("HIS API")
                .termsOfServiceUrl("http://221.210.124.26:8089/")
                .description("此API提供接口调用")
                .version("1.0").build();
    }
}
