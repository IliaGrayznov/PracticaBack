package inreco.vlgu.practic.service;

import inreco.vlgu.practic.dto.AddNewsDto;
import inreco.vlgu.practic.dto.UpdateNewsDto;
import inreco.vlgu.practic.model.*;
import inreco.vlgu.practic.repository.*;
import inreco.vlgu.practic.security.jwt.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NewsService {

    private final NewsRepository newsRepository ;
    private final CurrentNewsRepository currentNewsRepository;
    private  final CatRepository catRepository;
    private  final UserRepository userRepository;
    private final JwtUtils jwtUtils;
    private final AuthorRepository authorRepository;

    @Transactional
    public void save(AddNewsDto addNewsDto) {
        News n = new News();
        n.setContent(addNewsDto.getContent());
        n.setPreview(addNewsDto.getPreview());
        n = newsRepository.save(n);
        Cat c =catRepository.getById(addNewsDto.getCat_id()) ;
        String username = jwtUtils.getUserNameFromJwtToken(addNewsDto.getToken());
        User u =userRepository.findByUsername(username).get();
        Author a = authorRepository.findAuthorByUser(u);
        CurrentNews cn = new CurrentNews();
        cn.setAuthor(a);
        cn.setNews(n);
        cn.setCat(c);
        currentNewsRepository.save(cn);
    }

    @Transactional
    public void update (UpdateNewsDto updateNewsDto) {
        News n = newsRepository.getById(updateNewsDto.getId());
        if (updateNewsDto.getPreview() != null ) {
        n.setPreview(updateNewsDto.getPreview());
        }
        if (updateNewsDto.getContent() != null) {
            n.setContent(updateNewsDto.getContent());
        }
    }


        public List<News> getall()  {
        return newsRepository.findAll();
        }

        public  List<News> gethot () {
        List<CurrentNews> cn = currentNewsRepository.getAllByHotTrue();
            List<News> news = new ArrayList<>();
            cn.forEach(item -> news.add(item.getNews()));
            return news;
        }

        @Transactional
        public void delete (int id) {
        News n = newsRepository.getById(id);
        newsRepository.delete(n);
        }

    public  List<News> gettop () {
        List<CurrentNews> cn = currentNewsRepository.getAllByTopTrue();
        List<News> news = new ArrayList<>();
        cn.forEach(item -> news.add(item.getNews()));
        return news;
    }

}
