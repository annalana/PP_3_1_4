package ru.kata.spring.boot_security.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication

public class SpringBootSecurityDemoApplication {
	/*
	 Скрипт для создания администратора после запуска приложения:

	 INSERT INTO users (login, passworduser) VALUES ('root', '$2a$10$1xEQPrZTNYOYIt0i4JsMLe/xC0H1XMPZuB7xO2Zu8Zc/1sc9hyMsS');
	 INSERT INTO roles (role) VALUES ('ROLE_ADMIN');
	 INSERT INTO users_roles (user_id, role_id) VALUES (1, 1);

	 Login: root
	 Password: root
	 */
	public static void main(String[] args) {
		SpringApplication.run(SpringBootSecurityDemoApplication.class, args);
	}

}
