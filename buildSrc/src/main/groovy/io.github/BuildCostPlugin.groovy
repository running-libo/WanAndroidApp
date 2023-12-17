package io.github

import org.gradle.api.Plugin
import org.gradle.api.Project

/**
 * 编译耗时插件
 */
class BuildCostPlugin implements Plugin<Project> {

    @Override
    void apply(Project project) {
        //gradle添加task执行监听
        project.getGradle().addListener(new BuildTimeListener())
    }
}