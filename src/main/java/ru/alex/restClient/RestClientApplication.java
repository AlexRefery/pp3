package ru.alex.restClient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;
import ru.alex.restClient.pojo.User;

@SpringBootApplication
public class RestClientApplication {
	private final static String URL_USERS = "http://94.198.50.185:7081/api/users";

	public static void main(String[] args) {
		SpringApplication.run(RestClientApplication.class, args);

		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<User[]> responseUsers = restTemplate.getForEntity(URL_USERS, User[].class);
		String cookie = responseUsers.getHeaders().get("Set-Cookie").get(0);
		System.out.println(cookie);
		HttpHeaders headers = new HttpHeaders();

		headers.set(HttpHeaders.COOKIE, cookie);

		HttpEntity<User> request = new HttpEntity<>(new User("James", "Brown", (byte) 22), headers);
		String code1 = restTemplate.exchange(URL_USERS, HttpMethod.POST, request, String.class).getBody();
//		String code1 = restTemplate.postForEntity(URL_USERS, request, String.class).getBody();
		System.out.println(code1);

		Long id = 3L;
		User updatedUser = new User("Thomas", "Shelby", (byte) 33);
		HttpEntity<User> requestUpdate = new HttpEntity<>(updatedUser, headers);
		String code2 = restTemplate.exchange(URL_USERS, HttpMethod.PUT, requestUpdate, String.class, id).getBody();
		System.out.println(code2);

		headers.set(HttpHeaders.COOKIE, cookie.replace("/", "/3"));
		HttpEntity<User> requestDelete = new HttpEntity<>(headers);
		System.out.println(headers);

		ResponseEntity<String> response = restTemplate.exchange(URL_USERS + "/" + id, HttpMethod.DELETE, requestDelete, String.class);
		String code3 = response.getBody();

		System.out.println(code1 + " + " + code2 + " + " + code3 + " + " + "Done!");
	}

}
