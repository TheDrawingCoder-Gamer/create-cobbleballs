plugins {
    id("java")
    id("dev.architectury.loom") version("0.12.0-SNAPSHOT")
    id("architectury-plugin") version("3.4-SNAPSHOT")
    id("scala")
}

group = "io.github.thedrawingcoder-gamer"
version = "1.0-SNAPSHOT"

architectury {
    platformSetupLoomIde()
    fabric()
}

loom {
    silentMojangMappingsLicense()

    mixin {
        defaultRefmapName.set("mixins.${project.name}.refmap.json")
    }
}

repositories {
    mavenCentral()
    maven(url = "https://dl.cloudsmith.io/public/geckolib3/geckolib/maven/")
    maven("https://maven.impactdev.net/repository/development/")
    maven("https://s01.oss.sonatype.org/content/repositories/snapshots/")
}

dependencies {
    minecraft("com.mojang:minecraft:1.19.2")
    mappings("net.fabricmc:yarn:1.19.2+build.28:v2")

    modImplementation("net.fabricmc:fabric-loader:0.14.14")

    modImplementation("net.fabricmc:fabric-language-kotlin:1.9.3+kotlin.1.8.20")
    modImplementation(fabricApi.module("fabric-command-api-v2", "0.75.1+1.19.2"))
    modImplementation("dev.architectury", "architectury-fabric", "6.5.69")
    modImplementation("com.cobblemon:fabric:1.3.1+1.19.2-SNAPSHOT")
    modImplementation("io.github.thedrawingcoder-gamer:fabric-language-scala-cats:0.1.0-2-SNAPSHOT")
}

