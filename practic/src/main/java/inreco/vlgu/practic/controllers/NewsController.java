package inreco.vlgu.practic.controllers;

import inreco.vlgu.practic.dto.AddNewsDto;
import inreco.vlgu.practic.dto.UpdateNewsDto;
import inreco.vlgu.practic.model.News;
import inreco.vlgu.practic.service.NewsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/news")
public class NewsController {
    private  final NewsService n;

    @PostMapping
    public void Save( @RequestBody AddNewsDto addNewsDto) {

        n.save(addNewsDto);
    }

    @GetMapping
    public List<News>  getall (){
        return  n.getall();
    }
    @PostMapping ("/upd")
    public  void update (@RequestBody UpdateNewsDto updateNewsDto) {
        n.update(updateNewsDto);
    }

}
