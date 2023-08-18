@Entity
public class Note {
    // ...

    @NotBlank(message = "Title is required")
    private String title;

    @NotBlank(message = "Content is required")
    private String content;

    // ...
}