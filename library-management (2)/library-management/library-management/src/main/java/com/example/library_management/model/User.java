@Entity
public class User {
    @Id @GeneratedValue
    private Long userId;
    private String name;
    private String membershipType;

    // Getters & Setters
}
