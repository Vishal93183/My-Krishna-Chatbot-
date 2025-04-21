package com.example.demo;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.ai.ollama.OllamaChatModel;

@RestController
public class ChatController {

    private final OllamaChatModel chatModel;
    private final MypagePdfDocumentReader pdfReader;

    @Autowired
    public ChatController(OllamaChatModel chatModel, MypagePdfDocumentReader pdfReader) {
        this.chatModel = chatModel;
        this.pdfReader = pdfReader;
    }

    @GetMapping("/ai/generate")
    public Map<String, String> generate(
        @RequestParam(value = "message", defaultValue = "Tell me a joke") String message) {

        // Read PDF content
        List<org.springframework.ai.document.Document> docs = pdfReader.getDocsFromPdf();
        String context = docs.stream()
    .map(org.springframework.ai.document.Document::getText)
    .collect(Collectors.joining("\n"));


        // Combine user message with PDF context
        String prompt = "Context: " + context + "\n\nUser: " + message;

String response = this.chatModel.call(prompt); // assuming call() returns a String

return Map.of("generation", response);

    }
}

