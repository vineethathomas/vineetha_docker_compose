package com.example.demo.service;



import com.example.demo.data.CompactDiscRepository;
import com.example.demo.entities.CompactDisc;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Collection;
import java.util.Optional;

@Service
public class CompactDiscService {

    @Autowired
    private CompactDiscRepository dao;


    public void addToCatalog(CompactDisc disc) {
        dao.insert(disc);
    }

    public Collection<CompactDisc> getCatalog() {
        return dao.findAll();
    }

    public Optional<CompactDisc> getCompactDiscById(ObjectId id) {
        return dao.findById(id);
    }

    public void deleteCompactDisc(ObjectId id) {
        dao.deleteById(id);
    }

    public void deleteCompactDisc(CompactDisc disc) {
        dao.delete(disc);
    }
    
    public void updateCompactDisc(ObjectId id, CompactDisc disc) {
    	disc.setId(id);
    	dao.save(disc);
    }


}
