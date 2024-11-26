package com.pg_management.Repository;

import org.springframework.stereotype.Repository;
import com.pg_management.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {

	int countByVacantBedsGreaterThan(int i);
}