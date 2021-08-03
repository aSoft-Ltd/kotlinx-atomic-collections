import expect.CollectionAssertion
import kotlinx.atomic.collections.mutableAtomicListOf
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import test.asyncTest
import kotlin.test.Test

class ConcurrencyTest {
    val numbers = mutableAtomicListOf(1)

    @Test
    fun should_add_items_to_list() {
        numbers.add(1)
    }

    @Test
    fun should_add_items_in_different_threads() = asyncTest {
        val chars = mutableAtomicListOf('A')
        withContext(Dispatchers.Default) {
            chars.add('B')
        }
        withContext(Dispatchers.Unconfined) {
            chars.add('C')
        }
        CollectionAssertion(chars).toContain('A', 'B', 'C')
    }

    @Test
    fun should_add_multiple_items_in_different_threads() = asyncTest {
        val chars = mutableAtomicListOf('A')
        withContext(Dispatchers.Default) {
            chars.addAll(listOf('B'))
        }
        withContext(Dispatchers.Unconfined) {
            chars.addAll(listOf('C'))
        }
        CollectionAssertion(chars).toContain('A', 'B', 'C')
    }

    @Test
    fun should_remove_items_in_any_thread() = asyncTest {
        val chars = mutableAtomicListOf('A', 'B', 'C')
        withContext(Dispatchers.Default) {
            chars.remove('B')
        }
        withContext(Dispatchers.Unconfined) {
            chars.remove('C')
        }
        CollectionAssertion(chars).toContain('A')
    }
}