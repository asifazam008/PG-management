package com.pg_management.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.pg_management.entity.User;
import com.pg_management.service.ExpenseService;
import com.pg_management.service.RoomService;
import com.pg_management.service.UserService;

@Controller
public class AdminController {

    @Autowired
    private RoomService roomService;

    @Autowired
    private ExpenseService expenseService;
    
    @Autowired
    private UserService userService;

    @GetMapping("/admin")
    public String adminDashboard(Model model) {
        // Fetch the list of all users
        List<User> users = userService.getAllUsers();

        // Add data to the model to be accessed in the Thymeleaf template
        model.addAttribute("pendingRegistrations", userService.getPendingRegistrationsCount());
        model.addAttribute("vacantRooms", roomService.getVacantRoomsCount());
        model.addAttribute("monthlyExpenses", expenseService.getTotalExpenses());
        model.addAttribute("users", users);  // Add the list of users

        return "admin-dashboard";  // The view name for the Admin Dashboard
    }
}