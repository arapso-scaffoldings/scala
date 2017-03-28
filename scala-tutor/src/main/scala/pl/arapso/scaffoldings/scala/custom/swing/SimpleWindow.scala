package pl.arapso.scaffoldings.scala.custom.swing

import scala.swing._
/**
  * Created by damian0o on 06.01.17.
  */
object SimpleWindow extends SimpleSwingApplication {

  override def top: MainFrame = new MainFrame{
      title = "Simple Application"
      contents = new Button{
        text = "Click me!"
      }
    }
}
