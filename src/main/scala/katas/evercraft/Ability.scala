package katas.evercraft

class Ability(val score: Int) {

  def modifier() : Int = {
    Math.floor((score - 10) / 2.0).toInt
  }
}

object Ability {

  def apply(score: Int = 10): Ability = {
    new Ability(score)
  }

  implicit def abilityToNumeric(ability: Ability): Int = {
    ability.modifier()
  }

  implicit def numericToAbility(score: Int) : Ability = {
    Ability(score)
  }
}