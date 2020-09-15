package com.seminar.rest;

import com.seminar.rest.Exception.ResourceNotFoundException;
import com.seminar.rest.Repository.InfoRepository;
import com.seminar.rest.model.Info;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/v1/api")
public class BaseController {

    @Autowired
    InfoRepository infoRepository;

    @GetMapping(path = "/infos/{id}")
    public Info getInfo (@PathVariable  int id) throws ResourceNotFoundException {

        return infoRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Can't find info match this id"));
    }

    @GetMapping(path = "/infos")
    public List<Info> getAllInfo(){

        return infoRepository.findAll();
    }
    @PostMapping(path = "/infos/{id}")
    public Info newInfo(@RequestBody Info info){
        return infoRepository.save(info);
    }

    @PutMapping(path = "infos/{id}")
    public ResponseEntity<Info> modifyInfo(@PathVariable int id, @RequestBody Info infodetail) throws ResourceNotFoundException{
        Info info = infoRepository.findById(id).orElseThrow(
                ()->new ResourceNotFoundException("Cant find this id")
        );
        info.setName(infodetail.getName());
        info.setCategory(infodetail.getCategory());
        info.setYear(infodetail.getYear());
        final Info updatedInfo = infoRepository.save(info);
        return ResponseEntity.ok(updatedInfo);
    }

    @DeleteMapping(path = "infos/{id}")
    public Map<String, Boolean> deleteInfo(@PathVariable int id) throws ResourceNotFoundException{
        Info info = infoRepository.findById(id).orElseThrow(
                ()-> new ResourceNotFoundException("Cant find this id")
        );
        infoRepository.delete(info);
        Map<String, Boolean> response = new HashMap<>();
        response.put("Deleted", Boolean.TRUE);
        return response;
    }
}
