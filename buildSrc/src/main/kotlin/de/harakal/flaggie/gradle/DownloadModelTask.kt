package de.harakal.flaggie.gradle

import okhttp3.OkHttpClient
import okhttp3.Request
import okio.buffer
import okio.sink
import org.gradle.api.DefaultTask
import org.gradle.api.GradleException
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.OutputFile
import org.gradle.api.tasks.TaskAction
import java.io.File

abstract class DownloadModelTask : DefaultTask() {

    @get:Input
    lateinit var sourceUrl: String

    @get:OutputFile
    val targetFile: File
        get() = File("${project.projectDir}/$target")

    lateinit var target: String

    init {
        group = "prepare"
        description = "Download Tensorflow model file"
    }

    @TaskAction
    fun run() {
        val client = OkHttpClient()

        val request = Request.Builder().url(sourceUrl).build()
        val response = client.newCall(request).execute()
        if (response.isSuccessful) {
            val sink = targetFile.sink().buffer()
            sink.writeAll(response.body!!.source())
            sink.close()
        } else {
            throw GradleException("Error downloaded file at $sourceUrl with code ${response.code}")
        }
    }
}
