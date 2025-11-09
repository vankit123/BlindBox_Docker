package com.msaccount_se182744.entity;

import com.msaccount_se182744.enums.Role;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "system_accounts")
public class SystemAccounts {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer accountId;

    private String userName;

    private String password ;

    private String email;

    private Role role;

    private boolean isActive;
}
