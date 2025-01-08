package com.aston.javabase;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

public class QuickSortTest {

    private QuickSort<Integer> quickSort;

    @BeforeEach
    void setUp() {
        quickSort = new QuickSort<>();
    }

    @Test
    @DisplayName("Sort empty array")
    void shouldSortEmptyArray() {
        Integer[] arr = {};
        assertArraySortsCorrectly(arr, Comparator.naturalOrder());
    }

    @Test
    @DisplayName("Sort a single element array")
    void shouldSortSingleElementArray() {
        Integer[] arr = {5};
        assertArraySortsCorrectly(arr, Comparator.naturalOrder());
    }

    @Test
    @DisplayName("Sort an already sorted array")
    void shouldSortAlreadySortedArray() {
        Integer[] arr = {1, 2, 3, 4, 5};
        assertArraySortsCorrectly(arr, Comparator.naturalOrder());
    }

    @Test
    @DisplayName("Sort a reverse sorted array")
    void shouldSortReverseSortedArray() {
        Integer[] arr = {5, 4, 3, 2, 1};
        assertArraySortsCorrectly(arr, Comparator.naturalOrder());
    }

    @Test
    @DisplayName("Sort array with duplicates")
    void shouldSortWithDuplicates() {
        Integer[] arr = {5, 2, 8, 2, 5, 1};
        assertArraySortsCorrectly(arr, Comparator.naturalOrder());
    }

    @Test
    @DisplayName("Sort array with negative numbers")
    void shouldSortNegativeNumbers() {
        Integer[] arr = {-5, -2, -8, -1, -10};
        assertArraySortsCorrectly(arr, Comparator.naturalOrder());
    }

    @Test
    @DisplayName("Sort array with positive and negative numbers")
    void shouldSortPositiveAndNegativeNumbers() {
        Integer[] arr = {-5, 2, -8, 1, -10, 0, 5};
        assertArraySortsCorrectly(arr, Comparator.naturalOrder());
    }

    @Test
    @DisplayName("Sort array of strings")
    void shouldSortStrings() {
        String[] arr = {"banana", "apple", "cherry", "date"};
        assertArraySortsCorrectly(arr, Comparator.naturalOrder());
    }

    @Test
    @DisplayName("Sort an array with a custom comparator")
    void shouldSortWithComparator() {
        Integer[] arr = {5, 2, 8, 2, 5, 1};
        assertArraySortsCorrectly(arr, Comparator.reverseOrder());
    }

    @Test
    @DisplayName("Array is null")
    void shouldThrowNullPointerExceptionWhenArrayIsNull() {
        assertThrows(NullPointerException.class, () -> quickSort.sort(null, 0, 0, Comparator.naturalOrder()));
    }

    @Test
    @DisplayName("Sort a large random array")
    void shouldSortLargeRandomArray() {
        Random random = new Random();
        int size = 1000;
        Integer[] arr = new Integer[size];
        for (int i = 0; i < size; i++) {
            arr[i] = random.nextInt();
        }
        assertArraySortsCorrectly(arr, Comparator.naturalOrder());
    }

    private <T extends Comparable<T>> void assertArraySortsCorrectly(T[] arr, Comparator<? super T> comparator) {
        T[] expected = Arrays.copyOf(arr, arr.length);
        Arrays.sort(expected, comparator);
        if (arr.length > 0 && arr[0] instanceof Integer) {
            quickSort.sort((Integer[])arr, 0, arr.length - 1, (Comparator<? super Integer>) comparator);
        } else {
            QuickSort<T> quickSortT = new QuickSort<>();
            quickSortT.sort(arr, 0, arr.length - 1, comparator);
        }
        assertArrayEquals(expected, arr);
    }
}
