import pl.arapso.scaffoldings.scala.tutor.fpis.{ Cons, Nil, List}

val list = Cons("Dam", Cons("ia", Cons("n", Nil)))


val withoutDam = List.tail(list)
