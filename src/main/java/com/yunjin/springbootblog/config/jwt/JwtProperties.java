package com.yunjin.springbootblog.config.jwt;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@Setter
@Getter
@ConfigurationProperties("jwt") // application.yml 설정 파일 프로퍼티값을 가져와서 사용하는 애너테이션
public class JwtProperties {

    private String issuer;
    private String secretKey;
}
