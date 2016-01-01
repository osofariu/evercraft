package katas.evercraft

import org.scalatest.Matchers

class AbilitySpec extends org.scalatest.path.FunSpec with Matchers {

  describe("Ability") {

    it("Allows implicit conversion to Int") {
      val myIntAbility : Int = Ability(20)
      myIntAbility shouldBe 5
    }

    it("modifier function works as expected") {
       def modifierValues =  Map(1 -> -5, 2 -> -4, 3-> -4, 4 -> -3, 5 -> -3,
        6 -> -2, 7 -> -2, 8 -> -1, 9 -> -1, 10 -> 0,
        11 -> 0, 12 -> 1, 13 -> 1, 14 -> 2, 15 -> 2,
        16 -> 3, 17 -> 3, 18 -> 4, 19 -> 4, 20 -> 5)

      (1 to 20).foreach { i =>
        assert(modifierValues(i) == Ability(i).modifier())
      }
    }
  }
}
