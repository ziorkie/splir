package com.ziora.splir.repository;

import com.ziora.splir.model.GroupExpense;
import com.ziora.splir.model.Invitation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface GroupExpenseRepository extends JpaRepository<GroupExpense, Long> {
    @Query("SELECT g from GroupExpense g WHERE g.roomId= :roomId and g.userId= :userId ")
    List<GroupExpense> findByRoomIdAndUserId(@Param("roomId")Long roomId, @Param("userId")Long userId);

    @Query("SELECT SUM(g.expenseValue) FROM GroupExpense g WHERE g.roomId= :roomId and g.userId= :userId ")
    Optional<Double> getTotalExpenseByUserAndRoom(@Param("roomId")Long roomId, @Param("userId")Long userId);
}
