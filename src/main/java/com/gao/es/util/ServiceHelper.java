package com.gao.es.util;

import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 * @author ：gaozhiqi
 * @date ：2022/8/13 20:35
 */
public class ServiceHelper {

    /**
     * 将list转化为Iterable
     * @param list
     * @param <T>
     * @return
     */
    public  static <T> Iterable<T> listConvertIterable(List<T> list){
        return new Iterable<T>() {
            @Override
            public Iterator<T> iterator() {
                return new Iterator<T>() {

                    ListIterator<T> listIterator = list.listIterator(list.size());
                    @Override
                    public boolean hasNext() {

                        return listIterator.hasPrevious();
                    }
                    @Override
                    public T next() {
                        return listIterator.previous();
                    }
                    @Override
                    public void remove() {
                        listIterator.remove();
                    }
                };
            }
        };
    }

}
