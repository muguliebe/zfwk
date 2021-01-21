import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import java.util.Date

plugins {
    id("org.springframework.boot") version "2.4.1"
    id("io.spring.dependency-management") version "1.0.10.RELEASE"
    kotlin("jvm") version "1.4.21"
    kotlin("plugin.spring") version "1.4.21"
    id("com.jfrog.bintray") version "1.8.4"
    `maven-publish`
}

group = "zfwk"
version = "0.1.3"
java.sourceCompatibility = JavaVersion.VERSION_1_8

repositories {
    mavenLocal()
    mavenCentral()
    jcenter()
}

dependencies {

    // spring
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-jdbc")
    developmentOnly("org.springframework.boot:spring-boot-devtools")

    // kotlin
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation("org.jetbrains.kotlin:kotlin-noarg")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")

    // test
    testImplementation("org.springframework.boot:spring-boot-starter-test")
}


////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// build
tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "1.8"
    }
}

val sourcesJar by tasks.creating(Jar::class) {
    archiveClassifier.set("sources")
    from(sourceSets.getByName("main").allSource)
}

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// test
tasks.withType<Test> {
    useJUnitPlatform()
}

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// publish by bintray
val artifactName = project.name
val artifactGroup = project.group.toString()
val artifactVersion = project.version.toString()

val pomUrl = "https://github.com/muguliebe/zfwk"
val pomScmUrl = "https://github.com/muguliebe/zfwk"
val pomIssueUrl = "https://github.com/muguliebe/zfwk/issues"
val pomDesc = "https://github.com/muguliebe/zfwk"

val githubRepo = "muguliebe/zfwk"
val githubReadme = "README.md"

val pomLicenseName = "MIT"
val pomLicenseUrl = "https://opensource.org/licenses/mit-license.php"
val pomLicenseDist = "repo"

val pomDeveloperId = "zany"
val pomDeveloperName = "zany"

tasks.getByName<org.springframework.boot.gradle.tasks.bundling.BootJar>("bootJar") {
    enabled = false
}

tasks.getByName<Jar>("jar") {
    enabled = true
}

// for jar produce
//configurations {
//    listOf(apiElements, runtimeElements).forEach {
//        val jar by tasks
//        it.get().outgoing.artifacts.removeIf { it.buildDependencies.getDependencies(null).contains(jar) }

//        it.get().outgoing.artifact(tasks.bootJar)
//    }
//}

publishing {
    publications {
        create<MavenPublication>("zfwk") {
            groupId = artifactGroup
            artifactId = artifactName
            version = artifactVersion
            from(components["java"])

            artifact(sourcesJar)

            pom.withXml {
                asNode().apply {
                    appendNode("description", pomDesc)
                    appendNode("name", rootProject.name)
                    appendNode("url", pomUrl)
                    appendNode("licenses").appendNode("license").apply {
                        appendNode("name", pomLicenseName)
                        appendNode("url", pomLicenseUrl)
                        appendNode("distribution", pomLicenseDist)
                    }
                    appendNode("developers").appendNode("developer").apply {
                        appendNode("id", pomDeveloperId)
                        appendNode("name", pomDeveloperName)
                    }
                    appendNode("scm").apply {
                        appendNode("url", pomScmUrl)
                    }
                }
            }

        }
    }
}

bintray {
    user = project.findProperty("bintrayUser").toString()
    key = project.findProperty("bintrayKey").toString()
    publish = true

    setPublications("zfwk")

    pkg.apply {
        println("packaging..")
        repo = "zfwk"
        name = artifactName
        userOrg = "zany"
        githubRepo = githubRepo
        vcsUrl = pomScmUrl
        description = "framework by spring boot"
        setLabels("spring", "spring boot", "framework")
        setLicenses("MIT")
        desc = description
        websiteUrl = pomUrl
        issueTrackerUrl = pomIssueUrl
        githubReleaseNotesFile = githubReadme

        version.apply {
            name = artifactVersion
            desc = pomDesc
            released = Date().toString()
            vcsTag = artifactVersion
        }

        println("name:$name")
        println("group:$artifactGroup")
    }
}
