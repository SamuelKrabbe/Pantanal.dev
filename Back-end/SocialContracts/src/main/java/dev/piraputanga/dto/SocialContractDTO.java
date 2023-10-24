package dev.piraputanga.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SocialContractDTO {
    @NotBlank(message = "O id é obrigatório.")
    private Long id;

    @NotBlank(message = "O email do usuario é obrigatório.")
    private String userEmail;

    @NotBlank(message = "O id da ação é obrigatório.")
    private Long socialActionId;


    @NotNull(message = "A data do contrato é obrigatória.")
    private Date startDate;


}
