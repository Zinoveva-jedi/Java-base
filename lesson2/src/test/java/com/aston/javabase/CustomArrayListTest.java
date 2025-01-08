package com.aston.javabase;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


public class CustomArrayListTest {

    private CustomArrayList<Integer> customList;

    @BeforeEach
    void setUp() {
        customList = new CustomArrayList<>();
    }

    @Test
    @DisplayName("Add element to the end")
    void shouldAddElementToEnd() {
        assertTrue(customList.add(10));
        assertEquals(1, customList.size());
        assertEquals(10, customList.get(0));
    }

    @Test
    @DisplayName("Grow list when it is full")
    void shouldGrowListWhenFull() {
        for (int i = 0; i < 10; i++) {
            customList.add(i);
        }
        assertEquals(10, customList.size());

        customList.add(10);
        assertEquals(11, customList.size());
    }

    @Test
    @DisplayName("Add all elements from a collection")
    void shouldAddAllElementsFromCollection() {
        List<Integer> collection = Arrays.asList(1, 2, 3);
        assertTrue(customList.addAll(collection));
        assertEquals(3, customList.size());
        assertEquals(1, customList.get(0));
        assertEquals(2, customList.get(1));
        assertEquals(3, customList.get(2));
    }

    @Test
    @DisplayName("Not add all elements from an empty collection")
    void shouldNotAddAllElementsFromEmptyCollection() {
        List<Integer> collection = new ArrayList<>();
        assertFalse(customList.addAll(collection));
        assertEquals(0, customList.size());
    }

    @Test
    @DisplayName("Add elements from a collection and grow if needed")
    void shouldAddAllAndGrow() {
        for (int i = 0; i < 5; i++) {
            customList.add(i);
        }
        assertTrue(customList.addAll(List.of(5, 6, 7, 8, 9, 10)));

        assertEquals(11, customList.size());
        assertEquals(0, customList.get(0));
        assertEquals(5, customList.get(5));
        assertEquals(10, customList.get(10));
    }


    @Test
    @DisplayName("Remove element by index")
    void shouldRemoveElementByIndex() {
        customList.add(10);
        customList.add(20);
        Integer removedValue = customList.remove(0);
        assertEquals(1, customList.size());
        assertEquals(20, customList.get(0));
        assertEquals(10, removedValue);
    }

    @Test
    @DisplayName("Throw IndexOutOfBoundsException when removing with invalid index")
    void shouldThrowIndexOutOfBoundsExceptionWhenRemovingWithInvalidIndex() {
        assertThrows(IndexOutOfBoundsException.class, () -> customList.remove(1));
        assertThrows(IndexOutOfBoundsException.class, () -> customList.remove(-1));
    }


    @Test
    @DisplayName("Remove element by object")
    void shouldRemoveElementByObject() {
        customList.add(10);
        customList.add(20);
        assertTrue(customList.remove(Integer.valueOf(10)));
        assertEquals(1, customList.size());
        assertEquals(20, customList.get(0));
    }


    @Test
    @DisplayName("Object not found when removing by object")
    void shouldReturnFalseIfObjectNotFoundWhenRemovingByObject() {
        customList.add(10);
        customList.add(20);
        assertFalse(customList.remove(Integer.valueOf(30)));
        assertEquals(2, customList.size());
        assertEquals(10, customList.get(0));
        assertEquals(20, customList.get(1));
    }

    @Test
    @DisplayName("Removing null object")
    void shouldReturnFalseWhenRemovingNullObject() {
        customList.add(10);
        assertFalse(customList.remove(null));
        assertEquals(1, customList.size());
        assertEquals(10, customList.get(0));
    }

    @Test
    @DisplayName("ElementData is null or empty when removing by object")
    void shouldReturnFalseIfElementDataNullOrEmpty() {
        assertFalse(customList.remove(Integer.valueOf(10)));
    }


    @Test
    @DisplayName("Grow list correctly with a specific capacity")
    void shouldGrowListWithSpecificCapacity() {
        Integer[] initialArray = new Integer[]{1, 2, 3, 4, 5};
        List<Integer> initialList = Arrays.asList(initialArray);
        CustomArrayList<Integer> customListWithInit = new CustomArrayList<>(initialList);
        int initialSize = customListWithInit.size();
        int minCapacity = initialArray.length * 3 / 2 + 2;
        Object[] grownArray = customListWithInit.grow(minCapacity);
        assertTrue(grownArray.length > initialArray.length);
        assertEquals(minCapacity, grownArray.length);
        assertEquals(initialSize, customListWithInit.size());
    }


}

