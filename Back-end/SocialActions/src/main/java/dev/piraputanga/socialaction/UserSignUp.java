package dev.piraputanga.socialaction;

import java.util.Date;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class UserSignUp {

    @Id
    @Column(unique = true, nullable = false)
    private String cpf;

    @Basic
    @Column
    private String username;

    @Basic
    @Column
    private String userEmail;

    @Basic
    @Column
    private String userCellphoneNumber;

    @Basic
    @Column
    private String sex;

    @Basic
    @Column
    private String cep;

    @Basic
    @Column
    private String userPassword;
}
