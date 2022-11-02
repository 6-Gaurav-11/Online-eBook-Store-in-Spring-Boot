package com.capgemini.gateway.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.capgemini.gateway.filter.JwtAuthFilter;

@Configuration
public class GatewayConfig {
	
	@Autowired
	private JwtAuthFilter filter;

	@Bean
	public RouteLocator routes(RouteLocatorBuilder builder) {
		return builder.routes().route("securityserver", r -> r.path("/bookstore/auth/**").filters(f -> f.filter(filter)).uri("http://localhost:8400"))
				.route("bookservice", r -> r.path("bookstore/books/**").filters(f -> f.filter(filter)).uri("http://localhost:8100"))
				.route("userservice", r -> r.path("bookstore/users/**").filters(f -> f.filter(filter)).uri("http://localhost:8200"))
				.route("libraryservice", r -> r.path("bookstore/library/**").filters(f -> f.filter(filter)).uri("http://localhost:8300"))
				.route("bookcontents", r -> r.path("bookstore/contents/**").filters(f -> f.filter(filter)).uri("http://localhost:8500")).build();
	}

}
