package project_backend.model;

import lombok.Data;

@Data
public class User {
    private String userId;
    private String email;
    private String password;
    private String token;
}
