plugins {
    `java-library`
    `maven-publish`
    id("com.github.johnrengelman.shadow") version "7.1.2"
}

group = "com.runicrealms.plugin"
version = "1.0-SNAPSHOT"

dependencies {
    implementation(commonLibs.jedis)
    implementation(commonLibs.acf)
    implementation(commonLibs.taskchain)
    implementation(commonLibs.springdatamongodb)
    implementation(commonLibs.mongodbdrivercore)
    implementation(commonLibs.mongodbdriversync)
    implementation(commonLibs.configme)
    implementation(commonLibs.apachecommonslang)
    implementation(commonLibs.apachecommonsmath)
    implementation(commonLibs.apachecommonspool)
    implementation(commonLibs.httpclient)
    implementation(commonLibs.commonsio)
    implementation(commonLibs.spark)
    implementation(commonLibs.menus)
    implementation(commonLibs.jda)
}

java {
    toolchain.languageVersion.set(JavaLanguageVersion.of(17))
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            groupId = "com.runicrealms.plugin"
            artifactId = "items"
            version = "1.0-SNAPSHOT"
            from(components["java"])
        }
    }
}

//tasks.withType<com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar> {
//    relocate("co.aikar.commands", "com.runicrealms.libs.acf")
//    relocate("co.aikar.locales", "com.runicrealms.libs.locales")
//    relocate("co.aikar.taskchain", "com.runicrealms.libs.taskchain")
//}

tasks.register("wrapper")
tasks.register("prepareKotlinBuildScriptModel")