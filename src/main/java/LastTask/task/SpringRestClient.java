package LastTask.task;

import LastTask.task.model.User;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class SpringRestClient {

    private static final String EMPLOYEES_ENDPOINT_URL = "http://91.241.64.178:7081/api/users/";
    private static final RestTemplate restTemplate = new RestTemplate();
    private static final StringBuilder answer = new StringBuilder();
    private static final HttpHeaders headers = new HttpHeaders();
    private static List<String> text;

    public static void main(String[] args) {
        SpringRestClient springRestClient = new SpringRestClient();

        // Step1: get all employees
        springRestClient.getEmployees();

        // Step2: first create a new employee
        springRestClient.createEmployee();

       // Step3: Update employee with id = 1
        springRestClient.updateEmployee();

        // Step4: Delete employee with id = 1
        springRestClient.deleteEmployee(3L);

        System.out.println("ANSWER : "+ answer.toString());
    }

    private void getEmployees() {

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<>("parameters", headers);

        ResponseEntity<String> result = restTemplate.exchange(EMPLOYEES_ENDPOINT_URL, HttpMethod.GET, entity,
                String.class);
        text = Objects.requireNonNull(result.getHeaders().get("Set-Cookie"));
        System.out.println(text.get(0));

    }

    private void createEmployee() {
        User newUser = new User(3L, "James", "Brown", (byte) 23);
        headers.set("Cookie", text.get(0));
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<User> entity = new HttpEntity<>(newUser, headers);
        answer.append(restTemplate.exchange(EMPLOYEES_ENDPOINT_URL, HttpMethod.POST, entity, String.class).getBody());
    }

    private void updateEmployee() {
        User newUser = new User(3L, "Thomas", "Shelby", (byte) 23);
        headers.set("Cookie", text.get(0));
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<User> entity = new HttpEntity<>(newUser, headers);
        answer.append(restTemplate.exchange(EMPLOYEES_ENDPOINT_URL, HttpMethod.PUT, entity, String.class).getBody());
    }

    private void deleteEmployee(Long id) {
        headers.set("Cookie", text.get(0));
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<User> entity = new HttpEntity<>(headers);
        answer.append(restTemplate.exchange(EMPLOYEES_ENDPOINT_URL + id, HttpMethod.DELETE, entity, String.class).getBody());
    }
}

