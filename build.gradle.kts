plugins {
	java
	id("org.springframework.boot") version "3.5.0"
	id("io.spring.dependency-management") version "1.1.7"
}

group = "com.romantrippel"
version = "0.0.1-SNAPSHOT"

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(21)
	}
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
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")

	implementation("org.postgresql:postgresql:42.7.6")

	compileOnly("org.projectlombok:lombok")
	annotationProcessor("org.projectlombok:lombok")
	testCompileOnly("org.projectlombok:lombok")
	testAnnotationProcessor("org.projectlombok:lombok")

	implementation ("org.springframework.boot:spring-boot-starter-validation")

	implementation("org.jsoup:jsoup:1.21.1")

	testImplementation("org.springframework.boot:spring-boot-starter-test") {
		exclude(group = "org.mockito", module = "mockito-core")
	}
	testImplementation("org.mockito:mockito-junit-jupiter:5.10.0")
	testImplementation("org.junit.jupiter:junit-jupiter:5.10.0")
	testRuntimeOnly("org.junit.platform:junit-platform-launcher")
	testImplementation ("com.h2database:h2")
}

tasks.withType<Test> {
	useJUnitPlatform()
}
