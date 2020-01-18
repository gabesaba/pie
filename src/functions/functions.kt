package functions

import types.Fn
import types.Pair

val cons = Fn {
    if (it.size != 2) {
        error("Expected 2 args to cons")
    }
    Pair(it[0], it[1])
}

val car = Fn {
    if (it.size != 1) {
        error("Expected only 1 arg to car")
    }

    val arg = it[0]
    if (arg !is Pair) {
        error("Expected arg to be of type Pair by car")
    } else {
        arg.engine
    }
}

val cdr = Fn {
    if (it.size != 1) {
        error("Expected only 1 argument to car")
    }

    val arg = it[0]
    if (arg !is Pair) {
        error("Expected arg to be of type Pair by cdr")
    } else {
        arg.caboose
    }
}
