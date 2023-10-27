package dev.piraputanga.dto;

import java.util.Date;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateSocialContractDTO {

    @NotNull(message = "O id da ação é obrigatório.")
    private Long socialActionId;

    @NotNull(message = "A data do contrato é obrigatória.")
    private Date contractDate;
}
