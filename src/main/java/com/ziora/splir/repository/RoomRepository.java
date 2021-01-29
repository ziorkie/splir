package com.ziora.splir.repository;

import com.ziora.splir.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoomRepository extends JpaRepository<Room, Long> {


    List<Room> findAllByUsers_id(Long id);




}
