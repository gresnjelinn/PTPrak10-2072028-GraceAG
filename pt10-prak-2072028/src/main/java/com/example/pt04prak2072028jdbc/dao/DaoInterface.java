package com.example.pt04prak2072028jdbc.dao;

import java.util.List;

public interface DaoInterface<T> {
    List<T> getData();
    void addData(T data);
    void delData(T data);
    void updateData(T data);
}
