@Entity
public class Note {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String title;
    private String content;
    
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    
    // Constructors, getters, setters
}