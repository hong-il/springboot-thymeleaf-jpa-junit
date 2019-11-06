package boot.jpa.junit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class JunitApplication {

    public static void main(String[] args) {
        SpringApplication.run(JunitApplication.class, args);
    }

}
