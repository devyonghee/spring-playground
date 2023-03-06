package me.devyonghee.flywayorm.article.controller.request

class ArticleRequestTest {
    companion object {
        fun fixture(
            title: String = "title",
            description: String = "description",
            body: String = "body",
            tagList: List<String> = listOf("tag1", "tag2")
        ) = ArticleRequest(title, description, body, tagList)
    }
}