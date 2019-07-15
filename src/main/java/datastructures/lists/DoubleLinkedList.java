package datastructures.lists;

import datastructures.EmptyContainerException;
import misc.exceptions.NotYetImplementedException;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

/**
 * Note: For more info on the expected behavior of your methods:
 * @see IList
 * (You should be able to control/command+click "IList" above to open the file from IntelliJ.)
 */
public class DoubleLinkedList<T> implements IList<T> {
    /*
    Warning:
    You may not rename these fields or change their types.
    We will be inspecting these in our secret tests.
    You also may not add any additional fields.

    Note: The fields below intentionally omit the "private" keyword. By leaving off a specific
    access modifier like "public" or "private" they become package-private, which means anything in
    the same package can access them. Since our tests are in the same package, they will be able
    to test these properties directly.
     */
    Node<T> front;
    Node<T> back;
    int size;

    public DoubleLinkedList() {
        this.front = null;
        this.back = null;
        this.size = 0;
    }

    @Override
    public void add(T item) {
        if (this.front == null) {
            this.front = new Node<T>(item);
        } else if(this.back == null) {
            this.front.next = new Node<T>(this.front, item, null);
        } else {
            this.back.next = new Node<T>(item);
            Node<T> temp = this.back;
            this.back = this.back.next;
            this.back.prev = temp;
        }
        this.size++;
    }

    @Override
    public T remove() {
        if (this.front == null) {
            throw new EmptyContainerException();
        } else if (this.back == null) {
            Node<T> temp = this.front;
            this.front = null;
            return temp.data;
        } else {
            Node<T> temp = this.back;
            this.back = this.back.prev;
            this.back.next = null;
            return temp.data;
        }
        this.size--;
    }

    @Override
    public T get(int index) {
        if (index >= 0) {
            Iterator<T> itr = this.iterator();
            T data = itr.next();
            for (int i = 0; i <= index; i++) {
                if (itr.hasNext()) {
                   data = itr.next();
                } else {
                    throw new IndexOutOfBoundsException();
                }
            }
            return data;
        } else {
            throw new IndexOutOfBoundsException();
        }
    }

    @Override
    public T set(int index, T item) {
        // if index is 0
            // front.data = item
        // if index is size - 1
            // back.data = item
        // else
            // loop to index
            // set data@index to item
        if (index == 0) {
            
        }
    }

    @Override
    public void insert(int index, T item) {
        if (index == 0) {
            this.front.prev = new Node<T>(item);
            Node<T> temp = this.front;
            this.front = this.front.prev;
            this.front.next = temp;
        } else if (index == this.size - 1) {
            this.back.next = new Node<T>(item);
            Node<T> temp = this.back;
            this.back = this.back.next;
            this.back.prev = temp;
        } else {
            Node<T> curr = this.front;
            for (int i = 0; i < index - 1; i++) {
                curr = curr.next;
            }
            Node<T> prevNode = curr;
            Node<T> nextNode = curr.next;
            prevNode.next = new Node<T>(item);
            Node<T> newNode = prevNode.next;
            newNode.next = nextNode;
            nextNode.prev = newNode;
            newNode.prev = prevNode;
        }
        this.size++;
    }

    @Override
    public T delete(int index) {
        // TODO: your code here
        throw new NotYetImplementedException();
    }

    @Override
    public int indexOf(T item) {
        // TODO: your code here
        throw new NotYetImplementedException();
    }

    @Override
    public int size() {
        // TODO: your code here
        throw new NotYetImplementedException();
    }

    @Override
    public boolean contains(T other) {
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

        // return IList.toString(this);
    }

    @Override
    public Iterator<T> iterator() {
        /*
        Note: we have provided a part of the implementation of an iterator for you. You should
        complete the methods stubs in the DoubleLinkedListIterator inner class at the bottom of
        this file. You do not need to change this method.
        */
        return new DoubleLinkedListIterator<>(this.front);
    }

    static class Node<E> {
        // You may not change the fields in this class or add any new fields.
        final E data;
        Node<E> prev;
        Node<E> next;

        Node(Node<E> prev, E data, Node<E> next) {
            this.data = data;
            this.prev = prev;
            this.next = next;
        }

        Node(E data) {
            this(null, data, null);
        }

        // Feel free to add additional constructors or methods to this class.
    }

    private static class DoubleLinkedListIterator<T> implements Iterator<T> {
        // You should not need to change this field, or add any new fields.
        private Node<T> next;

        public DoubleLinkedListIterator(Node<T> front) {
            // You do not need to make any changes to this constructor.
            this.next = front;
        }

        /**
         * Returns `true` if the iterator still has elements to look at;
         * returns `false` otherwise.
         */
        public boolean hasNext() {
            if (this.next == null) {
                return false;
            }
            return true;
        }

        /**
         * Returns the next item in the iteration and internally updates the
         * iterator to advance one element forward.
         *
         * @throws NoSuchElementException if we have reached the end of the iteration and
         *         there are no more elements to look at.
         */
        public T next() {
            if (this.hasNext()) {
                Node<T> temp = this.next;
                this.next = this.next.next;
                return temp.data;
            } else {
                throw new NoSuchElementException();
            }
            //throw new NotYetImplementedException();
        }
    }
}
