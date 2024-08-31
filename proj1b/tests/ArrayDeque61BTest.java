import jh61b.utils.Reflection;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.List;

import static com.google.common.truth.Truth.assertThat;
import static com.google.common.truth.Truth.assertWithMessage;

public class ArrayDeque61BTest {

     @Test
     @DisplayName("ArrayDeque61B has no fields besides backing array and primitives")
     void noNonTrivialFields() {
         List<Field> badFields = Reflection.getFields(ArrayDeque61B.class)
                 .filter(f -> !(f.getType().isPrimitive() || f.getType().equals(Object[].class) || f.isSynthetic()))
                 .toList();

         assertWithMessage("Found fields that are not array or primitives").that(badFields).isEmpty();
     }
    @Test
    /* In this test, we have three different assert statements that verify that addFirst works correctly. */
    public void addFirstTestBasic() {
        Deque61B<String> lld1 = new ArrayDeque61B<>();

        lld1.addFirst("back"); // after this call we expect: ["back"]
        assertThat(lld1.toList()).containsExactly("back").inOrder();

        lld1.addFirst("middle"); // after this call we expect: ["middle", "back"]
        assertThat(lld1.toList()).containsExactly("middle", "back").inOrder();

        lld1.addFirst("front"); // after this call we expect: ["front", "middle", "back"]
        assertThat(lld1.toList()).containsExactly("front", "middle", "back").inOrder();

         /* Note: The first two assertThat statements aren't really necessary. For example, it's hard
            to imagine a bug in your code that would lead to ["front"] and ["front", "middle"] failing,
            but not ["front", "middle", "back"].
          */
    }

    @Test
     /* In this test, we use only one assertThat statement. IMO this test is just as good as addFirstTestBasic.
        In other words, the tedious work of adding the extra assertThat statements isn't worth it. */
    public void addLastTestBasic() {
        Deque61B<String> lld1 = new ArrayDeque61B<>();

        lld1.addLast("front"); // after this call we expect: ["front"]
        lld1.addLast("middle"); // after this call we expect: ["front", "middle"]
        lld1.addLast("back"); // after this call we expect: ["front", "middle", "back"]
        assertThat(lld1.toList()).containsExactly("front", "middle", "back").inOrder();
    }

    @Test
    /* This test performs interspersed addFirst and addLast calls. */
    public void addFirstAndAddLastTestAndResizeTest() {
        Deque61B<Integer> lld1 = new ArrayDeque61B<>();

         /* I've decided to add in comments the state after each call for the convenience of the
            person reading this test. Some programmers might consider this excessively verbose. */
        lld1.addLast(0);   // [0]
        lld1.addLast(1);   // [0, 1]
        lld1.addFirst(-1); // [-1, 0, 1]
        lld1.addLast(2);   // [-1, 0, 1, 2]
        lld1.addFirst(-2); // [-2, -1, 0, 1, 2]
        lld1.addFirst(0); // [0, -2, -1, 0, 1, 2]
        lld1.addFirst(0); // [0, 0, -2, -1, 0, 1, 2]
        lld1.addFirst(0); // [0, 0, 0, -2, -1, 0, 1, 2]
        lld1.addFirst(0); // [0, 0, 0, 0, -2, -1, 0, 1, 2]
        lld1.addFirst(0); // [0, 0, 0, 0, 0, -2, -1, 0, 1, 2]
        lld1.addFirst(0); // [0, 0, 0, 0, 0, 0, -2, -1, 0, 1, 2]

        assertThat(lld1.toList()).containsExactly(0, 0, 0, 0, 0, 0,-2, -1, 0, 1, 2 ).inOrder();
    }

    // Below, you'll write your own tests for ArrayDeque61B.
    @Test
    public void isEmptyTest() {
        Deque61B<Integer> lld1 = new ArrayDeque61B<>();
        assertThat(lld1.isEmpty()).isTrue();
        lld1.addFirst(1);
        assertThat(lld1.isEmpty()).isFalse();
    }

    @Test
    public void sizeZeroTest() {
        Deque61B<String> lld1 = new ArrayDeque61B<>();
        int expectedSize = 0;
        int actualSize = lld1.size();
        assertThat(actualSize).isEqualTo(expectedSize);
    }

    @Test
    public void sizeTwoTest() {
        Deque61B<String> lld1 = new ArrayDeque61B<>();
        lld1.addFirst("hu");
        lld1.addLast("binZe");
        int expectedSize = 2;
        int actualSize = lld1.size();
        assertThat(actualSize).isEqualTo(expectedSize);
    }

    @Test
    public void getTest() {
        Deque61B<String> lld1 = new ArrayDeque61B<>();

        lld1.addLast("front"); // after this call we expect: ["front"]
        lld1.addLast("middle"); // after this call we expect: ["front", "middle"]
        lld1.addLast("back"); // after this call we expect: ["front", "middle", "back"]

        assertThat(lld1.get(0)).isEqualTo("front");
        assertThat(lld1.get(4)).isEqualTo(null);
        assertThat(lld1.get(-1)).isEqualTo(null);
    }
    @Test
    public void getRecursiveTest(){
        Deque61B<String> lld1 = new ArrayDeque61B<>();

        lld1.addLast("front"); // after this call we expect: ["front"]
        lld1.addLast("middle"); // after this call we expect: ["front", "middle"]
        lld1.addLast("back"); // after this call we expect: ["front", "middle", "back"]

        assertThat(lld1.getRecursive(1)).isEqualTo("middle");
        assertThat(lld1.getRecursive(4)).isEqualTo(null);
        assertThat(lld1.getRecursive(-1)).isEqualTo(null);
    }
    @Test
    public void removeFirstAndRemoveLastTest(){
        Deque61B<Integer> lld1 = new ArrayDeque61B<>();

        lld1.addLast(0);   // [0]
        lld1.addLast(1);   // [0, 1]
        lld1.addFirst(-1); // [-1, 0, 1]
        lld1.addLast(2);   // [-1, 0, 1, 2]
        lld1.addFirst(-2); // [-2, -1, 0, 1, 2]

        assertThat(lld1.toList()).containsExactly(-2, -1, 0, 1, 2).inOrder();

        int first = lld1.removeFirst();
        assertThat(lld1.toList()).containsExactly(-1,0,1,2).inOrder();
        assertThat(first).isEqualTo(-2);

        int last = lld1.removeLast();
        assertThat(lld1.toList()).containsExactly(-1,0,1).inOrder();
        assertThat(last).isEqualTo(2);

        assertThat(lld1.size()).isEqualTo(3);
    }
    @Test
    public void resize_up_and_resize_downTest() {
        Deque61B<Integer> lld1 = new ArrayDeque61B<>();

        lld1.addLast(0);   // [0]
        lld1.addLast(1);   // [0, 1]
        lld1.addFirst(-1); // [-1, 0, 1]
        lld1.addLast(2);   // [-1, 0, 1, 2]
        lld1.addFirst(-2); // [-2, -1, 0, 1, 2]
        lld1.addFirst(0); // [0, -2, -1, 0, 1, 2]
        lld1.addFirst(0); // [0, 0, -2, -1, 0, 1, 2]
        lld1.addFirst(0); // [0, 0, 0, -2, -1, 0, 1, 2]
        lld1.addFirst(0); // [0, 0, 0, 0, -2, -1, 0, 1, 2]
        lld1.addFirst(0); // [0, 0, 0, 0, 0, -2, -1, 0, 1, 2]
        lld1.addFirst(0); // [0, 0, 0, 0, 0, 0, -2, -1, 0, 1, 2]

        assertThat(((ArrayDeque61B<?>)lld1).getItemLength()).isEqualTo(16);

        for(int i = 0; i < 9; i++) {
            lld1.removeFirst();
        }
        assertThat(((ArrayDeque61B<?>)lld1).getItemLength()).isEqualTo(8);
    }
}
