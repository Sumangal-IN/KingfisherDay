package com.tcs.KingfisherDay;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.tcs.KingfisherDay")

public class KingfisherDayApplication {

	public static void main(String[] args) {
		// System.setProperty("http.proxyHost", "165.225.80.32");
		// System.setProperty("http.proxyPort", "80");
		// System.setProperty("http.nonProxyHosts", "localhost|127.0.0.1");

		SpringApplication.run(KingfisherDayApplication.class, args);
	}
}
