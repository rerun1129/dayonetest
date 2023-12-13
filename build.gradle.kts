plugins {
	java
	id("org.springframework.boot") version "3.2.0"
	id("io.spring.dependency-management") version "1.1.4"
}

group = "com.example.dayonetest"
version = "0.0.1-SNAPSHOT"

java {
	sourceCompatibility = JavaVersion.VERSION_17
}

configurations {
	compileOnly {
		extendsFrom(configurations.annotationProcessor.get())
	}
}

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-web")
	compileOnly("org.projectlombok:lombok")

	runtimeOnly("org.mariadb.jdbc:mariadb-java-client")
	runtimeOnly("com.mysql:mysql-connector-j")
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation ("org.springframework.boot:spring-boot-starter-data-redis")

	annotationProcessor("org.projectlombok:lombok")
	testImplementation ("org.mockito:mockito-core:3.+")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation ("org.testcontainers:testcontainers:1.19.0")
	testImplementation ("com.redis.testcontainers:testcontainers-redis-junit:1.6.4")
}

tasks.withType<Test> {
	useJUnitPlatform()
}
