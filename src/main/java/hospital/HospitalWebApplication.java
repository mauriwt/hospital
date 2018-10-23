package hospital;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableAutoConfiguration
@EntityScan(basePackages={"*.model"})
@EnableJpaRepositories(basePackages = "*.repository")
@ComponentScan(basePackages={"*.controller", "*.utilities", "*.config"})
@EnableTransactionManagement
public class HospitalWebApplication {

	public static void main(String[] args) {
		SpringApplication.run(HospitalWebApplication.class, args);
	}
}
