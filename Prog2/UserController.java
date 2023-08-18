@Controller
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());
        return "registration";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute("user") User user) {
        userRepository.save(user);
        return "redirect:/login";
    }
}

@Controller
public class NoteController {
    @Autowired
    private NoteRepository noteRepository;

    @GetMapping("/notes")
    public String showNotes(Model model, Principal principal) {
        User user = userRepository.findByUsername(principal.getName());
        List<Note> notes = noteRepository.findByUser(user);
        model.addAttribute("notes", notes);
        return "notes";
    }

    @PostMapping("/notes")
    public String createNote(@ModelAttribute("note") Note note, Principal principal) {
        User user = userRepository.findByUsername(principal.getName());
        note.setUser(user);
        noteRepository.save(note);
        return "redirect:/notes";
    }
}
Конфігурація Spring Security:
java
Copy code
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private UserRepository userRepository;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
                .antMatchers("/register").permitAll()
                .anyRequest().authenticated()
                .and()
            .formLogin()
                .loginPage("/login")
                .permitAll()
                .and()
            .logout()
                .permitAll();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(username -> userRepository.findByUsername(username));
    }
}





