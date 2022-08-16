package com.websocket.simplerest.service;

import com.websocket.simplerest.entity.Simple;

import java.util.List;

public interface SimpleService {

    Simple saveObject(Simple simple);

    List<Simple> getListContent();

    void deleteContentById(long id);

    void update(long id, Simple simple);

    Simple findById(long id);

}
