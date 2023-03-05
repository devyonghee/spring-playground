package me.devyonghee.flywayjooq.user.controller

import jakarta.validation.Valid
import me.devyonghee.flywayjooq.user.application.UserService
import me.devyonghee.flywayjooq.user.controller.request.UserRequest
import me.devyonghee.flywayjooq.user.controller.response.UserResponse
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.net.URI

@RestController
class UserController(
    private val userService: UserService
) {

    @PostMapping("/api/user")
    fun save(@RequestBody @Valid request: UserRequest): ResponseEntity<UserResponse> {
        val registeredUserName = userService.register(request.toDomain())
        return ResponseEntity.created(URI("/api/user/$registeredUserName")).build()
    }

    @GetMapping("/api/user/{username}")
    fun getUser(@PathVariable username: String): ResponseEntity<UserResponse> {
        return ResponseEntity.ok(UserResponse(userService.findUser(username)))
    }
}