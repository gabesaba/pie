package interpreter

import functions.car
import functions.cdr
import functions.cons
import functions.eq
import syntax.Expression
import syntax.buildSyntaxTree
import syntax.tokenize
import types.Atom
import types.Fn
import types.Type

class Interpreter() {
    var env = mutableMapOf(
        "cons" to cons,
        "car" to car,
        "cdr" to cdr,
        "eq?" to eq
    )

    fun parseAndEval(inp: String): String{
        val tokens = tokenize(inp)
        val tree = buildSyntaxTree(tokens)
        return eval(tree).toString()
    }

    private fun eval(exp: Expression): Type {
        when (exp) {
            is Expression.S -> {
                val exps = exp.expressions
                val fn = eval(exps[0])
                val args = exps.subList(1, exps.lastIndex + 1).map { eval(it) }
                return when (fn) {
                    is Fn -> {
                        fn.f(args)
                    }
                    else -> {
                        error("Expecting function as first argument in S-Expression")
                    }
                }
            }
            is Expression.Leaf -> {
                return when {
                    Atom.isAtom(exp.identifier) -> Atom(exp.identifier)
                    env.containsKey(exp.identifier.toLowerCase()) -> env[exp.identifier.toLowerCase()]!!
                    else -> error("Error: ${exp.identifier} is not in scope")
                }
            }
        }
    }
}
