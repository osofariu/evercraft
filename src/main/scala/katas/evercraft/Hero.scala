package katas.evercraft

import katas.evercraft.Alignment.Neutral

class Hero private (val name: String, val hitPoints: Int, val alignment: Alignment, val experiencePoints : Int,
                    val strength: Ability, val dexterity : Ability, val constitution : Ability) {
  def armorClass = 10
  def wisdom = Ability()
  def intelligence = Ability()
  def charisma = Ability()

  def copy(name: String = name, hitPoints : Int = hitPoints, alignment: Alignment = alignment, experiencePoints : Int = experiencePoints,
           strength: Ability = strength, dexterity : Ability = dexterity) = {
    new Hero(name, hitPoints,alignment, experiencePoints, strength, dexterity, constitution)
  }
}

object Hero {
  def apply(name: String ="Agnes", hitPoints: Int = 5, alignment: Alignment = Neutral, experiencePoints : Int = 0,
            strength: Ability = Ability(), dexterity: Ability = Ability(), constitution: Ability = Ability()): Hero = {
    new Hero(name = name, hitPoints = hitPoints, alignment = alignment, experiencePoints = experiencePoints,
      strength = strength, dexterity = dexterity, constitution = constitution)
  }
}