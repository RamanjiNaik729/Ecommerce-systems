public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findByUserId(Long userId);
    List<Transaction> findByStatusAndReturnDateBefore(String status, LocalDate date);
}
