package com.websocket.simplerest.service.impl;

import com.websocket.simplerest.entity.Simple;
import com.websocket.simplerest.repository.SimpleRestRepository;
import com.websocket.simplerest.service.SimpleService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class SimpleServiceImpl implements SimpleService {

    private SimpleRestRepository simpleRestRepository;

    @Autowired
    public SimpleServiceImpl(SimpleRestRepository simpleRestRepository){
        this.simpleRestRepository = simpleRestRepository;
    }


    @Override
    public Simple saveObject(Simple simple) {
        return simpleRestRepository.save(simple);
    }

    @Override
    public List<Simple> getListContent() {
        return simpleRestRepository.findAll();
    }

    @Override
    public void deleteContentById(long id) {
        simpleRestRepository.deleteById(id);
    }

    @Override
    public void update(long id, Simple updateSimpleObject) {
        Simple oldSimpleObject = simpleRestRepository.findById(id).orElse(null);
        assert oldSimpleObject != null;
        oldSimpleObject.setContent(updateSimpleObject.getContent());
        simpleRestRepository.save(oldSimpleObject);
    }

    @Override
    public Simple findById(long id) {
        return simpleRestRepository.findById(id).orElse(null);
    }
}
