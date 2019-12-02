package de.harakal.flaggie.gradle

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.register

open class FlaggiePlugin : Plugin<Project> {
    override fun apply(project: Project) : Unit = project.run {
        tasks.register<DownloadModelTask>("downloadModel") {
            sourceUrl = "https://storage.googleapis.com/download.tensorflow.org/models/tflite/posenet_mobilenet_v1_100_257x257_multi_kpt_stripped.tflite"
            target = "src/main/assets/posenet_model.tflite"
        }
    }
}