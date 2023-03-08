package com.websocket.simplerest.repository;

import com.websocket.simplerest.entity.Simple;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface SimpleRestRepository extends JpaRepository<Simple, Long> {

}
