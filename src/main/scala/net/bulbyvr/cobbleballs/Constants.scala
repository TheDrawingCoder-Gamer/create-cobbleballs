package net.bulbyvr.cobbleballs

import net.minecraft.util.Identifier

object Constants {
  case class BasicBall(id: Identifier, color: String)
  // THESE MUST BE IN THE SAME ORDER
  val apricornColors: List[String] = List("black", "blue", "green", "pink", "red", "white", "yellow")
  val balls: List[Identifier] = List("slate", "azure", "verdant", "roseate", "poke", "premier", "citrine").map(it => Identifier("cobblemon", s"${it}_ball"))
  val basicBalls: List[BasicBall] = apricornColors.zip(balls).map((l, r) => BasicBall(r, l))
}
