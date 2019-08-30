import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    //kotlin("jvm") version "1.3.31"
    kotlin("multiplatform") version "1.3.50"
    id("se.jensim.kt2ts") version "0.11.0"
}

repositories {
    mavenCentral()
}

kotlin {
    jvm()
    js()

    sourceSets {

        val commonMain by getting {
            dependencies {
                java
                implementation(kotlin("stdlib-common"))
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test-common"))
                implementation(kotlin("test-annotations-common"))
            }
        }

        // Default source set for JVM-specific sources and dependencies:
        jvm().compilations["main"].defaultSourceSet {
            dependencies {
                implementation(kotlin("stdlib-jdk8"))
            }
        }
        // JVM-specific tests and their dependencies:
        jvm().compilations["test"].defaultSourceSet {
            dependencies {
                implementation(kotlin("test"))
                implementation(kotlin("test-junit"))
            }
        }
    }
}

kt2ts {
    output {
        outputFile = file("$buildDir/ts/kt2ts.d.ts")
        annotations = listOf("io.digitalmagic.ToTypescript")
    }
    classFilesSources.compileTasks = listOf(tasks.named("compileKotlinJvm"))
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}

group = "io.digitalmagic"
version = "1.0"
