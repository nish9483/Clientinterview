package com.example.demo;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CheckinRepo extends JpaRepository<Checkin,Integer> {

}
