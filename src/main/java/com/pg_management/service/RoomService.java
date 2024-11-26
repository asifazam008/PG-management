package com.pg_management.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pg_management.Repository.RoomRepository;
import com.pg_management.entity.Room;

@Service
public class RoomService {
	
	@Autowired
    private RoomRepository roomRepository;

    public List<Room> getAllRooms() {
        return roomRepository.findAll();
    }

    public void saveRoom(Room room) {
        roomRepository.save(room);
    }
    
    public int getVacantRoomsCount() {
        return (int) roomRepository.countByVacantBedsGreaterThan(0);
    }
}