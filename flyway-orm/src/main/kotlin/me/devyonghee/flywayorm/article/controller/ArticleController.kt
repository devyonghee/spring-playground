package me.devyonghee.flywayorm.article.controller

import me.devyonghee.flywayorm.article.application.ArticleService
import me.devyonghee.flywayorm.article.controller.request.ArticleRequest
import me.devyonghee.flywayorm.article.controller.response.ArticleResponse
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.net.URI

@RestController
class ArticleController(
    private val articleService: ArticleService
) {

    @PostMapping("/api/articles")
    fun saveArticle(@RequestBody articleRequest: ArticleRequest): ResponseEntity<Void> {
        val id = articleService.save(articleRequest.toDomain())
        return ResponseEntity.created(URI("/api/articles/${id}")).build()
    }

    @GetMapping("/api/articles/{slug}")
    fun findArticle(@PathVariable slug: String): ResponseEntity<ArticleResponse> {
        return ResponseEntity.ok(
            ArticleResponse(articleService.findOne(slug))
        )
    }
}
