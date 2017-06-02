import java.lang.reflect.Array;
import java.util.Iterator;

public class ReversedList<T> implements Iterable<T>{
    private int capacity;
    private T[] items;
    private int count;

    public ReversedList(){
        this.capacity = 2;
        @SuppressWarnings("unchecked")
        this.items = (T[]) new Object[this.capacity];
        this.count = 0;
    }

    public void add(T item){
        if(this.count==this.capacity){
            this.grow();
        }

        this.items[this.count] = item;
        this.count++;

    }

    public void removeAt(int index){
        int newCount = count--;
        @SuppressWarnings("unchecked")
        T[] copy = (T[]) new Object[this.items.length];

        index = count-index;

        for(int i = 0; i<index; i++){
            copy[i] = this.items[i];
        }

        for(int i = index+1; i<this.count; i++){
            copy[i-1] = this.items[i];
        }

        this.items = copy;

        capacity -= 2;

    }

    public int count(){
        return this.count;
    }

    public int capacity(){
        return this.capacity;
    }

    public T get(int index){
        return this.items[this.count - index - 1];
    }


    private void grow(){
	@SuppressWarnings("unchecked")
        T[] copy = (T[]) new Object[this.items.length*2];
        System.arraycopy(this.items, 0, copy, 0, this.items.length);

        capacity *= 2;

        this.items = copy;
    }

    @Override
    public Iterator<T> iterator() {
        return new RevListIterator();
    }

    private final class RevListIterator implements Iterator<T> {
        private int index = this.count - 1;

        @Override
        public boolean hasNext() {
            return this.index>-1;
        }

        @Override
        public T next() {
            return items[this.index--];
        }
    }

}
