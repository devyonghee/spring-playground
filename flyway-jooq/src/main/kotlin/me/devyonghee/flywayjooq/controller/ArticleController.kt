package me.devyonghee.flywayjooq.controller

import me.devyonghee.flywayjooq.application.ArticleService
import me.devyonghee.flywayjooq.controller.request.ArticleRequest
import me.devyonghee.flywayjooq.controller.response.ArticleResponse
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