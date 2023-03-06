package me.devyonghee.flywayorm.article.controller

import com.fasterxml.jackson.databind.ObjectMapper
import me.devyonghee.flywayorm.article.controller.request.ArticleRequestTest
import org.junit.jupiter.api.MethodOrderer
import org.junit.jupiter.api.Order
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestMethodOrder
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultHandlers
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation::class)
class ArticleControllerTest {

    @Autowired
    lateinit var mockMvc: MockMvc

    @Autowired
    lateinit var mapper: ObjectMapper

    @Test
    @Order(1)
    fun saveArticle() {
        val request = ArticleRequestTest.fixture()
        mockMvc.perform(
            post("/api/articles")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(request))
        ).andDo(MockMvcResultHandlers.print())
            .andExpect(status().isCreated)
    }

    @Test
    @Order(2)
    fun getArticle() {
        // given & then
        mockMvc.perform(
            get("/api/articles/title")
                .contentType(MediaType.APPLICATION_JSON)
        ).andDo(MockMvcResultHandlers.print())
            .andExpect(status().isOk)
    }
}