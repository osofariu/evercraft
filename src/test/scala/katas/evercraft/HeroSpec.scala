package katas.evercraft

import katas.evercraft.Alignment.{Good, Evil, Neutral}
import org.scalatest.Matchers

class HeroSpec extends org.scalatest.path.FunSpec with Matchers {

  describe("a Hero") {

    val myHero = Hero(name = "Sue", alignment = Neutral)

    it("has a name") {
      myHero.name shouldBe "Sue"
    }

    it("has an alignment") {
      myHero.alignment shouldBe Neutral
      Hero(alignment = Evil).alignment shouldBe Evil
      Hero(alignment = Good).alignment shouldBe Good
    }

    it("has armor class and hit points") {
      myHero.armorClass shouldBe 10
      myHero.hitPoints shouldBe 5
    }

    it("has abilities: Strength, Dexterity, Constitution, Wisdom, Intelligence, Charisma") {
      myHero.strength.score shouldBe 10
      myHero.dexterity.score shouldBe 10
      myHero.constitution.score shouldBe 10
      myHero.wisdom.score shouldBe 10
      myHero.intelligence.score shouldBe 10
      myHero.charisma.score shouldBe 10
    }
  }
}
