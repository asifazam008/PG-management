package com.pg_management.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.pg_management.entity.Expense;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Long>{

}
