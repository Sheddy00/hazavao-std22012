package hei.school.com.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ChatGptService {
    @Value("${OPENAI_API_KEY}")
    private String apiKey;

    private final RestTemplate restTemplate = new RestTemplate();

    public String getMalagasyDefinition(String teny) {
        String endpoint = "https://api.openai.com/v1/chat/completions";

        Map<String, Object> body = new HashMap<>();
        body.put("model", "gpt-3.5-turbo");

        List<Map<String, String>> messages = List.of(
                Map.of("role", "system", "content", "Mamaly amin'ny teny malagasy amin'ny fomba mazava."),
                Map.of("role", "user", "content", "Inona ny dikan'ny teny '" + teny + "'?")
        );

        body.put("messages", messages);

        Map<String, String> headers = Map.of(
                "Authorization", "Bearer " + apiKey,
                "Content-Type", "application/json"
        );

        var request = new org.springframework.http.HttpEntity<>(body, new org.springframework.http.HttpHeaders() {{
            setAll(headers);
        }});

        var response = restTemplate.postForEntity(endpoint, request, Map.class);
        List<Map<String, Object>> choices = (List<Map<String, Object>>) response.getBody().get("choices");

        return (String) ((Map<String, Object>) choices.get(0).get("message")).get("content");
    }
}
