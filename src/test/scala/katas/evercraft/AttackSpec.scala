package katas.evercraft

import org.scalatest.Matchers

class AttackSpec extends org.scalatest.path.FunSpec with Matchers {

  describe("Attack") {

    val attacker = Hero(name = "Bully")
    val defender = Hero(name = "Rose")

    it("allows player to attack opponent with a 20-sided die") {
      Attack(attacker, defender, 15)
    }

    it("wins attack when score beats opponent's armor class") {
      Attack(attacker, defender, 11).wasAttackSuccessful shouldBe true
    }

    it("wins attack when score matches defender's armor class") {
      Attack(attacker, defender, 10).wasAttackSuccessful shouldBe true
    }

    it("subtracts defender's hitPoints by one when hit is successful") {
      Attack(attacker, defender, 10).defenderAfterHit.hitPoints shouldBe 4
    }

    it("subtracts defender's hitPoints by two when hit with a roll of 20 (critical hit)") {
      Attack(attacker, defender, 20).defenderAfterHit.hitPoints shouldBe 3

    }

    it("attack is unsuccessful when score is less than defender's armor class") {
      val result = Attack(attacker, defender, 9)
      result.wasAttackSuccessful shouldBe false
      result.defenderAfterHit.hitPoints shouldBe 5
      result.isDefenderDead shouldBe false
    }

    it("when hitPoints become zero, a character is dead") {
      val weakDefender = Hero(name = "WeakAsWater", hitPoints = 1)
      val attack = Attack(attacker, weakDefender, 10)
      attack.isDefenderDead shouldBe true
    }

    it("adds strength modifier to attack roll") {
      val strongerAttacker = Hero(name = "Bully", strength = 12)
      Attack(strongerAttacker, defender, 9).wasAttackSuccessful shouldBe true
    }

    it("adds strength to damage dealt") {
      val strongerAttacker = Hero(name = "Bully", strength = 12)
      Attack(strongerAttacker, defender, 10).defenderAfterHit.hitPoints shouldBe 3
    }

    it("doubles strength modifier on critical hits") {
      val strongerAttacker = Hero(name = "Bully", strength = 12)
      Attack(strongerAttacker, defender, 20).defenderAfterHit.hitPoints shouldBe 1
    }

    it("minimum damage is always at least 1, even when strength modifier is negative") {
      val weakAttacker = Hero(name = "Weakly", strength = 8)
      val weakDefender = Hero(name = "WeakAsWater", hitPoints = 1)
      Attack(weakAttacker, weakDefender, 11).defenderAfterHit.hitPoints shouldBe 0
    }

    it("adds dexterity modifier to armor class, to improve defender's defenses") {
      val skilledDefender = Hero(name = "Agnes", dexterity = 12)
      val attackResults = Attack(attacker, skilledDefender, 10)
      attackResults.wasAttackSuccessful shouldBe false
      attackResults.defenderAfterHit.dexterity.score shouldBe 12
    }

    it ("adds constitution modifier to hit points") {
      val strongAttacker = Hero(constitution = 12)
      val attackResult = Attack(strongAttacker, defender, 9)
      attackResult.wasAttackSuccessful shouldBe true
      attackResult.defenderAfterHit.hitPoints shouldBe 4
    }

    it("attacker gains 10 experience points when attack was successful") {
      attacker.experiencePoints shouldBe 0
      val attackResult = Attack(attacker, defender, 10)
      attackResult.attackerAfterHit.experiencePoints shouldBe 10
    }
  }
}
