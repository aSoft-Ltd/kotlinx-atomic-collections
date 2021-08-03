package kotlinx.atomic.collections

import kotlinx.atomicfu.AtomicRef

interface AtomicCollection<E, C : Collection<E>> : Collection<E> {
    val atomic: AtomicRef<C>

    override val size: Int
        get() = atomic.value.size

    override fun contains(element: E): Boolean

    override fun containsAll(elements: Collection<E>): Boolean

    override fun isEmpty(): Boolean

    override fun iterator(): Iterator<E>
}