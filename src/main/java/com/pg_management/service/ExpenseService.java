package com.pg_management.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pg_management.Repository.ExpenseRepository;
import com.pg_management.entity.Expense;

@Service
public class ExpenseService {

    @Autowired
    private ExpenseRepository expenseRepository;

    /**
     * Retrieve all expenses.
     *
     * @return List of expenses.
     */
    public List<Expense> getAllExpenses() {
        return expenseRepository.findAll();
    }

    /**
     * Save a new expense record.
     *
     * @param expense Expense object to save.
     */
    public void saveExpense(Expense expense) {
        expenseRepository.save(expense);
    }

    /**
     * Retrieve an expense by its ID.
     *
     * @param id Expense ID.
     * @return Expense object or null if not found.
     */
    public Expense getExpenseById(Long id) {
        return expenseRepository.findById(id).orElse(null);
    }

    /**
     * Delete an expense by its ID.
     *
     * @param id Expense ID to delete.
     */
    public void deleteExpense(Long id) {
        expenseRepository.deleteById(id);
    }

    /**
     * Calculate the total expenses.
     *
     * @return Total amount of all expenses.
     */
    public Double getTotalExpenses() {
        return expenseRepository.findAll()
                .stream()
                .mapToDouble(Expense::getAmount)
                .sum();
    }
}