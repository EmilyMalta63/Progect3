@Entity
public class User {
    // ...

    @NotNull
    @Size(min = 5, message = "Username must be at least 5 characters")
    private String username;

    @NotNull
    @Size(min = 8, message = "Password must be at least 8 characters")
    private String password;

    // ...
}