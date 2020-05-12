package com.example.demo;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;


@Component
public class JettyRoute extends RouteBuilder{

	@Override
	public void configure() throws Exception {
		from("jetty:http://0.0.0.0:8999/IPM/Test?matchOnUriPrefix=true")
		.log("Message:${body}")
		.setBody(constant("Hello IPM....GoodMorning")).end();
		
	}

}
