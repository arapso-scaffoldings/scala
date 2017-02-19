import net.liftweb.json._
import pl.arapso.kafka.Event
implicit val formats = DefaultFormats

case class Person(firstName: String, lastName: String)

val json =
  """
    | {
    | "firstName": "Damian",
    | "lastName": "Ospara"
    | }
  """.stripMargin

val jsonAst = parse(json)
val person = jsonAst.extract[Person]

val eventJson =
  """
    |{"bid" : {"currency":"USD","price":3.183},
    |"deviceId":"4f3ae885-01ec-46f0-888f-d1ddee0ebfd8",
    |"bidId":"c151bfae-d245-4757-a082-a81b0ac50b40",
    |"device":{"category":"d"},
    |"creativeId":"32",
    |"campaignId": "444",
    |"event": "CLICK",
    |"ts": 1487507042}
    |
  """.stripMargin

val event = parse(eventJson).extract[Event]