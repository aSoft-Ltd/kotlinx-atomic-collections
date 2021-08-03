package kotlinx.atomic.collections

interface MutableAtomicCollection<E, C : MutableCollection<E>> : AtomicCollection<E, C>, MutableCollection<E> {
    fun <T> doAction(run: C.() -> T): T
    override fun iterator(): MutableIterator<E> = atomic.value.iterator()
}