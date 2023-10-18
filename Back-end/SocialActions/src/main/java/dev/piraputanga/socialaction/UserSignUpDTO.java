package dev.piraputanga.socialaction;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserSignUpDTO {
    @NotBlank(message = "O cpf é obrigatório.")
    private String cpf;

    @NotBlank(message = "O nome de usuário é obrigatório.")
    private String username;

    @NotBlank(message = "O email é obrigatório.")
    private String userEmail;

    @NotBlank(message = "O número de celular é obrigatório.")
    private String userCellphoneNumber;

    @NotBlank(message = "O sexo é obrigatório.")
    private String sex;

    @NotBlank(message = "O cep é obrigatório.")
    private String cep;

    @NotBlank(message = "A senha é obrigatória.")
    private String userPassword;
}
