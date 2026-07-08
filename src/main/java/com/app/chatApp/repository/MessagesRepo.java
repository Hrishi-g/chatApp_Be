package com.app.chatApp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.app.chatApp.vo.Messages;

@Repository
public interface MessagesRepo extends JpaRepository<Messages, Long> {
    @Query("SELECT m FROM Messages m WHERE (m.sender = :user1 AND m.receiver = :user2) ORDER BY m.createdTime ASC")
    List<Messages> findBySenderIdAndReceiverId(@Param("user1") Long user1, @Param("user2") Long user2);

    @Query("SELECT m FROM Messages m WHERE m.sender.mblNo = :mobNO ORDER BY m.createdTime ASC")
    List<Messages> findAllBySenderMobNO(@Param("mobNO") String mobNO);
}
