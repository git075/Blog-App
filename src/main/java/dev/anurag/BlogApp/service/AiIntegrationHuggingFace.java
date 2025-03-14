package dev.anurag.BlogApp.service;

import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class AiIntegrationHuggingFace {

    @Value("${huggingface.api.url}")
    private String apiUrl;

    @Value("${huggingface.api.key}")
    private String apiKey;

    public String generateSummary(String content) {
        try {
            HttpResponse<JsonNode> response = Unirest.post(apiUrl)
                    .header("Authorization", "Bearer " + apiKey)
                    .header("Content-Type", "application/json")
                    .body("{\"inputs\": \"" + content + "\"}")
                    .asJson();

            if (response.getStatus() == 200) {
                return response.getBody().getArray().getJSONObject(0).getString("summary_text");
            } else {
                return "Error: " + response.getBody().toString();
            }
        } catch (Exception e) {
            return "Exception occurred: " + e.getMessage();
        }
    }
}
