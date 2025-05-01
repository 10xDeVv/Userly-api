package org.example.userly.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", nullable = false)
    private Long id;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "user_password", nullable = false)
    private String password;

    @Column(name = "email", nullable = false)
    private String email;
}
