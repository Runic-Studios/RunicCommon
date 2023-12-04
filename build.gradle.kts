plugins {
    id("com.github.johnrengelman.shadow")
}

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
    implementation(commonLibs.log4japi)
    implementation(commonLibs.log4jcore)
    implementation(commonLibs.jgit)
}
