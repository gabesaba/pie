package functions

import types.Bool
import types.Fn
import types.Nat
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

@kotlin.ExperimentalUnsignedTypes
val add = Fn {
    var out: UInt = 0u
    for (t in it) {
        if (t is Nat) {
            out += t.n
        } else {
            error("Invalid type ${t::class} encountered when adding")
        }
    }
    Nat(out)
}

private fun List<Type>.assertSize(expectedSize: Int, funName: String) {
    if (this.size != expectedSize) {
        error("Expected only $expectedSize argument(s) to $funName")
    }
}
