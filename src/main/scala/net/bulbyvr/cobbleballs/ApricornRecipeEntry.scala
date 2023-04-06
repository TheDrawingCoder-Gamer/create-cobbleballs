package net.bulbyvr.cobbleballs

import com.simibubi.create.content.contraptions.components.deployer.DeployerApplicationRecipe
import com.simibubi.create.content.contraptions.processing.ProcessingRecipeBuilder
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider
import net.fabricmc.fabric.api.datagen.v1.{DataGeneratorEntrypoint, FabricDataGenerator}
import net.minecraft.data.server.recipe.RecipeJsonProvider
import net.minecraft.item.ItemStack
import net.minecraft.recipe.Ingredient
import net.minecraft.util.Identifier
import net.minecraft.util.registry.Registry

import java.util.function.Consumer
import scala.annotation.unused

private class ApricornRecipeGenerator(generator: FabricDataGenerator) extends FabricRecipeProvider(generator) {
  override def generateRecipes(exporter: Consumer[RecipeJsonProvider]): Unit = {
    for {
      color <- Constants.apricornColors
      kind <- List("normal", "great", "ultra")
    } {
      val ingot =
        kind match {
          case "normal" => Identifier("minecraft", "copper_ingot")
          case "great"  => Identifier("minecraft", "iron_ingot")
          case "ultra" => Identifier("minecraft", "gold_ingot")
          case _ => Identifier("minecraft", "dirt")
        }
      ProcessingRecipeBuilder[DeployerApplicationRecipe](it => DeployerApplicationRecipe(it), Identifier(Cobbleballs.MODID, s"${kind}_carved_${color}_apricorn_deploy"))
        .withItemIngredients(Ingredient.ofItems(Registry.ITEM.get(Identifier("cobblemon", s"${color}_apricorn"))), Ingredient.ofItems(Registry.ITEM.get(ingot)))
        .withSingleItemOutput(ItemStack(Registry.ITEM.get(Identifier(Cobbleballs.MODID, s"${kind}_carved_${color}_apricorn"))))
        .build(exporter)
    }
  }
}


@unused
object ApricornRecipeEntry extends DataGeneratorEntrypoint {
  override def onInitializeDataGenerator(fabricDataGenerator: FabricDataGenerator): Unit = {
    fabricDataGenerator.addProvider(it => ApricornRecipeGenerator(it))
  }
}
