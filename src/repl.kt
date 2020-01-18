import interpreter.Interpreter
import java.lang.Exception

fun main() {

    val interpreter = Interpreter()

    var currExpr = ""
    while (true) {
        val line = readLine()
        line ?: break

        currExpr += line

        if (currExpr.count { it == '('} == currExpr.count {it == ')'}) {
            try {
                println(interpreter.parseAndEval(currExpr))
            } catch (e: Exception) {
                println(e)
            }
            currExpr = ""
        }
    }
}
