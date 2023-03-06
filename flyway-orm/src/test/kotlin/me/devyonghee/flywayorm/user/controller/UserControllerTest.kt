package me.devyonghee.flywayorm.user.controller

import com.fasterxml.jackson.databind.ObjectMapper
import me.devyonghee.flywayorm.user.controller.request.UserRequestTest
import org.junit.jupiter.api.Assertions.*
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
import org.springframework.test.web.servlet.result.MockMvcResultHandlers.print
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status


@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation::class)
class UserControllerTest {

    @Autowired
    lateinit var mockMvc: MockMvc

    @Autowired
    lateinit var mapper: ObjectMapper

    @Test
    @Order(1)
    fun save() {
        mockMvc.perform(
            post("/api/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(UserRequestTest.fixture()))
        ).andDo(print())
            .andExpect(status().isCreated)
    }

    @Test
    @Order(2)
    fun getUser() {
        mockMvc.perform(
            get("/api/user/{username}", "username")
                .contentType(MediaType.APPLICATION_JSON)
        ).andDo(print())
            .andExpect(status().isOk)
    }
}