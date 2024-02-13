package me.soojin.springbootdeveloper.controller;

import lombok.RequiredArgsConstructor;
import me.soojin.springbootdeveloper.domain.Article;
import me.soojin.springbootdeveloper.dto.AddArticleRequest;
import me.soojin.springbootdeveloper.dto.ArticleResponse;
import me.soojin.springbootdeveloper.dto.UpdateArticleRequest;
import me.soojin.springbootdeveloper.service.BlogService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RequiredArgsConstructor
@RestController //HTTP Response Body에 객체 데이터를 json 형식으로 반환하는 컨트롤러
public class BlogApiController {
    private final BlogService blogService;

    //http 메서드가 post일 때 전달받은 url와 동일하면 메서드로 매핑
    @PostMapping("/api/articles")
    //요청 본문 값 매핑
    public ResponseEntity<Article> addArticle(@RequestBody AddArticleRequest request, Principal principal) {
        Article savedArticle = blogService.save(request, principal.getName());//요청한 자원이 성공적으로 생성되었으며 저장된 블로그 글 정보를 응답 객체에 담아 전송
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(savedArticle);

    }

    @GetMapping("/api/articles")
    public ResponseEntity<List<ArticleResponse>> findAllArticles() {
        List<ArticleResponse> articles = blogService.findAll()
                .stream()
                .map(ArticleResponse::new)
                .toList();

        return ResponseEntity.ok()
                .body(articles);

    }

    @GetMapping("/api/articles/{id}")
    //URL 경로에서 값 추출
    public ResponseEntity<ArticleResponse> findArticle(@PathVariable long id) {
        Article article = blogService.findById(id);

        return ResponseEntity.ok()
                .body(new ArticleResponse(article));
    }

    @DeleteMapping("/api/articles/{id}")
    public ResponseEntity<Void> deleteArticle(@PathVariable long id) {
        blogService.delete(id);

        return ResponseEntity.ok()
                .build();
    }

    @PutMapping("/api/articles/{id}")
    public ResponseEntity<Article> updateArticle(@PathVariable long id, @RequestBody UpdateArticleRequest request) {
        Article updatedArticle = blogService.update(id,request);
        return ResponseEntity.ok()
                .body(updatedArticle);
    }

}
