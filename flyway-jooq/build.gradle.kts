plugins {
    id("nu.studer.jooq") version "8.1"
    id("org.flywaydb.flyway") version "9.5.1"
}

val flywayMigration = configurations.create("flywayMigration")
val h2Path = "${project.buildDir}/generated/jooqh2"

flyway {
    url = "jdbc:h2:file:$h2Path"
    user = "sa"
    password = ""
    configurations = arrayOf(flywayMigration.name)
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-validation")
    implementation("org.springframework.boot:spring-boot-starter-jooq")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("io.rest-assured:rest-assured")

    runtimeOnly("com.h2database:h2")
    jooqGenerator("com.h2database:h2")
    flywayMigration("com.h2database:h2")
    implementation("org.flywaydb:flyway-core")
}

jooq {
    configurations {
        create("main") {
            jooqConfiguration.apply {
                logging = org.jooq.meta.jaxb.Logging.WARN
                jdbc.apply {
                    driver = "org.h2.Driver"
                    url = flyway.url
                    user = flyway.user
                    password = flyway.password
                }
                generator.apply {
                    database.apply {
                        name = "org.jooq.meta.h2.H2Database"
                        inputSchema = "PUBLIC"
                        includes = ".*"
                        excludes = ""
                    }
                    target.apply {
                        packageName = "me.devyonghee.kotlinjooq.generated"
                        directory = "build/generated/jooq/main"
                    }
                }
            }
        }
    }
}

tasks.named("generateJooq").configure {
    dependsOn("flywayMigrate")
    inputs.files(fileTree("src/main/resources/db/migration"))
        .withPropertyName("migrations")
        .withPathSensitivity(PathSensitivity.RELATIVE)
    doLast {
        delete("$h2Path.mv.db")
    }
}
