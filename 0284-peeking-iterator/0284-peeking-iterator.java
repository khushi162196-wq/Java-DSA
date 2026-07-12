import java.util.Iterator;

// Java Iterator interface assume karte hain
class PeekingIterator implements Iterator<Integer> {
    private Iterator<Integer> it;
    private Integer nextElement; // buffer

    public PeekingIterator(Iterator<Integer> iterator) {
        // constructor me iterator initialize karo
        this.it = iterator;
        // pehla element pehle se le lo
        if(it.hasNext()) {
            nextElement = it.next();
        }
    }
	
    // Returns the next element in the iteration without advancing the iterator.
    public Integer peek() {
        return nextElement;
    }
	
    // hasNext() and next() should behave the same as in the Iterator interface.
    // next() returns the next element in the iteration and advances the iterator
    @Override
    public Integer next() {
        Integer res = nextElement; // jo cache me hai wo do
        // cache ko update karo
        if(it.hasNext()) {
            nextElement = it.next();
        } else {
            nextElement = null;
        }
        return res;
    }
	
    @Override
    public boolean hasNext() {
        return nextElement!= null;
    }
}