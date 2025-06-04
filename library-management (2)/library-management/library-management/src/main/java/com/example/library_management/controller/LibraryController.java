@RestController
@RequestMapping("/api")
public class LibraryController {
    @Autowired private BookRepository bookRepo;
    @Autowired private UserRepository userRepo;
    @Autowired private LibraryService libraryService;
    @Autowired private TransactionRepository txRepo;

    @PostMapping("/books") public Book addBook(@RequestBody Book book) { return bookRepo.save(book); }
    @DeleteMapping("/books/{id}") public void removeBook(@PathVariable Long id) { bookRepo.deleteById(id); }

    @PostMapping("/users") public User addUser(@RequestBody User user) { return userRepo.save(user); }

    @PostMapping("/lend") public void lendBook(@RequestParam Long bookId, @RequestParam Long userId) {
        libraryService.lendBook(bookId, userId);
    }

    @PostMapping("/return") public void returnBook(@RequestParam Long transactionId) {
        libraryService.returnBook(transactionId);
    }

    @GetMapping("/search") public List<Book> searchBooks(@RequestParam String query) {
        List<Book> byTitle = bookRepo.findByTitleContainingIgnoreCase(query);
        List<Book> byAuthor = bookRepo.findByAuthorContainingIgnoreCase(query);
        List<Book> byCategory = bookRepo.findByCategoryContainingIgnoreCase(query);
        return Stream.concat(Stream.concat(byTitle.stream(), byAuthor.stream()), byCategory.stream())
                     .distinct().collect(Collectors.toList());
    }

    @GetMapping("/overdue") public List<Transaction> overdueBooks() {
        return txRepo.findByStatusAndReturnDateBefore("ISSUED", LocalDate.now().minusDays(14));
    }

    @GetMapping("/history/{userId}") public List<Transaction> userHistory(@PathVariable Long userId) {
        return txRepo.findByUserId(userId);
    }
}
