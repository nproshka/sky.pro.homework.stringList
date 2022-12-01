package StringList;

import java.util.Arrays;

public class StringListImpl implements StringList {

    private final String[] repository;

    private int size;

    public StringListImpl() {
        repository = new String[5];
    }

    public StringListImpl(int size) {
        repository = new String[size];
    }

    @Override
    public String add(String item) {
        validateItem(item);
        validateSize(size);

        this.repository[size++] = item;

        return item;
    }

    @Override
    public String add(int index, String item) {
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
    public String set(int index, String item) {
        validateItem(item);
        validateIndex(index);

        this.repository[index] = item;

        return item;
    }

    @Override
    public String remove(String item) {
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
    public String remove(int index) {
        validateIndex(index);

        String item = repository[index];

        System.arraycopy(repository, index + 1, repository, index, size - index);

        size--;
        return item;
    }

    @Override
    public boolean contains(String item) {
        return indexOf(item) != -1;
    }

    @Override
    public int indexOf(String item) {
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
    public int lastIndexOf(String item) {
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
    public String get(int index) {
        validateIndex(index);

        String item = repository[index];

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
    public String[] toArray() {
        return Arrays.copyOf(repository, size);
    }

    @Override
    public boolean equals(StringList otherList) {
        return Arrays.equals(this.toArray(), otherList.toArray());
    }

    private void validateItem (String item) {
        if (item == null || item.isBlank()) {
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

