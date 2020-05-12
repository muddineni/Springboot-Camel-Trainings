package com.example.demo;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;

@org.springframework.stereotype.Component
public class SampleRoute extends RouteBuilder {

	@Override
	public void configure() throws Exception {


		from("file://input_dir?move=completed").autoStartup(false)
		//.log("Recieved file:${file:name}")
		//.log("file headers:${headers}")
		.process(new Processor() {
			
			@Override
			public void process(Exchange exchange) throws Exception {
				System.out.println("Exchange id::"+exchange.getExchangeId());
				System.out.println("Exchange properties::"+exchange.getProperties());
				System.out.println("Exchange In headers::"+exchange.getIn().getHeaders());
				exchange.setProperty("myproperty", "fuse trainings");
				exchange.getIn().setHeader("myheader", "camel traings");
				System.out.println("Exchange In headers::"+exchange.getIn().getHeaders());
				System.out.println("Exchange properties::"+exchange.getProperties());
				System.out.println("Exchange Out headers::"+exchange.getOut().getHeaders());
				exchange.getOut().setHeaders(exchange.getIn().getHeaders());
				exchange.getOut().setBody(exchange.getIn().getBody());
				System.out.println("Exchange Out headers::"+exchange.getOut().getHeaders());
				
			}
		})
		//.log("no of file:${property.CamelBatchSize}")
		.log("Recieved file:${file:name}")
		.log("file headers:${headers}")
		.to("file://output_dir")
		.end();
		
	}
	
	

}
