package com.eksad.loadbalancer.load;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.eksad.loadbalancer.load.config.RibbonConfiguration;

@SpringBootApplication

@RestController
@RibbonClient(
  name = "ping-a-server",
  configuration = RibbonConfiguration.class)
public class LoadApplication {
	
	  @LoadBalanced
	    @Bean
	    RestTemplate getRestTemplate() {
	        return new RestTemplate();
	    }
	 
	    @Autowired
	    RestTemplate restTemplate;
	 
	    @RequestMapping("brand/getAll")
	    public String serverLocation() {
	        return this.restTemplate.getForObject(
	          "http://ping-server/brand/getAll", String.class);
	    }

	public static void main(String[] args) {
		SpringApplication.run(LoadApplication.class, args);
	}

}
