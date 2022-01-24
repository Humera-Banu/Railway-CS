
  package com.RrsCaseStudy;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import com.RrsCaseStudy.service.*;
import com.RrsCaseStudy.model.*;
import com.RrsCaseStudy.Repository.*;
@RunWith(SpringRunner.class)
  
  @SpringBootTest
  class ReservationServiceApplicationTests {
  
  @Autowired
  ReservationService service;
  
  @MockBean
  ReservationRepository repository;
  
//  @Test 
//  public void getTrainTest() {
//  when(repository.findAll()).thenReturn(Stream
//		  .of(new Reservation(12,18,"trainame","Delhi","mysore",245.5,"confirmed"),new Reservation(13,18,"trainame","Delhi","mysore",245.5,"confirmed"))
//		  .collect(Collectors.toList()));
//  assertEquals(2,service.getOrder().size());
// 
//  }
 }