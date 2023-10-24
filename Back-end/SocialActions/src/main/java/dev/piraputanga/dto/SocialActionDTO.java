package dev.piraputanga.dto;

import java.util.Date;

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
public class SocialActionDTO {
    
    @NotNull(message = "O id é obrigatório.")
    private Long id;

    @NotBlank(message = "O nome da ação é obrigatório.")
    private String name;

    @NotNull
    private Boolean status;

    @NotBlank(message = "A descrição da é obrigatória.")
    private String description;

    @NotNull(message = "A data de início é obrigatória.")
    private Date startDate;

    @NotNull(message = "A data de término é obrigatória.")
    private Date endDate;
}
