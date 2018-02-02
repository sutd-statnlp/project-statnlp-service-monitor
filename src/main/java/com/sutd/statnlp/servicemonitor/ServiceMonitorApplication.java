package com.sutd.statnlp.servicemonitor;

import de.codecentric.boot.admin.config.EnableAdminServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;

import java.net.InetAddress;
import java.net.UnknownHostException;

@SpringBootApplication
@EnableAdminServer
public class ServiceMonitorApplication {

	private static final Logger log = LoggerFactory.getLogger(ServiceMonitorApplication.class);

	private final Environment env;

	public ServiceMonitorApplication(Environment env) {
		this.env = env;
	}

	public static void main(String[] args) throws UnknownHostException {
		SpringApplication app = new SpringApplication(ServiceMonitorApplication.class);
		Environment env = app.run(args).getEnvironment();
		String protocol = "http";

		log.info("\n----------------------------------------------------------\n\t" +
						"Application '{}' is running! Access URLs:\n\t" +
						"Local: \t\t{}://localhost:{}\n\t" +
						"External: \t{}://{}:{}\n\t" +
						"----------------------------------------------------------",
				env.getProperty("spring.application.name"),
				protocol,
				env.getProperty("server.port"),
				protocol,
				InetAddress.getLocalHost().getHostAddress(),
				env.getProperty("server.port"));
	}
}
