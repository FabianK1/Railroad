package viewsupport

import com.sun.org.apache.xml.internal.security.utils.XMLUtils
import org.apache.commons.io.FileUtils
import java.nio.file.Files
import java.io.File
import java.nio.charset.StandardCharsets
import scala.xml.NodeSeq
import scala.xml.Elem

abstract class ViewHTML {
  System.setProperty("java.awt.headless", "false")
  protected val base = Files.createTempDirectory("dashboard-rendering").toFile().getAbsolutePath()

  def showHTML() = {
    val file = generate()
    println("Viewing " + file.getCanonicalPath())
    java.awt.Desktop.getDesktop().open(file)
  }

  protected def generate() = {
    write("index.html", doctype() + "\r\n" + xml.Xhtml.toXhtml(html()))
  }

  protected def html() =
    {
      <html>
        <head>
          <title>Dashboard</title>
          <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
          <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous"/>
          <style>
            {
              "body { font-size: 10px !important; text-align: center; } "
            }
          </style>
        </head>
        <body style="margin-top: 50px;">
          { htmlContent() }
        </body>
      </html>
    }
  protected def htmlContent(): NodeSeq

  protected def write(filename: String, content: String) = {
    val file = new File(base + "/" + filename)
    FileUtils.writeStringToFile(file, content + "\n", StandardCharsets.UTF_8, false)
    file
  }

  protected def doctype() = {
    "<!DOCTYPE html>"
  }
}
