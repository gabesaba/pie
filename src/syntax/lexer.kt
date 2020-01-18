package syntax

sealed class Token {
    object LParen: Token() {
        override fun toString() = "LPAREN"
    }

    object RParen: Token() {
        override fun toString() = "RPAREN"
    }

    // Better name?
    data class Leaf(val s: String): Token()
}

fun tokenize(inp: String): TokenStream {
    var i = 0
    val tokens = mutableListOf<Token>()

    while (i <= inp.lastIndex) {
        when {
            inp[i] == '(' -> {
                tokens.add(Token.LParen)
                i += 1
            }
            inp[i] == ')' -> {
                tokens.add(Token.RParen)
                i += 1
            }
            inp[i].isWhitespace() -> i += 1
            else -> {
                var leaf = ""
                while (inp[i] != ')' && !inp[i].isWhitespace()) {
                    leaf += inp[i]
                    i += 1
                }
                tokens.add(Token.Leaf(leaf))
            }
        }
    }
    return TokenStream(tokens)
}

class TokenStream(private val list: List<Token>) {

private var i = 0
    fun get(): Token {
        val n = list[i]
        i += 1
        return n
    }

    fun unget() {
        i -= 1
    }
}
