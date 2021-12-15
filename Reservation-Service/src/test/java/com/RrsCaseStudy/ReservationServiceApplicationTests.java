package com.RrsCaseStudy;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Collection;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.RrsCaseStudy.Repository.*;
import com.RrsCaseStudy.model.*;
import com.RrsCaseStudy.resource.*;
import com.RrsCaseStudy.service.*;
@RunWith(SpringRunner.class)
@SpringBootTest
class ReservationServiceApplicationTests {
	@Autowired
	ReservationService service;
	@MockBean
	ReservationRepository repository;
	@Test
	public void getTrainTest()
	{
		when(repository.findAll()).thenReturn(Stream
			.of(new Reservation(11,null,"confirmed"), new Reservation(13,null,"confirmed"))
			.collect(Collectors.toList()));
	assertEquals(2,service.getOrder().size());
	}

	@Test
	public void addTrainTest()
	{
		 Reservation order= new Reservation(13,null,"confirmed");
		when(repository.insert(order)).thenReturn(order);
		assertEquals(order,service.addReservation(order));
	}
	@Test
	public void updateTrainTest()
	{
		Reservation order= new Reservation(13,null,"confirmed");
		when(repository.save(order)).thenReturn(order);
			assertEquals(order,service.update(order));
		
	}
	@Test
	public void deleteTrainTest()
	{
		Reservation order= new Reservation(13,null,"confirmed");
		service.delete(order);
		verify(repository,times(1)).delete(order);
		
	}

}
