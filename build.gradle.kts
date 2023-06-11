val artifactName = "common"
val rrGroup: String by rootProject.extra
val rrVersion: String by rootProject.extra

plugins {
    `java-library`
    `maven-publish`
    id("com.github.johnrengelman.shadow") version "8.1.1"
}

group = rrGroup
version = rrVersion

dependencies {
    // No shadow
    compileOnly(commonLibs.spigot)
    compileOnly(commonLibs.paper)
    compileOnly(commonLibs.luckperms)

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
    implementation(commonLibs.log4japi)
    implementation(commonLibs.log4jcore)
}

java {
    toolchain.languageVersion.set(JavaLanguageVersion.of(17))
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            groupId = rrGroup
            artifactId = artifactName
            version = rrVersion
            from(components["java"])
        }
    }
}