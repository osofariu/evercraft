package katas.evercraft

import org.scalatest.{Matchers, WordSpec}

class AttackWordSpec extends WordSpec with Matchers {

  "An Atack" when {

    "attacker is stronger" should {
      "succeed" in {
        Attack(Hero(), Hero(), 11).attackWasSuccessful shouldBe true
      }
    }

    "score matches defender's armor class" should {
      "succeed" in {
        Attack(Hero(), Hero(), 10).attackWasSuccessful shouldBe true
      }
    }

    "is successful" should {
      "lower defender's score" in {
        Attack(Hero(), Hero(), 10).defenderAfterHit.hitPoints shouldBe 4
      }
    }

    "performed with a roll of 20" should {
      "subtract defender's hitPoints by two " in {
        Attack(Hero(), Hero(), 20).defenderAfterHit.hitPoints shouldBe 3
      }
    }

    "attacker's score is less than defender's armor class" should {
      "fail" in {
        val attackResult = Attack(Hero(), Hero(), 9)
        attackResult.attackWasSuccessful shouldBe false
        attackResult.defenderAfterHit.hitPoints shouldBe 5
        attackResult.defenderIsDead shouldBe false
      }
    }

    "defender's hit points become zero" should {
      "kill the defender" in {
        val weakDefender = Hero(hitPoints = 1)
        val attack = Attack(Hero(), weakDefender, 10)
        attack.defenderIsDead shouldBe true
      }
    }

    "performed" should {
      "add strength modifier to attacker's attack roll" in {
        Attack(Hero(strength = 12), Hero(), 9).attackWasSuccessful shouldBe true
      }
    }

    "performed" should {
      "add attacker strength to damage dealt" in {
        Attack(Hero(strength = 12), Hero(), 10).defenderAfterHit.hitPoints shouldBe 3
      }
    }

    "performed" should {
      "double strength modifier on critical hits" in {
        Attack(Hero(strength = 12), Hero(), 20).defenderAfterHit.hitPoints shouldBe 1
      }
    }

    "performed with negative strength modifier" should {
      "deal a minimum damage of 1" in {
        Attack(Hero(strength = 8), Hero(), 11).defenderAfterHit.hitPoints shouldBe 4
      }
    }

    "performed" should {
      "add dexterity modifier to armor class, to improve defender's defenses" in {
        val attackResults = Attack(Hero(), Hero(dexterity = 12), 10)
        attackResults.attackWasSuccessful shouldBe false
        attackResults.defenderAfterHit.dexterity.score shouldBe 12
      }
    }

    "performed with a positive constitution modifier" should {
      "not" +
        " help attacker win attack" in {
        val attackResult = Attack(Hero(constitution = 12), Hero(), 9)
        attackResult.attackWasSuccessful shouldBe false
      }
    }

    "performed" should {
      "add attacker's constitution modifier to hit points" in {
        val attackResult = Attack(Hero(constitution = 12), Hero(), 10)
        attackResult.defenderAfterHit.hitPoints shouldBe 3
      }
    }

    "performed with a critical hit" should {
      "not double constitution " in {
        val attackResult = Attack(Hero(constitution = 12), Hero(), 20)
        attackResult.defenderAfterHit.hitPoints shouldBe 2
      }
    }

    "performed with a positive constitution modifier" should {
      "not decrease hitPoints by less than one\"" in {
        val attackResult = Attack(Hero(constitution = 8), Hero(), 10)
        attackResult.defenderAfterHit.hitPoints shouldBe 4
      }
    }

    "is successful" should {
      "gain the attacker 10 experience points" in {
        Hero().experiencePoints shouldBe 0
        val attackResult = Attack(Hero(), Hero(), 10)
        attackResult.attackerAfterHit.experiencePoints shouldBe 10
      }
    }

    "level is 2" should {
      "increase hit points by 5 plus Con modifier" in {
        val attackResult = Attack(Hero(experiencePoints = 990, constitution = 12), Hero(), 10)
        attackResult.attackerAfterHit.hitPoints shouldBe 11
      }
    }

    "achieving a new level" should {
      "increase attack roll" in {
        val attackResult = Attack(Hero(experiencePoints = 990, constitution = 12), Hero(), 9)
        attackResult.attackWasSuccessful shouldBe true
      }
    }
  }
}


