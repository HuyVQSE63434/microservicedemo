package com.example.zuul;

import javax.servlet.http.HttpServletRequest;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableDiscoveryClient
@EnableZuulProxy
public class ZuulApplication {

	public static void main(String[] args) {
		SpringApplication.run(ZuulApplication.class, args);
	}

	@Bean
	public ZuulFilter postFilter(){
		return new ZuulFilter(){

			@Override
			public Object run() throws ZuulException {
				System.out.println("Pre filter - run");
				HttpServletRequest httpServletRequest = RequestContext.getCurrentContext().getRequest();
				System.out.println("Request Method: "+httpServletRequest.getMethod());
				System.out.println("Request url: "+httpServletRequest.getRequestURL());
				return null;
			}

			@Override
			public boolean shouldFilter() {
				return true;
			}

			@Override
			public int filterOrder() {
				return 0;
			}

			@Override
			public String filterType() {
				return "post";
			}
			
		};
	}

}
