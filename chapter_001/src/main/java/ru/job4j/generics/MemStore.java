package ru.job4j.generics;

import java.util.ArrayList;
import java.util.List;

public final class MemStore<T extends Base> implements Store<T> {

    private final List<T> list = new ArrayList<>();

    @Override
    public void add(T model) {
        list.add(model);
    }

    @Override
    public boolean replace(String id, T model) {
        var index = findById(id);
        if (checkIndex(index)) {
            return false;
        }
        list.set(index, model);
        return true;
    }

    @Override
    public boolean delete(String id) {
        var index = findById(id);
        if (checkIndex(index)) {
            return false;
        }
        list.remove(index);
        return true;
    }

    @Override
    public int findById(String id) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getId().equals(id)) {
                return i;
            }
        }
        return -1;
    }

    private boolean checkIndex(int index) {
        return index == -1;
    }

}
