package dev.piraputanga.socialcontract;

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
public class SocialContract {

    @Id
    private String id;

    @Basic
    @Column
    private String userEmail;

    @Basic
    @Column
    private String socialActionId;

    @Basic
    @Column
    @Temporal(TemporalType.DATE)
    private Date contractDate;
}