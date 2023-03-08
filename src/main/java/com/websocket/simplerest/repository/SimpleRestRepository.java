package com.websocket.simplerest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.websocket.simplerest.entity.Simple;

@Repository
public interface SimpleRestRepository extends JpaRepository<Simple,Long> {




}
