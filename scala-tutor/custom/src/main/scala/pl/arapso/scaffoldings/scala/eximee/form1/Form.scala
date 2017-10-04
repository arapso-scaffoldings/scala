package pl.arapso.scaffoldings.scala.eximee.form1


trait Form {
  def name: String

  def components: Graph[Component]

}

trait Graph[T >: Component] {

}

trait StartNode extends Node {

}

trait EndNode extends Node {

}

trait Node {

}

trait Component {
  def name: String
  def fields: List[Field]
}

trait Field {

  def name: String

  def value: Value

}

trait Value {
  def value: String
  def validators: List[Validator]
  def services: List[Service]

}

trait Validator {

}

class Service {

}
