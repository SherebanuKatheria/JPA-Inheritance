package com.example.jpa_inheritance;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "auth_type", discriminatorType = DiscriminatorType.STRING)
@Table(name = "auth_details")
public abstract class BaseAuthDetails {

    @Id
    @Column(name = "user_id")
    private String userId;

    @OneToOne
    @JoinColumn(name = "user_id")
    @MapsId
    private UserAuth userAuth;

    public void setUserAuth(UserAuth userAuth) {
        this.userAuth = userAuth;
        this.userId = userAuth.getUserId(); // Ensure ID consistency
    }
}
