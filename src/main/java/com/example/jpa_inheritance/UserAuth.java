package com.example.jpa_inheritance;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Table(name = "user_auth")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserAuth {
    @Id
    @Column(name = "user_id")
    private String userId;

    @OneToOne
    @JoinColumn(name = "user_id")
    @MapsId
    private User user;

    @Enumerated(EnumType.STRING)
    private AuthType authType;

    @OneToOne(mappedBy = "userAuth", cascade = CascadeType.ALL)
    private BaseAuthDetails authDetails;
}