package IntegerList;



import StringList.ElementNotFoundException;
import StringList.IllegalIndexException;
import StringList.NullItemException;
import StringList.RepositoryIsFullException;

import java.util.Arrays;

public class IntegerListImpl implements IntegerList {

    private final Integer[] repository;

    private int size;

    public IntegerListImpl() {
        repository = new Integer[5];
    }

    public IntegerListImpl(int size) {
        repository = new Integer[size];
    }

    @Override
    public Integer add(Integer item) {
        validateItem(item);
        validateSize(size);

        this.repository[size++] = item;

        return item;
    }

    @Override
    public Integer add(int index, Integer item) {
        validateItem(item);
        validateIndex(index);
        validateSize(size);

        if (index == size) {
            this.repository[size++] = item;
            return item;
        }

        System.arraycopy(repository, index, repository, index + 1, size - index);
        this.repository[index] = item;
        size++;

        return item;
    }

    @Override
    public Integer set(int index, Integer item) {
        validateItem(item);
        validateIndex(index);

        this.repository[index] = item;

        return item;
    }

    @Override
    public Integer remove(Integer item) {
        validateItem(item);

        int index = indexOf(item);

        if (index == -1) {
            throw new ElementNotFoundException();
        }

        System.arraycopy(repository, index + 1, repository, index, size - index);

        size--;
        return item;
    }

    @Override
    public Integer remove(int index) {
        validateIndex(index);

        Integer item = repository[index];

        System.arraycopy(repository, index + 1, repository, index, size - index);

        size--;
        return item;
    }

    @Override
    public boolean contains(Integer item) {
        Integer[] repositoryCopy = toArray();
        sort(repositoryCopy);
        return binarySearch(repositoryCopy, item);
    }

    @Override
    public int indexOf(Integer item) {
        validateItem(item);

        int index = 0;

        for (int i = 0; i < size; i++) {
            if (repository[i].equals(item)) {
                index = i;
            } else {
                index = -1;
            }
        }
        return index;
    }

    @Override
    public int lastIndexOf(Integer item) {
        validateItem(item);

        int index = 0;

        for (int i = size - 1; i >= 0; i--) {
            if (repository[i].equals(item)) {
                index = i;
            } else {
                index = -1;
            }
        }
        return index;
    }

    @Override
    public Integer get(int index) {
        validateIndex(index);

        Integer item = repository[index];

        return item;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void clear() {
        size = 0;
        Arrays.copyOf(repository, size);
    }

    @Override
    public Integer[] toArray() {
        return Arrays.copyOf(repository, size);
    }

    @Override
    public boolean equals(IntegerList otherList) {
        return Arrays.equals(this.toArray(), otherList.toArray());
    }

    private void sort(Integer[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int temp = arr[i];
            int j = i;
            while (j > 0 && arr[j - 1] >= temp) {
                arr[j] = arr[j - 1];
                j--;
            }
            arr[j] = temp;
        }
    }

    public boolean binarySearch(Integer[] arr, int item) {
        int min = 0;
        int max = arr.length - 1;

        while (min <= max) {
            int mid = (min + max) / 2;

            if (item == arr[mid]) {
                return true;
            }

            if (item < arr[mid]) {
                max = mid - 1;
            } else {
                min = mid + 1;
            }
        }
        return false;
    }


    private void validateItem (Integer item) {
        if (item == null) {
            throw new NullItemException();
        }
    }

    private void validateIndex (int index) {
        if (index < 0 || index > size) {
            throw new IllegalIndexException();
        }
    }

    private void validateSize(int size) {
        if (size == repository.length) {
            throw new RepositoryIsFullException();
        }

    }
}

