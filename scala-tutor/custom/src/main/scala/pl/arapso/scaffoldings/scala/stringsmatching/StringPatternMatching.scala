package pl.arapso.scaffoldings.scala.stringsmatching


object StringPatternMatching {

  def main(args: Array[String]): Unit = {

    val SubdomainPattern = """^\*\.(\S+)$""".r

    def inDomain(x: String): String => Boolean = (y: String) => {
      x match {
        case SubdomainPattern(domain, _*) => {
          (domain equals y) || (y endsWith ("." + domain))
        }
        case _ => x equals y
      }
    }

    assert(inDomain("arapso.pl")("arapso.pl"))
    assert(inDomain("*.arapso.pl")("sub.arapso.pl"))

  }


}
