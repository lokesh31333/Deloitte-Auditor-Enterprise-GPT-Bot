package com.javatechie.controller;

import com.javatechie.dto.ChatGPTRequest;
import com.javatechie.dto.ChatGptResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/bot")
public class CustomBotController {

    @Value("${openai.model}")
    private String model;

    @Value(("${openai.api.url}"))
    private String apiURL;

    @Autowired
    private RestTemplate template;

    @GetMapping("/chat")
    public String chat(@RequestParam("prompt") String prompt){
        prompt = "You were hired as part of Deloitte Auditor team that specializes in development of Audit for commercial companies and individuals As part of the auditor's day to day job, you need to study US Tax law to file appropriate tax deductions to benefit the clients so answer this question to the client :  " + prompt;
        ChatGPTRequest request=new ChatGPTRequest(model, prompt);
        ChatGptResponse chatGptResponse = template.postForObject(apiURL, request, ChatGptResponse.class);
        return chatGptResponse.getChoices().get(0).getMessage().getContent();
    }
}
