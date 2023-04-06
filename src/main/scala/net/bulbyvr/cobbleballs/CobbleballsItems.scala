package net.bulbyvr.cobbleballs

import net.minecraft.util.registry.Registry
import com.cobblemon.mod.common.pokeball.PokeBall
import com.cobblemon.mod.common.item.PokeBallItem
import net.minecraft.item.Item
import dev.architectury.registry.registries.{RegistrySupplier, DeferredRegister}

object CobbleballsItems extends PainfulRegister[Item](Registry.ITEM_KEY) {

  private def pokeballItem(pokeBall: PokeBall): RegistrySupplier[PokeBallItem] = {
    val supplier = this.queue(pokeBall.getName.getPath, () => PokeBallItem(pokeBall))
    pokeBall.itemSupplier = supplier
    supplier
  }

  val STEEL_BALL = pokeballItem(Balls.STEEL_BALL)
}
