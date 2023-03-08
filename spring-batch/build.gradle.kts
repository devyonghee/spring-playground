dependencies {
    implementation("org.springframework.boot:spring-boot-starter-batch")
    implementation("org.jetbrains.kotlin:kotlin-reflect")

//    implementation("org.mybatis.spring.boot:mybatis-spring-boot-starter:3.0.0")
//    implementation("org.springframework.boot:spring-boot-starter-quartz")
    runtimeOnly("com.mysql:mysql-connector-j")
//    implementation("org.flywaydb:flyway-core")
//    implementation("org.flywaydb:flyway-mysql")
//    h2
    runtimeOnly("com.h2database:h2")

    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.springframework.batch:spring-batch-test")
}