package net.bulbyvr.cobbleballs

import net.minecraft.util.registry.Registry
import com.cobblemon.mod.common.pokeball.PokeBall
import com.cobblemon.mod.common.item.PokeBallItem
import net.minecraft.item.{Item, ItemGroup}
import dev.architectury.registry.registries.{DeferredRegister, RegistrySupplier}
import net.fabricmc.fabric.api.item.v1.FabricItemSettings
import net.minecraft.util.Identifier

object CobbleballsItems  {

  private val deferredRegistry = DeferredRegister.create(Cobbleballs.MODID, Registry.ITEM_KEY)
  private def pokeballItem(pokeBall: PokeBall): RegistrySupplier[PokeBallItem] = {
    val supplier = deferredRegistry.register(pokeBall.getName, () => PokeBallItem(pokeBall))
    pokeBall.itemSupplier = supplier
    supplier
  }
  private def item(name: String, item: Item): RegistrySupplier[Item] =
    deferredRegistry.register(name, () => item)
  private val colors = List("black", "blue", "red", "pink", "green", "yellow", "white")
  private def apricornColored(name: String => String, item: => Item): Unit = {
    for (color <- colors) {
      deferredRegistry.register(name(color), () => item)
    }
  }
  // val STEEL_BALL = pokeballItem(Balls.STEEL_BALL)
  private val settings = FabricItemSettings().group(ItemGroup.MISC)
  apricornColored(it => s"normal_carved_${it}_apricorn", Item(settings))
  apricornColored(it => s"great_carved_${it}_apricorn", Item(settings))
  apricornColored(it => s"ultra_carved_${it}_apricorn", Item(settings))

  def register(): Unit = deferredRegistry.register()
}
