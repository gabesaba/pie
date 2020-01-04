private val atomRegex = Regex("'[[a-z][-]]+")

fun isAtom(s: String): Boolean {
    return s.matches(atomRegex)
}
