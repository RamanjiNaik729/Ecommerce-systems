@Service
public class LibraryService {
    @Autowired private BookRepository bookRepo;
    @Autowired private UserRepository userRepo;
    @Autowired private TransactionRepository transactionRepo;

    public void lendBook(Long bookId, Long userId) {
        Book book = bookRepo.findById(bookId).orElseThrow();
        if (!book.isAvailability()) throw new RuntimeException("Book unavailable");
        book.setAvailability(false);
        Transaction tx = new Transaction();
        tx.setBook(book);
        tx.setUser(userRepo.findById(userId).orElseThrow());
        tx.setIssueDate(LocalDate.now());
        tx.setStatus("ISSUED");
        bookRepo.save(book);
        transactionRepo.save(tx);
    }

    public void returnBook(Long transactionId) {
        Transaction tx = transactionRepo.findById(transactionId).orElseThrow();
        tx.setReturnDate(LocalDate.now());
        tx.setStatus("RETURNED");
        Book book = tx.getBook();
        book.setAvailability(true);
        transactionRepo.save(tx);
        bookRepo.save(book);
    }
}
