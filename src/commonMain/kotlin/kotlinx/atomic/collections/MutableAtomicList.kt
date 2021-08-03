package kotlinx.atomic.collections

import kotlinx.atomicfu.AtomicRef
import kotlinx.atomicfu.atomic

class MutableAtomicList<E>(
    override val atomic: AtomicRef<MutableList<E>>
) : MutableAtomicCollection<E, MutableList<E>>, MutableList<E> {
    constructor(list: MutableList<E>) : this(atomic(list))

    override fun <T> doAction(run: MutableList<E>.() -> T): T {
        val list = atomic.value.toMutableList()
        val res = list.run()
        atomic.lazySet(list)
        return res
    }

    override fun get(index: Int): E = atomic.value[index]

    override fun indexOf(element: E): Int = atomic.value.indexOf(element)

    override fun lastIndexOf(element: E): Int = atomic.value.lastIndexOf(element)

    override fun listIterator(): MutableListIterator<E> = atomic.value.listIterator()

    override fun listIterator(index: Int): MutableListIterator<E> = atomic.value.listIterator(index)

    override fun set(index: Int, element: E): E = atomic.value.set(index, element)

    override fun subList(fromIndex: Int, toIndex: Int): MutableList<E> = atomic.value.subList(fromIndex, toIndex)

    override fun add(index: Int, element: E) = doAction { add(index, element) }

    override fun addAll(index: Int, elements: Collection<E>): Boolean = doAction { addAll(index, elements) }

    override fun removeAt(index: Int): E = doAction { removeAt(index) }

    override fun clear() {
        atomic.lazySet(mutableListOf())
    }

    override val size: Int
        get() = atomic.value.size

    override fun contains(element: E): Boolean = atomic.value.contains(element)

    override fun containsAll(elements: Collection<E>): Boolean = atomic.value.containsAll(elements)

    override fun isEmpty(): Boolean = atomic.value.isEmpty()

    override fun iterator(): MutableIterator<E> = atomic.value.iterator()

    override fun add(element: E): Boolean = doAction { add(element) }

    override fun addAll(elements: Collection<E>): Boolean = doAction { addAll(elements) }

    override fun remove(element: E): Boolean = doAction { remove(element) }

    override fun removeAll(elements: Collection<E>): Boolean = doAction { removeAll(elements) }

    override fun retainAll(elements: Collection<E>): Boolean = doAction { retainAll(elements) }
}