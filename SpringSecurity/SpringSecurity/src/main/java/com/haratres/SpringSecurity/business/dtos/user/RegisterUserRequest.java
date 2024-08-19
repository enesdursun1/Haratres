package com.haratres.SpringSecurity.business.dtos.user;



import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;	

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterUserRequest {

	
	@NotBlank()
	@NotNull()
	private String username;
	
	@NotBlank()
	@NotNull()
	@Size(min=7,max=20)
	private String password;
	
}
