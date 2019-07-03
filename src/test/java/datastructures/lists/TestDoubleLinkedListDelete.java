package datastructures.lists;

import datastructures.lists.DoubleLinkedList.Node;
import misc.BaseTest;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import static datastructures.lists.TestDoubleLinkedList.makeBasicList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;

/**
 * This class should contain all the tests you implement to verify that your `delete` method behaves
 * as specified. Each test should run quickly; if your tests take longer than 1 second to run, they
 * may get timed out on the runners and during grading.
 */
@Tag("project1")
@TestMethodOrder(MethodOrderer.Alphanumeric.class)
public class TestDoubleLinkedListDelete extends BaseTest {

    @Test
    void testExample() {
        /*
        Feel free to modify or delete this dummy test.

        Below is an example of using casting, so that Java lets us access
        the specific fields of DoubleLinkedList. If you wish, you may
        use this syntax to access the internal pointers within
        DoubleLinkedList objects. Being able to check the internal pointers
        will more easily let you be thorough in your tests. For reference, our
        secret tests will be checking that the internal pointers are correct to more
        easily check your solution.
         */
        IList<String> list = makeBasicList();

        Node<String> front = ((DoubleLinkedList<String>) list).front;
        Node<String> back = ((DoubleLinkedList<String>) list).back;
        assertThat(front.next, is(back.prev));
        assertThat(front.prev, is(nullValue()));
        assertThat(back.next, is(nullValue()));
    }
}
