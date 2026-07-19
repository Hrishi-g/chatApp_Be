package com.app.chatApp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.app.chatApp.dto.ChatDto;
import com.app.chatApp.vo.Messages;

@Repository
public interface MessagesRepo extends JpaRepository<Messages, Long> {

    @Query("SELECT new com.app.chatApp.dto.ChatDto(m.sender, m.receiver, m.msg,m.status, m.createdTime, m.delieverdTime, m.receiverTime) FROM Messages m WHERE m.sender=:mobNO ORDER BY m.createdTime DESC")
    List<ChatDto> findAllBySenderMobNO(@Param("mobNO") String mobNO);
}
