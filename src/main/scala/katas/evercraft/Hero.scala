package katas.evercraft

import katas.evercraft.Alignment.Neutral

case class Hero (name: String = "Agnes", hitPoints: Int = 5, alignment: Alignment = Neutral, experiencePoints : Int = 0,
                         strength: Ability = Ability(), dexterity : Ability = Ability(), constitution : Ability = Ability()) {
  def armorClass = 10
  def wisdom = Ability()
  def intelligence = Ability()
  def charisma = Ability()
}
