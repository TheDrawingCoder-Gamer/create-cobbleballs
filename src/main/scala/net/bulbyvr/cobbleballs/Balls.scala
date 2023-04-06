package net.bulbyvr.cobbleballs

import com.cobblemon.mod.common.pokeball.PokeBall
import com.cobblemon.mod.common.api.pokeball.catching.CatchRateModifier
import com.cobblemon.mod.common.api.pokeball.catching.CaptureEffect
import com.cobblemon.mod.common.api.pokeball.catching.modifiers.*
import net.minecraft.util.Identifier
import scala.jdk.CollectionConverters.*
object Balls {
  private def createDefault(name: String, modifier: CatchRateModifier = MultiplierModifier(1F, (_, _) => true), effects: List[CaptureEffect] = List()): PokeBall = {
    val identifier = Identifier(Cobbleballs.MODID, name)
    val model2d = s"${Cobbleballs.MODID}:${name}#inventory"
    val model3d = s"${Cobbleballs.MODID}:${name}_model#inventory"
    PokeBall(identifier, modifier,  effects.asJava, model2d, model3d)
  }
  val STEEL_BALL: PokeBall = createDefault("steel_ball", MultiplierModifier(1.5f, (_, _) => true))

}
