package plugins

import io.gitlab.arturbosch.detekt.DetektPlugin
import io.gitlab.arturbosch.detekt.extensions.DetektExtension

apply<DetektPlugin>()

configure<DetektExtension> {
    source.setFrom(project.files("src/main/kotlin"))
    config.setFrom(files("$rootDir/.detekt/config.yml"))
    buildUponDefaultConfig = true
}
