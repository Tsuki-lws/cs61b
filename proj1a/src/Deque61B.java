import java.util.List;

/**
 * Created by hug on 2/4/2017. Methods are provided in the suggested order
 * that they should be completed.
 */
public interface Deque61B<T> {

    /**
     * 将 {@code x} 添加到双端队列的前面。假设 {@code x} 永远不会为 null。
     *
     * @param x item to add
     */
    void addFirst(T x);

    /**
     * 将 {@code x} 添加到双端队列的背面。假设 {@code x} 永远不会为 null。
     *
     * @param x item to add
     */
    void addLast(T x);

    /**
     * 返回 deque 的 List 副本。不改变 deque。
     *
     * @return a new list copy of the deque.
     */
    List<T> toList();

    /**
     * 如果 deque 为空，则返回。不改变 deque。
     *
     * @return {@code true} if the deque has no elements, {@code false} otherwise.
     */
    boolean isEmpty();

    /**
     * 返回 deque 的大小。不改变 deque。
     *
     * @return the number of items in the deque.
     */
    int size();

    /**
     * 删除并返回 deque 前面的元素（如果存在）。
     *
     * @return removed element, otherwise {@code null}.
     */
    T removeFirst();

    /**
     * 删除并返回 deque 后面的元素（如果存在）。
     *
     * @return removed element, otherwise {@code null}.
     */
    T removeLast();

    /**
     * Deque61B 抽象数据类型通常没有 get 方法，
     * 但是我们包含了这个额外的操作，以便为您提供一些
     * 额外的编程练习。迭代获取元素。返回
     * 如果 index 超出范围，则为 null。不改变 deque。
     *
     * @param index index to get
     * @return element at {@code index} in the deque
     */
    T get(int index);

    /**
     *从技术上讲，此方法不应位于界面中，但它就在这里
     * 使测试更好。以递归方式获取一个元素。如果满足以下条件，则返回 null
     * index 超出范围。不改变 deque。
     *
     * @param index index to get
     * @return element at {@code index} in the deque
     */
    T getRecursive(int index);
}
