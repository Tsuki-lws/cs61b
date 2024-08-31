import java.util.ArrayList;
import java.util.List;

public class ArrayDeque61B<T> implements Deque61B<T>{
    private T[] item;
    private int size;
    private int nextFirst;
    private int nextLast;

    public ArrayDeque61B() {
        this.item = (T[])(new Object[8]);
        size = 0;
        nextFirst = 0;
        nextLast = 1;
    }
    public int getItemLength() {
        return item.length;
    }

    private void resize(int capacity) {
        T[] a = (T[])new Object[capacity];

        for (int i = 0; i < size; i++) {
            a[i] = get(i);
        }
        item = a;
    }
    @Override
    public void addFirst(T x) {
        if(size == item.length - 1) {
            resize(item.length * 2);
            nextFirst = item.length - 1;
            nextLast = size;
        }
        item[nextFirst] = x;
        size++;
        nextFirst = Math.floorMod(nextFirst - 1,item.length);
    }

    @Override
    public void addLast(T x) {
        if(size == item.length - 1) {
            resize(item.length * 2);
            nextFirst = item.length - 1;
            nextLast = size;
        }
        item[nextLast] = x;
        size++;
        nextLast = Math.floorMod(nextLast + 1,item.length);
    }

    @Override
    public List<T> toList() {
        List<T> returnList = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            returnList.add(get(i));
        }
        return returnList;
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public T removeFirst() {
        if((double) size / item.length < 0.25) {
            resize(item.length / 2);
            nextFirst = item.length - 1;
            nextLast = size;
        }
        int removeIndex = Math.floorMod(nextFirst + 1,item.length);
        T removeValue = item[removeIndex];
        item[removeIndex] = null;
        nextFirst++;
        size--;
        return removeValue;
    }

    @Override
    public T removeLast() {
        if((double) size / item.length < 0.25) {
            resize(item.length / 2);
            nextFirst = item.length - 1;
            nextLast = size;
        }
        int removeIndex = Math.floorMod(nextLast - 1,item.length);
        T removeValue = item[removeIndex];
        item[removeIndex] = null;
        nextLast--;
        size--;
        return removeValue;
    }

    @Override
    public T get(int index) {
        if (index < 0 || index >= size) {
            return null;
        }
        int actualIndex = Math.floorMod(index + nextFirst + 1,item.length);
        return item[actualIndex];
    }

    @Override
    public T getRecursive(int index) {
        throw new UnsupportedOperationException("No need to implement getRecursive for proj 1b");
    }
}
