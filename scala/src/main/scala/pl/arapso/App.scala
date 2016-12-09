package pl.arapso

/**
 * @author ${user.name}
 */
object App {

  def foo(x : Array[String]) = x.foldLeft("")((a,b) => a + b)
  
  def main(args : Array[String]) {
    println( "Hello World!" )
    println("concat arguments = " + foo(args))

    import java.nio.file.{Files, Paths}
    val byteArray = Files.readAllBytes(Paths.get("/home/damian/tmp/avro/pro/2016-11-20/tracker1000.avro"))

  }
}

