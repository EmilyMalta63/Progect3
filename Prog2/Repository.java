public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}

public interface NoteRepository extends JpaRepository<Note, Long> {
    List<Note> findByUser(User user);
}