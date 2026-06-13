package com.gokul.journal.application.dtos;

import javax.validation.constraints.NotBlank;

import com.gokul.journal.application.enums.Sentiment;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class JournalEntryCreateDTO {
	
    @NotBlank
    @Schema(description = "Journal Title")
    private String title;
    
    @NotBlank
    @Schema(description = "Journal Content")
    private String content;
    
    @Schema(description = "Sentiment while entering the Journal")
    private Sentiment sentiment;
    
}
