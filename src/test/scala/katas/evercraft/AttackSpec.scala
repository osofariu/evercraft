package katas.evercraft

import org.scalatest.Matchers

class AttackSpec extends org.scalatest.path.FunSpec with Matchers {

  describe("Attack") {

    it("allows player to attack opponent with a 20-sided die") {
      Attack(Hero(), Hero(), 15)
    }

    it("wins attack when score beats opponent's armor class") {
      Attack(Hero(), Hero(), 11).wasAttackSuccessful shouldBe true
    }

    it("wins attack when score matches defender's armor class") {
      Attack(Hero(), Hero(), 10).wasAttackSuccessful shouldBe true
    }

    it("subtracts defender's hitPoints by one when hit is successful") {
      Attack(Hero(), Hero(), 10).defenderAfterHit.hitPoints shouldBe 4
    }

    it("subtracts defender's hitPoints by two when hit with a roll of 20 (critical hit)") {
      Attack(Hero(), Hero(), 20).defenderAfterHit.hitPoints shouldBe 3

    }

    it("attack is unsuccessful when score is less than defender's armor class") {
      val attackResult = Attack(Hero(), Hero(), 9)
      attackResult.wasAttackSuccessful shouldBe false
      attackResult.defenderAfterHit.hitPoints shouldBe 5
      attackResult.isDefenderDead shouldBe false
    }

    it("when hitPoints become zero, a character is dead") {
      val weakDefender = Hero(hitPoints = 1)
      val attack = Attack(Hero(), weakDefender, 10)
      attack.isDefenderDead shouldBe true
    }

    it("adds strength modifier to attack roll") {
      Attack(Hero(strength = 12), Hero(), 9).wasAttackSuccessful shouldBe true
    }

    it("adds attacker strength to damage dealt") {
      Attack(Hero(strength = 12), Hero(), 10).defenderAfterHit.hitPoints shouldBe 3
    }

    it("doubles strength modifier on critical hits") {
      Attack(Hero(strength = 12), Hero(), 20).defenderAfterHit.hitPoints shouldBe 1
    }

    it("minimum damage is always at least 1, even when strength modifier is negative") {
      Attack(Hero(strength = 8), Hero(), 11).defenderAfterHit.hitPoints shouldBe 4
    }

    it("adds dexterity modifier to armor class, to improve defender's defenses") {
      val attackResults = Attack(Hero(), Hero(dexterity = 12), 10)
      attackResults.wasAttackSuccessful shouldBe false
      attackResults.defenderAfterHit.dexterity.score shouldBe 12
    }

    it ("attacker's constitution modifier does not help attacker win attack") {
      val attackResult = Attack(Hero(constitution = 12), Hero(), 9)
      attackResult.wasAttackSuccessful shouldBe false
    }

    it ("adds attacker's constitution modifier to hit points") {
      val attackResult = Attack(Hero(constitution = 12), Hero(), 10)
      attackResult.defenderAfterHit.hitPoints shouldBe 3
    }

    it("critical hit does not double constitution on critical hits") {
      val attackResult = Attack(Hero(constitution = 12), Hero(), 20)
      attackResult.defenderAfterHit.hitPoints shouldBe 2
    }

    it("constitution modifier will not decrease hitPoints by less than one") {
      val attackResult = Attack(Hero(constitution = 8), Hero(), 10)
      attackResult.defenderAfterHit.hitPoints shouldBe 4
    }

    it("attacker gains 10 experience points when attack was successful") {
      Hero().experiencePoints shouldBe 0
      val attackResult = Attack(Hero(), Hero(), 10)
      attackResult.attackerAfterHit.experiencePoints shouldBe 10
    }
  }
}
