package com.example.jpa_inheritance;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@DiscriminatorValue("GOOGLE")
@Builder
@AllArgsConstructor
public class GoogleAuthDetails extends BaseAuthDetails {
}