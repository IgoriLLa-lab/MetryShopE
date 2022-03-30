package com.metryfabricshopnew.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class UserDTO {
    private String username;
    private String password;
    private String matchingPassword;
    private String email;
}
