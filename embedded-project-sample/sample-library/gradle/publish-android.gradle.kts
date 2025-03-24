
apply(plugin = "maven-publish")

val javadocJar = tasks.getByName("javadocJar")
//val sourcesJar = tasks.getByName("sourcesJar")

configure<PublishingExtension> {
    repositories {
        maven {
            name = "Kotzilla"
            url = uri("https://repository.kotzilla.io/repository/kotzilla-platform/")
            credentials {
                username = findProperty("KOTZILLA_USER")?.toString() ?: System.getenv("KOTZILLA_USER") ?: ""
                password = findProperty("KOTZILLA_PWD")?.toString() ?: System.getenv("KOTZILLA_PWD") ?: ""
            }
        }
    }
    publications {
        register<MavenPublication>("release") {
            artifact(javadocJar)
//            artifact(sourcesJar)
            afterEvaluate {
                from(components["release"])
            }
            pom {
                name.set("Kotzilla")
                description.set("Kotzilla")
                url.set("https://kotzilla.io/")
                licenses {
                    license {
                        name.set("Kotzilla SDK Licence 1.0")
                        url.set("https://doc.kotzilla.io/docs/about/licence")
                    }
                }
                scm {
                    url.set("https://github.com/kotzilla-io/kotzilla-sdk-sample")
                    connection.set("https://github.com/kotzilla-io/kotzilla-sdk-sample.git")
                }
                developers {
                    developer {
                        name.set("Kotzilla")
                        email.set("contact@kotzilla.io")
                    }
                }
            }
        }
    }
}


//apply(from = file("../../gradle/signing.gradle.kts"))