package com.example.jpa_inheritance;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@DiscriminatorValue("LOCAL")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LocalAuthDetails extends BaseAuthDetails {
    private String passwordHash;
}