package com.gokul.journal.application.dtos;

import javax.validation.constraints.NotEmpty;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Data required to create an admin user")
public class AdminUserDTO {
	
	@NotEmpty
	@Schema(description = "provide Unique username for admin login", example = "admin_user")
	private String userName;
	
	@NotEmpty
	@Schema(description = "Admin user's password", example = "Password@123")
	private String password;
	
	@Schema(description = "Admin email", example = "admin_user@gmail.com")
	private String email;
	
}
