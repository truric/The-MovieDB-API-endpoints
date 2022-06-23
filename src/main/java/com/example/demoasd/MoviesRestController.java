package com.example.demoasd;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import java.util.Collections;
import java.util.List;

@RestController
public class MoviesRestController {

    @Value("${apiKey}")
    private String apiKey;

    private RestTemplate restTemplate;

    @Autowired
    public void restService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    @GetMapping("/")
    @ResponseBody
    private ModelAndView index() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("index");
        return modelAndView;
    }

    @GetMapping("/top_rated")
    public List<Object> getTopRated() {
        String url = "https://api.themoviedb.org/3/movie/top_rated?api_key=" + apiKey + "&language=en-US&page=1";
        return Collections.singletonList(restTemplate.exchange(url, HttpMethod.GET, null, Object.class).getBody());
    }

    @GetMapping("/popular")
    public List<Object> getPopular() {
        String url = "https://api.themoviedb.org/3/movie/popular?api_key=" + apiKey + "&language=en-US&page=1";
        return Collections.singletonList(restTemplate.exchange(url, HttpMethod.GET, null, Object.class).getBody());
    }

    @GetMapping("/upcoming")
    public List<Object> getUpcoming() {
        String url = "https://api.themoviedb.org/3/movie/upcoming?api_key=" + apiKey;
        return Collections.singletonList(restTemplate.exchange(url, HttpMethod.GET, null, Object.class).getBody());
    }

    @GetMapping("/now_playing")
    public List<Object> getNowPlaying() {
        String url = "https://api.themoviedb.org/3/movie/now_playing?api_key=" + apiKey;
        return Collections.singletonList(restTemplate.exchange(url, HttpMethod.GET, null, Object.class).getBody());
    }
}
