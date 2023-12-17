package io.github

import org.gradle.BuildListener
import org.gradle.BuildResult
import org.gradle.api.Task
import org.gradle.api.execution.TaskExecutionListener
import org.gradle.api.initialization.Settings
import org.gradle.api.invocation.Gradle
import org.gradle.api.tasks.TaskState

/**
 * 记录build过程中各个task的耗时，并写入build/trace/buildtime_HH_mm_ss.log文件中
 * 使用TaskExecutionListener，可以监控每个task任务的开始和结束执行回调
 * 使用BuildListener，监控编译过程
 */
class BuildTimeListener implements TaskExecutionListener, BuildListener {
    private int taskStartTime = 0
    private int taskExecutTime = 0
    private int buildStartTime = 0
    private int taskCounts = 0
    private StringBuilder stringBuilder = new StringBuilder()
    private String BUILD_COST_PATH = ".buildcost"

    BuildTimeListener() {
        buildStartTime = System.currentTimeMillis()
    }

    @Override
    void beforeExecute(Task task) {
        taskStartTime = System.currentTimeMillis()
    }

    @Override
    void afterExecute(Task task, TaskState taskState) {
        taskExecutTime = System.currentTimeMillis() - taskStartTime
        stringBuilder.append("task: " + task.name + " " + " 耗时：" + taskExecutTime + "ms" + "\n")
        taskCounts++
    }

    @Override
    void settingsEvaluated(Settings settings) {

    }

    @Override
    void projectsLoaded(Gradle gradle) {
        stringBuilder.append("各个project配置完成")
    }

    @Override
    void projectsEvaluated(Gradle gradle) {
        println("各个project执行完成")
    }

    @Override
    void buildFinished(BuildResult buildResult) {
        int buildCost = System.currentTimeMillis() - buildStartTime
        stringBuilder.append("整个编译过程完成，总共耗时: ${buildCost}" + "\n")
        stringBuilder.append("总共执行了 ${taskCounts} 个任务")
        println(stringBuilder.toString())
        writeToFile(stringBuilder.toString())
    }

    /**
     * 编译结束时，将整个过程信息写入build目录下文件日志中
     */
    private void writeToFile(String text) {
        File outPutFile = new File(BUILD_COST_PATH)
        if (!outPutFile.exists()) {
            outPutFile.mkdir()
        }
        File file = new File(BUILD_COST_PATH + File.separator + "buildTimeLog" + "_" + System.currentTimeMillis() + ".log")
        file.write(text, true)
    }
}
