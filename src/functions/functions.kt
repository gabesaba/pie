package functions

import types.Bool
import types.Fn
import types.Pair
import types.Type

val cons = Fn {
    it.assertSize(2, "cons")
    Pair(it[0], it[1])
}

val car = Fn {
    it.assertSize(1, "car")

    val arg = it[0]
    if (arg !is Pair) {
        error("Expected arg to be of type Pair by car")
    } else {
        arg.engine
    }
}

val cdr = Fn {
    it.assertSize(1, "cdr")

    val arg = it[0]
    if (arg !is Pair) {
        error("Expected arg to be of type Pair by cdr")
    } else {
        arg.caboose
    }
}

val eq = Fn {
    it.assertSize(2, "eq?")
    if (it[0] == it[1]) {
        Bool.True
    } else {
        Bool.False
    }
}

private fun List<Type>.assertSize(expectedSize: Int, funName: String) {
    if (this.size != expectedSize) {
        error("Expected only $expectedSize argument(s) to $funName")
    }
}
