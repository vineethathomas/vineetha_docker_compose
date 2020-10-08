package com.example.demo.rest;

import com.example.demo.service.CompactDiscService;

import java.util.Optional;

import com.example.demo.entities.CompactDisc;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/compactdiscs")
@CrossOrigin // allows requests from all domains
public class CompactDiscController {

	// private Logger logger = Logger.getLogger(this.getClass());

	@Autowired
	private CompactDiscService service;

	@RequestMapping(method = RequestMethod.GET)
	public Iterable<CompactDisc> findAll() {
		// logger.info("managed to call a Get request for findAll");
		return service.getCatalog();
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{id}")
	public Optional<CompactDisc> getCdById(@PathVariable("id") String id) {
		return service.getCompactDiscById(new ObjectId(id));
	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
	public void deleteCd(@PathVariable("id") String id) {
		service.deleteCompactDisc(new ObjectId(""+id));
	}

	@RequestMapping(method = RequestMethod.DELETE)
	public void deleteCd(@RequestBody CompactDisc disc) {
		service.deleteCompactDisc(disc);
	}

	@RequestMapping(method = RequestMethod.POST)
	public void addCd(@RequestBody CompactDisc disc) {
		service.addToCatalog(disc);
	}
	
	@RequestMapping(method = RequestMethod.GET, value="/seeddb")
	public void seedDb() {
		service.addToCatalog(new CompactDisc("Imagine","John Lennon",12.56));
		service.addToCatalog(new CompactDisc("Tommy","The Who",15.34));
		service.addToCatalog(new CompactDisc("Help","The Beatles",18.56));
	}
	
	@RequestMapping(method = RequestMethod.PUT, value="/{id}")
	public void updateCD(@PathVariable("id") ObjectId id, @RequestBody CompactDisc disc) { 
		service.updateCompactDisc(id, disc);
	}
	

}
