package com.example.demo;

import java.util.List;

import org.springframework.ai.reader.ExtractedTextFormatter;
import org.springframework.ai.reader.pdf.PagePdfDocumentReader;
import org.springframework.ai.reader.pdf.config.PdfDocumentReaderConfig;
import org.springframework.stereotype.Component;

@Component
public class MypagePdfDocumentReader {

    public List<org.springframework.ai.document.Document> getDocsFromPdf() {
        PagePdfDocumentReader pdfReader = new PagePdfDocumentReader(
            "classpath:/Bhagavad-gita-Swami-BG-Narasingha.pdf",
            PdfDocumentReaderConfig.builder()
                .withPageTopMargin(0)
                .withPageExtractedTextFormatter(ExtractedTextFormatter.builder()
                    .withNumberOfTopTextLinesToDelete(0)
                    .build())
                .withPagesPerDocument(1)
                .build()
        );

        return pdfReader.read();
    }
}
