package scratch

import org.scalatest.{Matchers, path}

class FunProgSpec extends path.FunSpec with Matchers {

  describe("Cafe") {

    it("has a payment system") {
      def cafe = Cafe(PaymentSystem("Boo"))
      cafe.ps.name shouldBe "Boo"
      cafe.ps.isInstanceOf[PaymentSystem] shouldBe true
    }

    it("can use payment system to create charges") {
      def cafe = Cafe(PaymentSystem("Boo"))
      cafe.charge(CreditCard("123")).isInstanceOf[Charge] shouldBe true
    }
  }

  describe("Charge") {

    it("allows you to pay for products") {
      def charge = Charge(CreditCard("JS123")).pay(Coffee("Folgers")).pay(Tea("Green"))
      charge.cc.number shouldBe "JS123"
      charge.items.size shouldBe 2
      charge.cost shouldBe 4.0
      charge.items.head.name shouldBe "Folgers"
      charge.items(1).name shouldBe "Green"
    }

    it("can be combined with other charge") {
      val charge1 = Charge(CreditCard("123"), List(Coffee("good1")))
      val charge2 = Charge(CreditCard("123"), List(Coffee("good2")))
      val charges = charge1.combine(charge2)
      charges.itemize.size shouldBe 2
      charges.itemize.unzip._2 shouldBe List(2.5, 2.5)
      charges.cost shouldBe 5.0
    }
  }
}

trait Product {
  def price: Double
  def name: String
}

case class Coffee(name: String) extends Product {
  override def price: Double = 2.5
  override def toString = "**Coffee*" + name + "**"
}

case class Tea(name: String) extends Product {
  override def price: Double = 1.5
  override def toString = "**Tea*" + name + "**"
}

case class CreditCard(number: String)

case class Cafe(ps: PaymentSystem) {
  def charge(cc: CreditCard): Charge = new Charge(cc)
}

case class Charge(cc: CreditCard, items: List[Product] = List()) {
  def combine(other: Charge) : Charge = {
    if (cc.number == other.cc.number) Charge(cc, items ::: other.items)
    else throw new Exception("Can't combine charges from different credit cards")
  }
  def pay(p: Product) : Charge = new Charge(cc, items :+ p)
  def cost : Double = items.foldLeft(0.0)((a, p) => a + p.price)
  def itemize = items.map(item => (cc, item.price))
}

case class PaymentSystem(name: String)
