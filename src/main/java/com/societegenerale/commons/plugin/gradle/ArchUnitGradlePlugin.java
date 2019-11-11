package com.societegenerale.commons.plugin.gradle;

import org.gradle.api.*;


public class ArchUnitGradlePlugin implements Plugin<Project> {

    public void apply(Project project) {

        ArchUnitGradleConfig archUnitGradleConfig = project.getExtensions().create("archUnit", ArchUnitGradleConfig.class, project);

        Task archUnitTask=project.getTasks().create("checkRules", ArchUnitRulesTask.class, archUnitGradleConfig);

        archUnitTask.setGroup("verification");

        final Task testTask = project.getTasks().findByName("test");

        if (testTask==null){
            throw new GradleException("can't find the 'test' task on which archUnitGradle task will depend - please check Gradle java plugin is applied");
        }

        testTask.dependsOn(archUnitTask);

    }
}