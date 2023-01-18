package com.models;

import java.util.List;

public interface GenericDao <E>{
    public boolean insert(E e);
    public E select(int id);
    public List<E> select();
    public List<E> select(String nome);
    public boolean update(E e);
    public boolean delete(E e);
}
