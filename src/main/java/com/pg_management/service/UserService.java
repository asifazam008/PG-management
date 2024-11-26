package com.pg_management.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.pg_management.Repository.UserRepository;
import com.pg_management.entity.LoginRequestDTO;
import com.pg_management.entity.Role;
import com.pg_management.entity.User;
import com.pg_management.entity.UserRegistrationDTO;

@Service
public class UserService {

	private final PasswordEncoder passwordEncoder;

	@Autowired
	private UserRepository userRepository;

	/**
	 * Fetch all registered users from the database.
	 *
	 * @return List of all users.
	 */
	public List<User> getAllUsers() {
		return userRepository.findAll();
	}

	public void saveUser(User user) {
		userRepository.save(user);
	}

	public int getPendingRegistrationsCount() {
		return (int) userRepository.countByStatus("PENDING");
	}

	/**
	 * Register a new user.
	 *
	 * @param userRegistrationDTO the registration details
	 */
	public void registerUser(UserRegistrationDTO userRegistrationDTO) {
		User user = new User();
		user.setName(userRegistrationDTO.getName());
		user.setEmail(userRegistrationDTO.getEmail());
		user.setPassword(userRegistrationDTO.getPassword()); // Password should be hashed in a real app
		user.setRole(Role.valueOf(userRegistrationDTO.getRole().toUpperCase()));
		user.setStatus("PENDING"); // Default status is "PENDING"
		userRepository.save(user);
	}

	/**
	 * Authenticate a user based on email and password.
	 * 
	 * @param loginRequestDTO Login credentials
	 * @return User if valid, null otherwise
	 */
	 public User authenticateUser(String email, String password) {
	        User user = userRepository.findByEmail(email);
	        if (user != null && passwordEncoder.matches(password, user.getPassword())) {
	            return user; // Authentication successful
	        }
	        return null; // Authentication failed
	    }

	@Autowired
	public UserService(PasswordEncoder passwordEncoder) {
		this.passwordEncoder = passwordEncoder; // Inject BCryptPasswordEncoder
	}

	public String encodePassword(String password) {
		return passwordEncoder.encode(password); // Use the injected PasswordEncoder
	}

}