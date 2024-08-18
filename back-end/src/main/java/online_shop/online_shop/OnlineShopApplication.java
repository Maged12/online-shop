package online_shop.online_shop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import online_shop.online_shop.property.FileStorageProperties;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "online_shop.online_shop.repository")
@EntityScan(basePackages = "online_shop.online_shop.domain")
@EnableConfigurationProperties({ FileStorageProperties.class })
public class OnlineShopApplication {

	public static void main(String[] args) {
		SpringApplication.run(OnlineShopApplication.class, args);
	}

}
