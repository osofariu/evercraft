package katas.evercraft

case class Attack(attacker: Hero, defender: Hero, score: Int) {

  private val newAttackerLevel = 1 + (attacker.experiencePoints + 10) / 1000

  def attackWasSuccessful = score + (newAttackerLevel - 1) + attacker.strength  >= defender.armorClass + defender.dexterity

  def defenderIsDead = defenderAfterHit.hitPoints == 0

  def defenderAfterHit = {
    if (attackWasSuccessful) {
      def calculateDamage = {
        def criticalHitFactor = if (score == 20) 2 else 1
        def strengthFactor = Math.max(attacker.strength, 0)
        def constitutionFactor = Math.max(attacker.constitution, 0)
        criticalHitFactor * (1 + strengthFactor) + constitutionFactor
      }
      defender.copy(hitPoints = defender.hitPoints - calculateDamage)
    } else {
      defender
    }
  }

  def attackerAfterHit = {
    if (attackWasSuccessful) {
      def updateHitPoints = {
        def levelHasChanged: Boolean = newAttackerLevel > attacker.level
        if (levelHasChanged) attacker.hitPoints + attacker.level * 5 + attacker.constitution
        else attacker.hitPoints
      }
      attacker.copy(hitPoints = updateHitPoints, experiencePoints = attacker.experiencePoints + 10)
    }
    else attacker
  }
}
