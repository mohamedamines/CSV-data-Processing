import org.scalatest.{BeforeAndAfter, FunSuite}
import org.scalatest.Matchers._

class SalesStatisticsComputerTest extends FunSuite with BeforeAndAfter {

  var statistics: SalesStatisticsComputer = _

  before {
    val salesReader = new SalesCSVReader("src/main/resources/SalesJan2009.csv")
    statistics = new SalesStatisticsComputer(salesReader)
  }

  test("get total number of sales") {
    statistics.getTotalNumberOfSales shouldBe 50
  }

  test("get average sales price grouped by payment type") {
    val avgPrices = statistics.getAvgSalePricesGroupedByPaymentType()

    avgPrices("Mastercard") shouldBe 1200
    avgPrices("Visa") shouldBe 1800
    avgPrices("Amex") shouldBe 2000
    avgPrices("Diners") shouldBe 1200
  }

  test("get number of sales groups by day") {
    val salesByDay = statistics.getNumberOfSalesGroupedByDay()

    salesByDay should have size (10)
    salesByDay("1/1") shouldBe 3
    salesByDay("1/2") shouldBe 9
    salesByDay("1/3") shouldBe 5
    salesByDay("1/4") shouldBe 6
    salesByDay("1/5") shouldBe 7
    salesByDay("1/6") shouldBe 7
    salesByDay("1/7") shouldBe 4
    salesByDay("1/8") shouldBe 5
    salesByDay("1/9") shouldBe 2
    salesByDay("1/10") shouldBe 2
  }

  test("get total number of sales made abroad and total value of them") {
    val (numberOfSales, total) = statistics.getTotalNumberAndPriceOfSalesMadeAbroad()

    numberOfSales shouldBe 21
    total shouldBe 30000
  }

}
