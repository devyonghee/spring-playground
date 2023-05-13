dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-jdbc")

    implementation("io.debezium:debezium-api:2.2.1.Final")
    implementation("io.debezium:debezium-embedded:2.2.1.Final")
    implementation("io.debezium:debezium-connector-mysql:2.2.1.Final")

    implementation("org.springframework.boot:spring-boot-starter-actuator")
    implementation("io.micrometer:micrometer-core")
    implementation("io.micrometer:micrometer-registry-prometheus")

}