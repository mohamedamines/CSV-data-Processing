
class SalesStatisticsComputer(val salesReader: SalesReader) {

  val sales = salesReader.readSales

  def getTotalNumberOfSales(): Int = sales size

  /**
    * Creates and returns a [[Map]] where the key is a payment type and the value is the
    * average sale price for all sales made using that payment type.
    *
    * @return The map with the data grouped by payment type.
    */
  def getAvgSalePricesGroupedByPaymentType(): Map[String, Double] = {
    def avg(salesOfAPaymentType: Seq[Sale]): Double =
      salesOfAPaymentType.map(_.price).sum / salesOfAPaymentType.size

    sales.groupBy(_.paymentType).mapValues(avg(_))
  }


  /**
    * Creates and returns a [[Map]] where the key is a given day in the format month/day
    * and the value is the number of sales made in the day.
    *
    * @return The map with the data grouped by day.
    */
  def getNumberOfSalesGroupedByDay(): Map[String, Int] = {
    def extractDay(sale: Sale): String = {
      val parts = sale.date.split("/")
      parts(0) + "/" + parts(1)
    }

    sales.groupBy(extractDay(_)).mapValues(_.length)
  }

  /**
    * @return A tuple where the first value is the number of sales made out of USA and
    *         the second value is the average price of these sales.
    */
  def getTotalNumberAndPriceOfSalesMadeAbroad(): (Int, Int) = {
    val filtered = sales.filter(_.country != "United States")
    (filtered.size, filtered.map(_.price).sum)
  }

}
