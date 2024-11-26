package com.pg_management.Repository;

import org.springframework.stereotype.Repository;
import com.pg_management.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);

	int countByStatus(String string);
}
