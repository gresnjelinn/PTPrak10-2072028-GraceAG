package com.example.pt10prak2072028hibernate.dao;

import java.util.List;

public interface DaoInterface<T> {
    List<T> getData();
    void addData(T data);
    void delData(T data);
    void updateData(T data);
}
