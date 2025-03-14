package dev.anurag.BlogApp.service;

import com.theokanning.openai.completion.CompletionRequest;
import com.theokanning.openai.completion.CompletionResult;
import com.theokanning.openai.service.OpenAiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AiIntegrationService {


    @Autowired
    private OpenAiService openAiService;

    public String generateSummary(String blogContent) {
        String prompt = "Summarize the following blog content in 3-4 sentences:\n" + blogContent;

        CompletionRequest request = CompletionRequest.builder()
                .model("gpt-3.5-turbo")  
                .prompt(prompt)
                .maxTokens(150)          
                .temperature(0.7)        // Balanced creativity
                .build();

        CompletionResult result = openAiService.createCompletion(request);
        return result.getChoices().get(0).getText().trim();
    }
}
