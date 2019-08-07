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
            Node<T> temp = new Node<T>(this.front, item, null);
            this.front.next = temp;
            this.back = temp;
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
            this.size--;
            return temp.data;
        } else {
            Node<T> temp = this.back;
            this.back = this.back.prev;
            this.back.next = null;
            // get rid of back if there are only two nodes
            if (this.size == 2) {
                this.back = null;
            }
            this.size--;
            return temp.data;
        }
    }

    @Override
    public T get(int index) {
        if (index >= 0) {
            Iterator<T> itr = this.iterator();
            T data = itr.next();
            for (int i = 0; i < index; i++) {
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

    // index -> index of DoubleLinkedList to set
    // item -> new data to replace old DoubleLinkedList data
    @Override
    public T set(int index, T item) {
        if (index < 0) {
            throw new IndexOutOfBoundsException();
        } else if (index >= this.size) {
            throw new IndexOutOfBoundsException();
        }
        if (index == 0) {
            T oldData = this.front.data;
            Node<T> newFront = new Node<T>(item);
            Node<T> remainder = this.front.next;
            if (remainder != null) {
                remainder.prev = newFront;
            }
            this.front = newFront;
            this.front.next = remainder;
            return oldData;
        } else if (index == this.size - 1) { // index is back of list
            T oldData = this.back.data;
            Node<T> newBack = new Node<T>(item);
            Node<T> remainder = this.back.prev;
            remainder.next = newBack;
            this.back = newBack;
            this.back.prev = remainder;
            return oldData;
        } else {
            // iterate to index
            Node<T> temp = this.front;
            for (int i = 0; i < index; i++) {
                temp = temp.next;
            }
            T oldData = temp.data;
            Node<T> newNode = new Node<T>(item);
            temp.prev.next = newNode;
            temp.next.prev = newNode;
            newNode.prev = temp.prev;
            newNode.next = temp.next;
            temp.prev = null;
            temp.next = null;
            return oldData;
        }
    }

    @Override
    public void insert(int index, T item) {
        if (index < 0 || index > this.size) {
            throw new IndexOutOfBoundsException();
        }
        if (index == 0) {
            if (this.front == null) {
                this.front = new Node<T>(item);
            } else {
                this.front.prev = new Node<T>(item);
                Node<T> temp = this.front;
                this.front = this.front.prev;
                this.front.next = temp;
            }
        } else if (index == this.size) {
            if (this.back == null) {
                this.back = new Node<T>(item);
                // iterate to one before back
                Node<T> temp = this.front;
                while (temp.next != null) {
                    temp = temp.next;
                }
                temp.next = this.back;
                this.back.prev = temp;
            } else {
                this.back.next = new Node<T>(item);
                Node<T> temp = this.back;
                this.back = this.back.next;
                this.back.prev = temp;
            }
        } else if (index == this.size - 1) {
            Node<T> curr = this.back.prev;
            Node<T> newNode = new Node<T>(item);
            newNode.next = this.back;
            newNode.prev = curr;
            curr.next = newNode;
            this.back.prev = newNode;

        } else {
            if (index > this.size/2) {
                Node<T> curr = this.back;
                // iterate to one after index
                for (int i = this.size; i > index; i--) {
                    curr = curr.prev;
                }
                Node<T> newItem = new Node<T>(item);
                Node<T> prevNode = curr.prev;
                prevNode.next = newItem;
                newItem.prev = prevNode;
                newItem.next = curr;
                curr.prev = newItem;
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
        }
        this.size++;
    }

    @Override
    public T delete(int index) {
        if (index < 0 || index >= this.size()) {
            throw new IndexOutOfBoundsException();
        }
        this.size--;
        Node<T> curr = this.front;
        for (int i = 0; i < index; i++) {
            curr = curr.next;
        }
        Node<T> prev = curr.prev;
        Node<T> next = curr.next;
        curr.next = null;
        curr.prev = null;
        prev.next = next;
        next.prev = prev;
        return curr.data;
    }

    @Override
    public int indexOf(T item) {
        Node<T> curr = this.front;
        for (int i = 0; i < this.size; i++) {
            if (item != null) {
                if (curr.data != null) {
                    if (curr.data.equals(item)) {
                        return i;
                    }
                }
                // else it is not null. move on to next node
            } else { // given item is null
                if (curr.data == null) {
                    return i;
                }
            }
            curr = curr.next;
        }
        return -1;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public boolean contains(T other) {
        Node<T> curr = this.front;
        for (int i = 0; i < this.size; i++) {
            if (other != null) {
                if (curr.data != null) {
                    if (curr.data.equals(other)) {
                        return true;
                    }
                }
                // else it is not equal and dont do anything
            } else { // other is null
                if (curr.data == null) {
                    return true;
                }
            }
            curr = curr.next;
        }
        return false;
    }

    @Override
    public String toString() {
//        return super.toString();

        /*
        After you've implemented the iterator, comment out the line above and uncomment the line
        below to get a better string representation for objects in assertion errors and in the
        debugger.
        */

        return IList.toString(this);
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
        }
    }
}
