import org.gradle.api.artifacts.dsl.RepositoryHandler

fun RepositoryHandler.addProjectDefaults() {
    google()
    mavenCentral()
    jcenter()
}