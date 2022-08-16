package com.websocket.simplerest.controller;


import com.websocket.simplerest.entity.Simple;
import com.websocket.simplerest.service.SimpleService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class SimpleRestController {

    @Autowired
    private SimpleService simpleService;


    @GetMapping("/logs")
    public ResponseEntity<?> findAllSimpleObjects(){
        try{
            List<Simple> listOfObjectsSimple = simpleService.getListContent();
            return new ResponseEntity<>(listOfObjectsSimple, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findByIdObject(@PathVariable("id") long id){
        try{
            Simple simple= simpleService.findById(id);
            return new ResponseEntity<>(simple, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/addLog")
    public ResponseEntity<?> saveSimpleObject(@RequestBody Simple simple){
        try{
            Simple savedSimpleObject = simpleService.saveObject(simple);
            return new ResponseEntity<>(savedSimpleObject, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PutMapping("/{id}")
    public ResponseEntity<?> updateSimpleObject(@PathVariable("id") long id,@RequestBody Simple simple){
        try{
            simpleService.update(id, simple);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteObjectById(@PathVariable("id") long id){
        try{
            simpleService.deleteContentById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
