import java.util.ArrayList;
import java.util.List;

public class LinkedListDeque61B<T> implements Deque61B<T> {
    private  Node sentinel;
    private int size;
    public LinkedListDeque61B() {
        sentinel = new Node(null, null, null);
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
        size = 0;
    }

    @Override
    public void addFirst(T x) {
        Node first = new Node(sentinel, x, sentinel.next);
        sentinel.next.prev = first;
        sentinel.next = first;
        size++;
    }

    @Override
    public void addLast(T x) {
        Node last = new Node(sentinel.prev,x,sentinel);
        sentinel.prev.next = last;
        sentinel.prev = last;
        size++;
    }

    @Override
    public List<T> toList() {
        List<T> returnList = new ArrayList<>();
        Node p = sentinel.next;
        while(p != sentinel){
            returnList.add(p.item);
            p = p.next;
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
        if(isEmpty())
            return null;
        T returnItem = sentinel.next.item;
        sentinel.next.next.prev = sentinel;
        sentinel.next = sentinel.next.next;
        size--;
        return returnItem;
    }

    @Override
    public T removeLast() {
        if(isEmpty())
            return null;
        T returnItem = sentinel.prev.item;
        sentinel.prev.prev.next = sentinel;
        sentinel.prev = sentinel.prev.prev;
        size--;
        return returnItem;
    }

    @Override
    public T get(int index) {
        if(index < 0 || index >= this.size)
            return null;
        T returnItem = null;
        int number = 0;
        Node p = sentinel.next;
        while (p != sentinel){
            if(number == index){
                returnItem = p.item;
            }
            p = p.next;
            number++;

        }
        return returnItem;
    }

    @Override
    public T getRecursive(int index) {
        return getRecursive(index,sentinel.next);
    }
    private T getRecursive(int index,Node p){
        if (index < 0 || index >= this.size) {
            return null;
        }
        if (index == 0) {
            return p.item;
        }
        return getRecursive(index - 1,p.next);
    }

    private class Node {
        public Node prev;
        public T item;
        public Node next;

        public Node(Node prev, T item, Node next) {
            this.prev = prev;
            this.item = item;
            this.next = next;
        }
    }
}
