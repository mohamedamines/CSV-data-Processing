



dsddd




trait SalesReader {

  def readSales(): Seq[Sale]

}

case class Sale(date: String, product: String, price: Int, paymentType: String, country: String)
