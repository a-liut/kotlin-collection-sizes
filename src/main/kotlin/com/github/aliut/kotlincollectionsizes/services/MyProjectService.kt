package com.github.aliut.kotlincollectionsizes.services

import com.intellij.openapi.project.Project
import com.github.aliut.kotlincollectionsizes.MyBundle

class MyProjectService(project: Project) {

    init {
        println(MyBundle.message("projectService", project.name))
    }
}
