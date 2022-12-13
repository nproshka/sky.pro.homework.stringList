package IntegerList;



import StringList.ElementNotFoundException;
import StringList.IllegalIndexException;
import StringList.NullItemException;
import StringList.RepositoryIsFullException;

import java.util.Arrays;

public class IntegerListImpl implements IntegerList {

    private Integer[] repository;

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
        increaseRepositoryIfNeed();

        this.repository[size++] = item;

        return item;
    }

    @Override
    public Integer add(int index, Integer item) {
        validateItem(item);
        validateIndex(index);
        validateSize(size);
        increaseRepositoryIfNeed();

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
        quickSort(arr, 0, arr.length - 1);
    }

    private void quickSort(Integer[] arr, int begin, int end) {
        if (begin < end) {
            int partitionIndex = partition(arr, begin, end);

            quickSort(arr, begin, partitionIndex - 1);
            quickSort(arr, partitionIndex + 1, end);
        }
    }

    private int partition(Integer[] arr, int begin, int end) {
        int pivot = arr[end];
        int i = (begin - 1);

        for (int j = begin; j < end; j++) {
            if (arr[j] <= pivot) {
                i++;

                swapElements(arr, i, j);
            }
        }

        swapElements(arr, i + 1, end);
        return i + 1;
    }

    private void swapElements(Integer[] arr, int left, int right) {
        int temp = arr[left];
        arr[left] = arr[right];
        arr[right] = temp;
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

    private void increaseRepositoryIfNeed() {
        if (size == repository.length) {
            repository = Arrays.copyOf(repository, size + 1);
        }
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

