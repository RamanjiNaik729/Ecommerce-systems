@Entity
public class Transaction {
    @Id @GeneratedValue
    private Long transactionId;

    @ManyToOne
    private Book book;

    @ManyToOne
    private User user;

    private LocalDate issueDate;
    private LocalDate returnDate;
    private String status; // ISSUED or RETURNED

    // Getters & Setters
}
