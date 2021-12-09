package com.SpringCloudlevl1.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;

import com.SpringCloudlevl1.model.CatalogItem;
import com.SpringCloudlevl1.model.Movie;
import com.SpringCloudlevl1.model.Ratings;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
@Service
public class MovieInfo {

@Autowired
private RestTemplate restTemplate;

@HystrixCommand(fallbackMethod = "getFallbackCatalogItem")
public CatalogItem getCatalogItem(Ratings rating) {
Movie movie = restTemplate.getForObject("http://movie-info-service/movies/" + rating.getMovieId(), Movie.class);
return new CatalogItem(movie.getName(), movie.getDescription(), rating.getRating());

}

public CatalogItem getFallbackCatalogItem(Ratings rating) {
return new CatalogItem("No Movie Found", " ", 5);

}



}
