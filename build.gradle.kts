plugins {
    `java-library`
    `maven-publish`
    id("com.github.johnrengelman.shadow") version "8.1.1"
}

group = "com.runicrealms.plugin"
version = "1.0-SNAPSHOT"
val artifactName = "common"

dependencies {
    // No shadow
    compileOnly(commonLibs.spigot)
    compileOnly(commonLibs.paper)

    // Shadow
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
            artifactId = artifactName
            version = "1.0-SNAPSHOT"
            from(components["java"])
        }
    }
}