package com.example.demo;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class CheckinController {
	
	@Autowired
	CheckinRepo cr;
	
	@PostMapping("/checkin")
	public ResponseEntity<Checkin>saveCheckin(@RequestBody Checkin ck)
	{
		Checkin c=cr.save(ck);
		return new ResponseEntity<Checkin>(c,HttpStatus.CREATED);
	}
	@GetMapping("/student")
	public ResponseEntity<List<Checkin>>getAllCheckin()
	{
		List<Checkin> y=cr.findAll();
		return new ResponseEntity<List<Checkin>>(y,HttpStatus.OK);
	}
	@GetMapping("/api/checkin")
	public ResponseEntity<List<Checkin>>getAllAddresses()
	{
		List<Checkin>ad=cr.findAll();
		return new ResponseEntity<List<Checkin>>(ad,HttpStatus.OK);
	}
	
	@GetMapping("/api/checkin/{id}")
	public ResponseEntity<Checkin>getCheckinById(@PathVariable int id)
	{
		Optional<Checkin>clg=cr.findById(id);
		if(clg.isPresent())
		{
			return new ResponseEntity<Checkin>(clg.get(),HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity<Checkin>(clg.get(),HttpStatus.NOT_FOUND);
		}
	}
	
	@PutMapping("api/checkin/{id}")
	public ResponseEntity<Checkin>updateCheckin(@RequestBody Checkin updatedCheckin ,@PathVariable int id)
	{
		Optional<Checkin>clg=cr.findById(id);
		if(clg.isPresent())
		{
			Checkin c=clg.get();
			c.setName(updatedCheckin.getName());
			
			Checkin cl=cr.save(c);
			return new ResponseEntity<Checkin>(cl,HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity<Checkin>(HttpStatus.NOT_FOUND);
		}
	}
	@DeleteMapping("/api/checkin/{id}")
	public ResponseEntity<Void>deleteCheckin(@PathVariable int id)
	{
		Optional<Checkin>clg=cr.findById(id);
		if(clg.isPresent())
		{
			cr.deleteById(id);
			return new ResponseEntity<Void>(HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}
	}
}