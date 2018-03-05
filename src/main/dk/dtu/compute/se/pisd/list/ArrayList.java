package dk.dtu.compute.se.pisd.list;

/**
 * A template for implementing a {@see dk.dtu.compute.se.pisd.list.List}
 * by using arrays. This is supposed to be implemented by
 * the students as a task of assignment 2 of the course. Note that
 * since Java arrays cannot be extended dynamically, the array must
 * be replaced at some points by a larger array. The {@see #sort()} method
 * can be implemented by using the bubble sort algorithm.
 * 
 * @author Ekkart Kindler, ekki@dtu.dk
 *
 */
public class ArrayList<E extends Comparable<E>> implements List<E> {
	
	int DEFAULTSIZE = 10;
	
	@SuppressWarnings("unchecked")
	E[] array = (E[]) new Comparable[DEFAULTSIZE];
	int size = 0;
	
	boolean isSorted = false;
	
	@SuppressWarnings("unchecked")
	@Override
	public void clear() {
		array = (E[]) new Comparable[DEFAULTSIZE];
	}
	
	@Override
	public int indexOf(E value) {
		if (!isSorted) {
			for (int i = 0; i < size; i++) {
				if (array[i] == value) {
					return i;
				}
			}
			return -1;
		} else {
			int lower = 0;
			int upper = size -1;
			int middle;
			
			while (lower <= upper) {
				middle = (lower + upper)/2;
				
				if (value.compareTo(array[middle]) == 0) {
					return middle;
				} else if (value.compareTo(array[middle]) < 0) {
					upper = middle - 1;
				} else {
					lower = middle + 1;
				}
			}
			return -1;
		}
		
	}

	@Override
	public void add(int pos, E value) {
		
		if(pos > size) {
			pos = size;
		} else if(pos < 0) {
			pos = 0;
		}
		
		//If array is full, we make it twice the size
		if(size >= array.length) {
			@SuppressWarnings("unchecked")
			E[] tempArray = (E[]) new Comparable[array.length * 2];
			for (int i = 0; i < array.length; i++) {
				tempArray[i] = array[i];
			}
			array = tempArray;
		}
		
		for(int i = size; i > pos; i--) {
			array[i] = array[i - 1];
		}
		size++;
		array[pos] = value;
	}

	@Override
	public E get(int pos) {
		if(pos >= 0 && pos < size) {
			return array[pos];
		} else {
			return null;
		}
	}

	@Override
	public E remove(int pos) throws IllegalArgumentException {
		if(pos < 0 || pos >= size-1) {
			//pos = 0;
			throw new IllegalArgumentException("Illegal argument inserted. The chosen position is out the array."); 
		}
		
		E result = array[pos];
		
		for (int i = pos +1; i < size; i++) {
			array[i - 1] = array[i];
		}
		array[size] = null;
		size--;
		
		return result;
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public void sort() {
		
		quickSort(0, size-1);
		isSorted = true;
	
	}
	
	
	private void quickSort(int lower, int upper) {
		int i = lower;
		int j = upper;
		
		E pivot = array[(lower + upper) / 2];
		
		while(i <= j) {
			while (array[i].compareTo(pivot) < 0) {
				i++;
			}
			
			while (array[j].compareTo(pivot) > 0) {
				j--;
			}
			if (i <= j) {
				byt(i, j);
				i++;
				j--;
			}
			
		}
		if (lower < j)
            quickSort(lower, j);
        if (i < upper)
            quickSort(i, upper);
	}
	
	private void byt(int i, int j) {
		E tempLow = array[i];
		E tempHight = array[j];
		
		array[i] = tempHight;
		array[j] = tempLow;
	}
	

}
