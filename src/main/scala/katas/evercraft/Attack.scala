package katas.evercraft

class Attack(attacker: Hero, defender: Hero, score: Int) {

  def wasAttackSuccessful = score + attacker.strength  >= defender.armorClass + defender.dexterity

  def damageToDefender : Int = {
    def criticalHitFactor = if (score == 20) 2 else 1
    criticalHitFactor * (1 + attacker.strength).max(1) + attacker.constitution
  }

  def isDefenderDead = defenderAfterHit.hitPoints == 0

  def defenderAfterHit = {
    if (wasAttackSuccessful) {
      defender.copy(hitPoints = defender.hitPoints - damageToDefender, dexterity = defender.dexterity)
    } else {
      defender
    }
  }

  def attackerAfterHit = {
    if (wasAttackSuccessful) attacker.copy(experiencePoints = attacker.experiencePoints + 10)
    else attacker
  }
}

object Attack {
  def apply(attacker: Hero, defender: Hero, score: Int): Attack = {
    new Attack(attacker: Hero, defender: Hero, score: Int)
  }
}
