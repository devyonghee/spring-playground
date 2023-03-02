package me.devyonghee.kotlinjooq.controller

import me.devyonghee.kotlinjooq.application.ArticleService
import me.devyonghee.kotlinjooq.controller.request.ArticleRequest
import me.devyonghee.kotlinjooq.controller.response.ArticleResponse
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.net.URI

@RestController
@RequestMapping("/api")
class ArticleController(
    private val articleService: ArticleService
) {

    @PostMapping("/articles")
    fun saveArticle(@RequestBody articleRequest: ArticleRequest): ResponseEntity<Void> {
        val id = articleService.save(articleRequest.domain)
        return ResponseEntity.created(URI("/api/articles/${id}")).build()
    }

    @GetMapping("/articles/{slug}")
    fun findArticle(@PathVariable slug: String): ResponseEntity<ArticleResponse> {
        return ResponseEntity.ok(
            ArticleResponse(articleService.findOne(slug))
        )
    }

}