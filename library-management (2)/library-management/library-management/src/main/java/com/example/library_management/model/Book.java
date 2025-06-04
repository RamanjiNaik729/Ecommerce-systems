@Entity
public class Book {
    @Id @GeneratedValue
    private Long bookId;
    private String title;
    private String author;
    private String category;
    private boolean availability;

    // Getters & Setters
}
