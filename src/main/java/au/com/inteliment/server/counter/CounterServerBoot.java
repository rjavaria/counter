package au.com.inteliment.server.counter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Counter Application boot
 * 
 * @author rjavaria
 *
 */
@SpringBootApplication
public class CounterServerBoot {

	public static void main(String[] args) {
		SpringApplication.run(CounterServerBoot.class, args);
	}
}