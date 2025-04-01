package springboot.desafio.cnab.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import springboot.desafio.cnab.model.Transaction;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
}
