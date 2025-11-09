package com.msaccount_se182744.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RegisterRequest {
    @Email
    @NotBlank
    private String gmail;

    @NotBlank(message = "Full name is required")
    private String fullName;

    @NotBlank(message = "Password cannot be blank")
    @Size(min = 8, max = 20)
    private String password;

}
