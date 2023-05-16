package ru.alex.restClient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import ru.alex.restClient.pojo.User;

import java.net.HttpCookie;
import java.util.Optional;

import static com.fasterxml.jackson.databind.type.LogicalType.Collection;

@SpringBootApplication
public class RestClientApplication {
	private final static String URL_GET_USERS = "http://94.198.50.185:7081/api/users";

	public static void main(String[] args) {
		SpringApplication.run(RestClientApplication.class, args);

		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<User[]> responseUsers = restTemplate.getForEntity(URL_GET_USERS, User[].class);
		User[] users = responseUsers.getBody();

		System.out.println(responseUsers.getHeaders().get("Set-Cookie").get(0));
		Optional.ofNullable(responseUsers.getHeaders().get(HttpHeaders.SET_COOKIE)).stream()
				.flatMap(Collection::stream)
				.map(HttpCookie::parse)
				.flatMap(Collection::stream)
				.map(HttpCookie::toString)
				.forEach(c -> headers.add(HttpHeaders.COOKIE, c));

		HttpHeaders headers = new HttpHeaders();
		headers.set("", "application/json");

		System.out.println("Done!");
	}

}
