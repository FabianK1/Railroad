package railroad

import java.io.File
import java.io.StringReader
import java.nio.charset.StandardCharsets

import org.apache.commons.io.FileUtils

import net.nextencia.rrdiagram.grammar.model.BNFToGrammar
import net.nextencia.rrdiagram.grammar.model.Grammar
import net.nextencia.rrdiagram.grammar.model.Literal
import net.nextencia.rrdiagram.grammar.model.Rule
import net.nextencia.rrdiagram.grammar.rrdiagram.RRChoice
import net.nextencia.rrdiagram.grammar.rrdiagram.RRDiagram
import net.nextencia.rrdiagram.grammar.rrdiagram.RRDiagramToSVG
import net.nextencia.rrdiagram.grammar.rrdiagram.RRSequence
import net.nextencia.rrdiagram.grammar.rrdiagram.RRText
import viewsupport.ViewHTML

object RailRoadRepository extends ViewHTML {

  def main(args: Array[String]): Unit = {
    showHTML()
  }

  def htmlContent() = {
    xml.Unparsed(getSVG())
  }

  def run() = {

    val tmp = new File(System.getProperty("user.dir") + "/svg/railroad.svg")
    FileUtils.writeStringToFile(tmp, getSVG(), StandardCharsets.UTF_8, false)
    println("Written to " + tmp.getCanonicalPath())
  }

  protected def getSVG() = {
    val reader = new StringReader("")
    val converter = new BNFToGrammar()

    val grammer = new Grammar(new Rule("bo", new Literal("bla")))

    val rrDiagram = new RRDiagram(
      new RRSequence(
        new RRChoice(
          new RRText(RRText.Type.RULE, "AFO280/1", null),
          new RRText(RRText.Type.RULE, "AFO280/2", null)),
        new RRText(RRText.Type.LITERAL, "Puffer(10)", null),
        new RRText(RRText.Type.RULE, "AFO300/1", null),
        new RRText(RRText.Type.RULE, "AFO400/1", null)))

    val rrDiagramToSVG = new RRDiagramToSVG()
    rrDiagramToSVG.convert(rrDiagram)
  }

}
