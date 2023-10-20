package dev.piraputanga.socialcontract;

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
public class SocialContractDTO {
    @NotBlank(message = "O id é obrigatório.")
    private String id;

    @NotBlank(message = "O id do usuario é obrigatório.")
    private String userEmail;

    @NotBlank(message = "O id da ação é obrigatório.")
    private String socialActionId;


    @NotNull(message = "A data do contrato é obrigatória.")
    private Date startDate;


}
