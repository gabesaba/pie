package syntax

sealed class Expression {
    data class S(val expressions: List<Expression>) : Expression()

    data class Leaf(val identifier: String) : Expression()
}


fun buildSyntaxTree(tokens: TokenStream): Expression {
    val lParen = tokens.get()
    if (lParen !is Token.LParen) {
        error("Syntax Error: expected ${Token.LParen}")
    }

    val expressionList = mutableListOf<Expression>()

    var token = tokens.get()
    while (true) {
        val currExpr = when (token) {
            is Token.LParen -> {
                tokens.unget()
                buildSyntaxTree(tokens)
            }
            is Token.Leaf -> {
                Expression.Leaf(token.s)
            }
            is Token.RParen -> {
                return Expression.S(expressionList)
            }
        }
        expressionList.add(currExpr)
        token = tokens.get()
    }
}
