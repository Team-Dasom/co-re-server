package com.dongyang.core.external.config;

import java.util.concurrent.TimeUnit;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;

import io.netty.channel.ChannelOption;
import io.netty.handler.timeout.ReadTimeoutHandler;
import reactor.netty.http.client.HttpClient;
import reactor.netty.tcp.TcpClient;

@Configuration
public class WebClientConfig {

	@Bean
	public WebClient webClient() {
		return WebClient.builder()
			.exchangeStrategies(ExchangeStrategies.builder()
				.codecs(configurer -> configurer
					.defaultCodecs()
					.maxInMemorySize(16 * 1024 * 1024))
				.build())
			.clientConnector(new ReactorClientHttpConnector(
				HttpClient.from(
					TcpClient.create()
						.option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 30000)
						.doOnConnected(conn ->
							conn.addHandler(new ReadTimeoutHandler(30000, TimeUnit.MILLISECONDS))
						)
				)
			))
			.build();
	}

}
