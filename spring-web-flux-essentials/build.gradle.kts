import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

//plugins {
//	id("org.springframework.boot") version "3.1.3"
//	id("io.spring.dependency-management") version "1.1.3"
//	kotlin("jvm") version "1.8.22"
//	kotlin("plugin.spring") version "1.8.22"
//}
//
//
//group = "com.softwalter"
//version = "0.0.1-SNAPSHOT"
//
//java {
//	sourceCompatibility = JavaVersion.VERSION_17
//}
//
//configurations {
//	compileOnly {
//		extendsFrom(configurations.annotationProcessor.get())
//	}
//}
//
//repositories {
//	mavenCentral()
//}
//
//dependencies {
//	implementation("org.springframework.boot:spring-boot-starter-validation:3.1.2")
//	implementation("org.springframework.boot:spring-boot-starter-data-r2dbc")
//	implementation("org.springframework.boot:spring-boot-starter-webflux")
//
//	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
//	implementation("org.jetbrains.kotlin:kotlin-reflect")
//	implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactor")
//	implementation("io.projectreactor.kotlin:reactor-kotlin-extensions")
//
//	implementation("io.projectreactor.tools:blockhound:1.0.8.RELEASE")
//	implementation("io.r2dbc:r2dbc-postgresql:0.8.2.RELEASE")
//	//implementation("org.postgresql:r2dbc-postgresql:1.0.0.RELEASE")
//	implementation("io.projectreactor:reactor-tools")
//
//	annotationProcessor("org.projectlombok:lombok")
//	compileOnly("org.projectlombok:lombok")
//	developmentOnly("org.springframework.boot:spring-boot-devtools")
//
//
//	testImplementation("org.springframework.boot:spring-boot-starter-test")
//	testImplementation("io.projectreactor:reactor-test")
//	implementation("io.projectreactor.kotlin:reactor-kotlin-extensions")
//
//
//
//
//}
//
//tasks.withType<KotlinCompile> {
//	kotlinOptions {
//		freeCompilerArgs += "-Xjsr305=strict"
//		jvmTarget = "17"
//	}
//}
//
//tasks.withType<Test> {
//	useJUnitPlatform()
//}
plugins {
	kotlin("jvm") version "1.5.21"
	id("org.springframework.boot") version "2.3.0.RELEASE"
	id("io.spring.dependency-management") version "1.0.11.RELEASE"
}

repositories {
	mavenCentral()
}

dependencies {
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactor")
	implementation("io.projectreactor.kotlin:reactor-kotlin-extensions")
	implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core")
//	implementation("org.jetbrains.kotlinx:kotlinx-serialization-json")
	implementation("io.projectreactor.tools:blockhound:1.0.3.RELEASE")
	implementation("io.r2dbc:r2dbc-postgresql:0.8.2.RELEASE")
	implementation("io.projectreactor:reactor-tools")
	implementation("org.springframework.boot:spring-boot-starter-validation")
	implementation("org.springframework.boot:spring-boot-starter-data-r2dbc")
	implementation("org.springframework.boot:spring-boot-starter-webflux")
	implementation("org.springframework.boot:spring-boot-devtools")
	implementation("org.projectlombok:lombok")
	implementation("org.springframework.boot:spring-boot-starter-test") {
		exclude(group = "org.junit.vintage", module = "junit-vintage-engine")
	}
	implementation("io.projectreactor:reactor-test")
}

java {
	sourceCompatibility = JavaVersion.VERSION_11
	targetCompatibility = JavaVersion.VERSION_11
}

dependencyManagement {
	imports {
		mavenBom("org.springframework.boot:spring-boot-dependencies:2.3.0.RELEASE")
	}
}

tasks.withType<org.springframework.boot.gradle.tasks.bundling.BootJar> {
	launchScript()
}