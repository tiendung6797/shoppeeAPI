package shoppee.com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import shoppee.com.property.FileStorageProperties;

@SpringBootApplication
@EnableConfigurationProperties({FileStorageProperties.class})
public class ShoppeeApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShoppeeApiApplication.class, args);
	}

}
