package com.dongyang.core.global.config.swagger;

import io.swagger.v3.oas.models.servers.Server;
import java.util.Arrays;

import java.util.List;
import org.springdoc.core.utils.SpringDocUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.dongyang.core.global.common.resolver.MemberId;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;

@Configuration
public class SwaggerConfig {
	private final static String TITLE = "Co:Re API Server";
	private final static String DESCRIPTION = "Co:Re API Docs";
	private final static String VERSION = "0.0.1";

	@Bean
	public OpenAPI openAPI() {
		Server serverLocal = createServer("http://localhost:8080", "for local usages");
		Server serverDev = createServer("https://core.pe.kr", "for dev usages");

		Info info = new Info()
			.title(TITLE)
			.description(DESCRIPTION)
			.version(VERSION);

		SecurityScheme securityScheme = new SecurityScheme()
			.type(SecurityScheme.Type.HTTP).scheme("bearer").bearerFormat("JWT")
			.in(SecurityScheme.In.HEADER).name("Authorization");
		SecurityRequirement securityRequirement = new SecurityRequirement().addList("Bearer Token");

		return new OpenAPI()
				.components(new Components().addSecuritySchemes("Bearer Token", securityScheme))
				.security(Arrays.asList(securityRequirement))
				.servers(List.of(serverLocal, serverDev))
				.info(info);
	}

	static {
		SpringDocUtils.getConfig().addAnnotationsToIgnore(MemberId.class);
	}

	private Server createServer(String url, String description) {
		Server server = new Server();
		server.setUrl(url);
		server.setDescription(description);

		return server;
	}
}
