package types

sealed class Type

data class Atom(val s: String): Type() {
    init {
        if (!isAtom(s)) {
            error("Error: $s is not an atom")
        }
    }

    override fun toString() = s.substring(1)

    companion object {
        private val atomRegex = Regex("'[[\\p{L}-]]+")

        fun isAtom(s: String): Boolean {
            return s.matches(atomRegex)
        }
    }
}

data class Pair(val engine: Type, val caboose: Type): Type() {
    override fun toString() = "($engine . $caboose)"
}

sealed class Bool: Type() {
    object True: Bool() {
        override fun toString() = "#t"
    }

    object False: Bool() {
        override fun toString() = "#f"
    }
}

class Fn(val f: (List<Type>) -> Type): Type()
