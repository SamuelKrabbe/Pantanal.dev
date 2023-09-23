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
public class SocialActionDTO {
    @NotBlank(message = "O id é obrigatório.")
    private String id;

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
