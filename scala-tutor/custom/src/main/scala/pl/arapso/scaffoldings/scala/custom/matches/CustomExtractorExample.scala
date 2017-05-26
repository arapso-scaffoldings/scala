package pl.arapso.scaffoldings.scala.custom.matches


object CustomExtractorExample {

  trait User {
    def name: String
  }

  class FreeUser(val name: String) extends User

  object FreeUser {
    def unapply(user: FreeUser): Option[String] = Some(user.name)
  }
  
  class PremiumUser(val name: String) extends User

  object PremiumUser {
    def unapply(user: PremiumUser): Option[String] = Some(user.name)
  }

  object DamianExtractor {
    def unapply[T <: User](user: T): Option[String] = {
      if (user.name == "damian") Some(user.name)
      else None
    }
  }


  def main(args: Array[String]): Unit = {

    val free: User = new PremiumUser("damian")

    free match {
      case DamianExtractor(x) => println(s"Czesc $x")
      case FreeUser(x) => println(s"Hi $x")
      case PremiumUser(x) => println(s"Welcome premium user $x")
    }

  }

}
