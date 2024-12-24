package tech.reliab.course.ryabikov.bank.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tech.reliab.course.ryabikov.bank.entity.Bank;

@Repository
public interface BankRepository extends JpaRepository<Bank,Long> {
}
