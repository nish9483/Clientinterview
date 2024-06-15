package com.example.demo;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import jakarta.persistence.Entity;

@Entity
public class GuestController {
	@Autowired
	GuestRepo cr;
	
	@PostMapping("/guest")
	public ResponseEntity<Guest>saveGuest(@RequestBody Guest gu)
	{
		Guest c=cr.save(gu);
		return new ResponseEntity<Guest>(c,HttpStatus.CREATED);
	}
	@GetMapping("/student")
	public ResponseEntity<List<Guest>>getAllGuest()
	{
		List<Guest> y=cr.findAll();
		return new ResponseEntity<List<Guest>>(y,HttpStatus.OK);
	}
	@GetMapping("/api/guest")
	public ResponseEntity<List<Guest>>getAllAddresses()
	{
		List<Guest>ad=cr.findAll();
		return new ResponseEntity<List<Guest>>(ad,HttpStatus.OK);
	}
	
	@GetMapping("/api/guest/{id}")
	public ResponseEntity<Guest>getGuestById(@PathVariable int id)
	{
		Optional<Guest>clg=cr.findById(id);
		if(clg.isPresent())
		{
			return new ResponseEntity<Guest>(clg.get(),HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity<Guest>(clg.get(),HttpStatus.NOT_FOUND);
		}
	}
	
	@PutMapping("api/guest/{id}")
	public ResponseEntity<Guest>updateGuest(@RequestBody Guest updatedGuest ,@PathVariable int id)
	{
		Optional<Guest>clg=cr.findById(id);
		if(clg.isPresent())
		{
			Guest c=clg.get();
			c.setName(updatedGuest.getName());
			c.setCity(updatedGuest.getCity());
			
			Guest cl=cr.save(c);
			return new ResponseEntity<Guest>(cl,HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity<Guest>(HttpStatus.NOT_FOUND);
		}
	}
	@DeleteMapping("/api/guest/{id}")
	public ResponseEntity<Void>deleteGuest(@PathVariable int id)
	{
		Optional<Guest>clg=cr.findById(id);
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


