package com.example.demo;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class EndpointsExamples extends RouteBuilder{

	@Override
	public void configure() throws Exception {
	
		
		from("timer://foo?period=60000").autoStartup(false)
		.log("Excange body before directendpoint:${body}")
		.inOnly("direct:firstroute").id("SynchronousRoute")
		.log("Excange body after directendpoint:${body}")
		.to("file://outputdir?fileName=output.txt&allowNullBody=true")
		.end();
		
		
		
		from("direct:firstroute").id("firstDirectEndpoint")
		.delay(10000)
		.setBody(constant("Im from DirectEndpoint"))
		.throwException(new Exception("custom exception"))
		.end();
		
		
		from("seda:secondroute").id("firstSedatEndpoint")
		.delay(10000)
		.log("seda delay completed")
		.setBody(constant("Im from SedaEndpoint"))
		.throwException(new Exception("custom exception"))
		.end();
		
		
	}

}
