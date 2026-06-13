package com.gokul.journal.application.dtos;

import javax.validation.constraints.NotEmpty;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginDTO {
	
    @NotEmpty
    @Schema(description = "Username of user trying to log in", example = "user01")
    private String userName;
    
    @NotEmpty
    @Schema(description = "Password of the user", example = "Password@123")
    private String password;
    
}