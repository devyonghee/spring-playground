plugins {
    id("nu.studer.jooq") version "8.1"
    id("org.flywaydb.flyway") version "9.5.1"

    kotlin("plugin.jpa")
    kotlin("kapt")
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
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("io.rest-assured:rest-assured")

    //jooq
    implementation("org.springframework.boot:spring-boot-starter-jooq")
    jooqGenerator("com.h2database:h2")
    //exposed
    implementation("org.jetbrains.exposed:exposed-spring-boot-starter:0.41.1")
    implementation("org.jetbrains.exposed:exposed-kotlin-datetime:0.41.1")
    //jpa
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    //querydsl
//    implementation("com.querydsl:querydsl-jpa:5.0.0:jakarta")
//    implementation("com.querydsl:querydsl-core:5.0.0")
//    kapt("com.querydsl:querydsl-apt:5.0.0:jakarta")
//    kapt("com.querydsl:querydsl-kotlin-codegen:5.0.0")
    implementation(group = "com.querydsl", name="querydsl-jpa", classifier="jakarta")
    kapt(group = "com.querydsl", name="querydsl-apt", classifier="jakarta")


    runtimeOnly("com.h2database:h2")
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
                    strategy.name = org.jooq.codegen.example.JPrefixGeneratorStrategy::class.java.name
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

sourceSets {
    main {
        kotlin {
            srcDir("$buildDir/generated/source/kapt/main")
        }
    }
}