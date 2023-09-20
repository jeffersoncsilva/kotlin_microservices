import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	id("org.springframework.boot") version "3.1.3"
	id("io.spring.dependency-management") version "1.1.3"
	kotlin("jvm") version "1.8.22"
	kotlin("plugin.spring") version "1.8.22"
	`maven-publish`
}

group = "Livro.backend.shared_components"
version = "0.0.1"

java {
	sourceCompatibility = JavaVersion.VERSION_17
}

repositories {
	mavenCentral()
	mavenLocal()
}

dependencies {
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("jakarta.validation:jakarta.validation-api:3.0.2")

}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs += "-Xjsr305=strict"
		jvmTarget = "17"
	}
}

publishing {
	publications {
		create<MavenPublication>("mavenKotlin") {
			groupId = "livro.backend.shared_components"
			artifactId = "shared_components"
			version = "0.0.3"
			from(components["kotlin"])
			pom{
				name.set("sharedcomponents")
				description.set("Shared components para o Livro backend")
				licenses {
					name.set("The Apache License, Version 2.0")
					url.set("http://www.apache.org/licenses/LICENSE-2.0.txt")
				}
				developers{
					developer{
						name.set("Jefferson C Silva")
						email.set("jefferson.c.silva@outlook.com")
					}
				}
			}
		}
	}
	repositories {
		mavenLocal()
	}
}

tasks{
	withType<JavaCompile>{
		options.encoding = "UTF-8"
	}
	jar{
		archiveBaseName.set("shared_components")
		archiveVersion.set("0.0.1")
	}
}