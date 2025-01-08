package com.aston.javabase;

import java.util.Comparator;

/**
 * Класс `QuickSort` реализует алгоритм быстрой сортировки для массивов.
 * Поддерживает сортировку с использованием компаратора для элементов, реализующих интерфейс Comparable.
 *
 * @param <T> Тип элементов массива, которые должны быть сравнимыми.
 */
public class QuickSort<T extends Comparable<T>> {

    /**
     * Сортирует указанный массив в заданном диапазоне индексов с использованием заданного компаратора.
     *
     * @param array Массив, который необходимо отсортировать.
     * @param low   Начальный индекс диапазона сортировки (включительно).
     * @param high  Конечный индекс диапазона сортировки (включительно).
     * @param c     Компаратор, определяющий порядок сортировки элементов. Если передан null, элементы будут сравниваться
     *              с использованием естественного порядка.
     * @throws NullPointerException если массив равен null.
     */
    public void sort(Object[] array, int low, int high, Comparator<? super T> c) {
        if (array == null) {
            throw new NullPointerException();
        }
        if (array.length < 2) return;
        if (low < high) {
            int markerIndex = partition(array, low, high, c);
            sort(array, low, markerIndex - 1, c);
            sort(array, markerIndex + 1, high, c);
        }
    }

    private int partition(Object[] array, int low, int high, Comparator<? super T> c) {
        T marker = (T) array[high];
        int i = (low - 1);

        for (int j = low; j < high; j++) {
            if (c.compare((T) array[j], marker) <= 0) {
                i++;
                swap(array, i, j);
            }
        }

        swap(array, i + 1, high);
        return i + 1;
    }

    private void swap(Object[] array, int i, int j) {
        Object temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}
