package datastructures.dictionaries;

import misc.exceptions.NotYetImplementedException;

import java.util.Iterator;

/**
 * @see IDictionary
 */
public class ArrayDictionary<K, V> implements IDictionary<K, V> {
    /*
    Warning:
    You may not rename this field or change its type.
    We will be inspecting it in our secret tests.

    Note: The field below intentionally omits the "private" keyword. By leaving off a specific
    access modifier like "public" or "private" it becomes package-private, which means anything in
    the same package can access it. Since our tests are in the same package, they will be able
    to test this property directly.
     */
    Pair<K, V>[] pairs;

    // You may add extra fields or helper methods though!

    public ArrayDictionary() {
        // TODO: your code here
        throw new NotYetImplementedException();
    }

    /**
     * This method will return a new, empty array of the given size that can contain `Pair<K, V>`
     * objects.
     *
     * Note that each element in the array will initially be null.
     */
    @SuppressWarnings("unchecked")
    private Pair<K, V>[] makeArrayOfPairs(int arraySize) {
        /*
        It turns out that creating arrays of generic objects in Java is complicated due to something
        known as "type erasure."

        We've given you this helper method to help simplify this part of your assignment. Use this
        helper method as appropriate when implementing the rest of this class.

        You are not required to understand how this method works, what type erasure is, or how
         arrays and generics interact. Do not modify this method in any way.
        */
        return (Pair<K, V>[]) (new Pair[arraySize]);
    }

    @Override
    public V get(K key) {
        // TODO: your code here
        throw new NotYetImplementedException();
    }

    @Override
    public V put(K key, V value) {
        // TODO: your code here
        throw new NotYetImplementedException();
    }

    @Override
    public V remove(K key) {
        // TODO: your code here
        throw new NotYetImplementedException();
    }

    @Override
    public boolean containsKey(K key) {
        // TODO: your code here
        throw new NotYetImplementedException();
    }

    @Override
    public int size() {
        // TODO: your code here
        throw new NotYetImplementedException();
    }

    @Override
    public Iterator<KVPair<K, V>> iterator() {
        // TODO: your code here
        throw new NotYetImplementedException();
    }

    @Override
    public String toString() {
        return super.toString();

        /*
        After you've implemented the iterator, comment out the line above and uncomment the line
        below to get a better string representation for objects in assertion errors and in the
        debugger.
        */

        // return IDictionary.toString(this);
    }

    private static class Pair<K, V> {
        public K key;
        public V value;

        // You may add constructors and methods to this class as necessary.
        public Pair(K key, V value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public String toString() {
            return String.format("%s=%s", this.key, this.value);
        }
    }

    private static class ArrayDictionaryIterator<K, V> implements Iterator<KVPair<K, V>> {
        // You'll need to add some fields

        public ArrayDictionaryIterator(/* The constructor will need some arguments too */) {
            // TODO: your code here
            throw new NotYetImplementedException();
        }

        @Override
        public boolean hasNext() {
            // TODO: your code here
            throw new NotYetImplementedException();
        }

        @Override
        public KVPair<K, V> next() {
            // TODO: your code here
            throw new NotYetImplementedException();
        }
    }
}
