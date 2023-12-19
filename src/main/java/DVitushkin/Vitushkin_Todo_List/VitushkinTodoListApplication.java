package DVitushkin.Vitushkin_Todo_List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class VitushkinTodoListApplication {

	public static void main(String[] args) {
		SpringApplication.run(VitushkinTodoListApplication.class, args);
	}

}