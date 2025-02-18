package com.example.jpa_inheritance;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Table(name = "users")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    private String userId;
    private String name;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private UserAuth userAuth;
}