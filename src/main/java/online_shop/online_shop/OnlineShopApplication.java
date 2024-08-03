package online_shop.online_shop;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "online_shop.online_shop.repository")
@EntityScan(basePackages = "online_shop.online_shop.domain")
public class OnlineShopApplication implements CommandLineRunner{

	public static void main(String[] args) {
		SpringApplication.run(OnlineShopApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

	}
}
