package me.devyonghee.flywayjooq.user.controller.request

class UserRequestTest {
    companion object {
        fun fixture(
            username: String = "username",
            password: String = "password",
            email: String = "email@email.com"
        ) = UserRequest(username, password, email)
    }
}