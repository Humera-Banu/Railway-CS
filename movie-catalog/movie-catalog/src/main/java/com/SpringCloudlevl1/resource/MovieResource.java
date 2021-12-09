package com.SpringCloudlevl1.resource;


import java.util.Arrays
;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.reactive.function.client.WebClientAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.SpringCloudlevl1.model.CatalogItem;
import com.SpringCloudlevl1.model.UserRating;
import com.SpringCloudlevl1.services.MovieInfo;
import com.SpringCloudlevl1.services.UserRatingInfo;
import com.netflix.discovery.DiscoveryClient;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;import org.springframework.beans.factory.annotation.Autowired;import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
@RestController
@RequestMapping("/catalog")
public class MovieResource { 
	@Autowired
    private RestTemplate restTemplate;
@Autowired
MovieInfo movieInfo;
@Autowired
UserRatingInfo userRatingInfo; 
@RequestMapping("/{userId}")
public List<CatalogItem> getCatalog(@PathVariable("userId") String userId) { 
	UserRating userRating = userRatingInfo.getUserRating(userId);
return userRating.getRating().stream()
.map(rating -> movieInfo.getCatalogItem(rating))
.collect(Collectors.toList()); 
}
/*public List<CatalogItem> getFallbackCatalog(@PathVariable("userId") String userId) {
return Arrays.asList(new CatalogItem("No Movie", "",0));*/
//}
}



