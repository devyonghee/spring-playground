package me.devyonghee.flywayorm.user.domain

data class Email(
    val value: String
) {
    init {
        require(value.matches(REGEX)) { "email('$value') must be email format" }
    }


    companion object {
        const val REGEX_PATTERN = "^[A-Za-z0-9+_.-]+@(.+)$"
        private val REGEX = Regex(REGEX_PATTERN)
    }

    override fun toString(): String {
        return value
    }
}