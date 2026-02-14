package sn.atos.ProjetJava17;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class ProjetJava17Application {

	public static void main(String[] args) {
		SpringApplication.run(ProjetJava17Application.class, args);
	}

}
