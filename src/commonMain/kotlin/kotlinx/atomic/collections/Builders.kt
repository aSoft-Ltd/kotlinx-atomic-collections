package kotlinx.atomic.collections

fun <E> mutableAtomicListOf(vararg elements: E): MutableList<E> {
    return MutableAtomicList(elements.toMutableList())
}