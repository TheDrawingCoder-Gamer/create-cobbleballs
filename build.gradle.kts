plugins {
    id("java")
    id("dev.architectury.loom") version("0.12.0-SNAPSHOT")
    id("architectury-plugin") version("3.4-SNAPSHOT")
    id("scala")
    kotlin("jvm") version ("1.7.10")
}

group = "io.github.thedrawingcoder-gamer"
version = properties["mod_version"]!!

architectury {
    platformSetupLoomIde()
    fabric()
}

loom {
    silentMojangMappingsLicense()

    mixin {
        defaultRefmapName.set("mixins.${project.name}.refmap.json")
    }

    runs {
        create("datagenClient") {
            client()
            name("Data Generation")
            vmArg("-Dfabric-api.datagen")
            vmArg("-Dfabric-api.datagen.output-dir=${file("src/main/generated")}")
            vmArg("-Dfabric-api.datagen.modid=${properties["mod_id"]}")
            runDir("runDatagen")
        }
        this.getByName("client") {
            runDir("run")
        }
    }

}

repositories {
    mavenCentral()
    maven(url = "https://dl.cloudsmith.io/public/geckolib3/geckolib/maven/")
    maven("https://maven.impactdev.net/repository/development/")
    maven("https://s01.oss.sonatype.org/content/repositories/snapshots/")
    maven(url = "https://mvn.devos.one/snapshots/") // Create
    maven(url = "https://jitpack.io/") // Mixin Extras, Fabric ASM
    maven(url = "https://cursemaven.com") // Forge Config API Port
    maven(url = "https://maven.tterrag.com/") // Flywheel
    maven(url = "https://maven.jamieswhiteshirt.com/libs-release") // Reach Entity Attributes
    maven(url = "https://api.modrinth.com/maven") // LazyDFU
    maven( url = "https://maven.terraformersmc.com/releases/") // Mod Menu
}

tasks.withType<ProcessResources> {
    filesMatching("fabric.mod.json") {
        expand(properties)
    }
}

sourceSets {
    main {
        resources {
            srcDirs.add(file("src/main/generated"))
        }
    }
}
dependencies {
    minecraft("com.mojang:minecraft:1.19.2")
    mappings("net.fabricmc:yarn:1.19.2+build.28:v2")

    modImplementation("net.fabricmc:fabric-loader:0.14.14")

    modImplementation("net.fabricmc:fabric-language-kotlin:1.9.3+kotlin.1.8.20")
    modImplementation("net.fabricmc.fabric-api:fabric-api:${properties["fabric_version"]}")
    modImplementation("dev.architectury", "architectury-fabric", "6.5.69")
    modImplementation("com.cobblemon:fabric:1.3.1+1.19.2-SNAPSHOT")
    modImplementation("io.github.thedrawingcoder-gamer:fabric-language-scala-cats:0.1.0-3-SNAPSHOT")
    modImplementation("com.simibubi.create:create-fabric-1.19.2:${properties["create_version"]}")

   	modLocalRuntime("maven.modrinth:lazydfu:${properties["lazydfu_version"]}")
	// modLocalRuntime("com.terraformersmc:modmenu:${properties["modmenu_version"]}")
}

