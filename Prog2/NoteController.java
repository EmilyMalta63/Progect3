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