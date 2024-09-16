import org.gradle.api.DefaultTask
import org.gradle.api.file.DirectoryProperty
import org.gradle.api.tasks.InputDirectory
import org.gradle.api.tasks.Internal
import org.gradle.api.tasks.OutputDirectory
import org.gradle.api.tasks.TaskAction
import org.gradle.workers.WorkerExecutor

import javax.inject.Inject

class DownmixWavFiles extends DefaultTask {

    @Internal
    final WorkerExecutor workerExecutor

    @InputDirectory
    final DirectoryProperty srcDir = project.objects.directoryProperty()

    @OutputDirectory
    final DirectoryProperty destDir = project.objects.directoryProperty()

    @Inject
    DownmixWavFiles(WorkerExecutor workerExecutor) {
        this.workerExecutor = workerExecutor
    }

    @TaskAction
    void process() {
        def soxPath = System.env['PATH'].split(':').collect { dir ->
            new File(dir, 'sox')
        }.find { it.exists() }
        assert soxPath
        def workQueue = workerExecutor.processIsolation()
        project.fileTree(srcDir).include('**/*.wav').each { wavFile ->
            def destFile = destDir.file("$wavFile.name").get().asFile
            workQueue.submit(RunnableExec.class) { parameters ->
                parameters.commandLine = [
                        soxPath,
                        wavFile,
                        destFile,
                        'remix', 1
                ].collect { it.toString() }
            }
        }
    }
}
