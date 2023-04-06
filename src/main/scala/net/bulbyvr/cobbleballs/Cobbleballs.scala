package net.bulbyvr.cobbleballs

import net.fabricmc.api.ModInitializer
import com.cobblemon.mod.common.pokeball.PokeBall
import com.cobblemon.mod.common.item.*
import dev.architectury.registry.registries.Registries
import net.minecraft.util.Identifier
import net.minecraft.util.registry.Registry
object Cobbleballs extends ModInitializer {
  val MODID = "cobbleballs"

  def onInitialize(): Unit = {
    CobbleballsItems.register()
  }
}
