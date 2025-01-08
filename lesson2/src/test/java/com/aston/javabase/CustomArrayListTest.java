
package com.aston.javabase;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;


public class CustomArrayListTest {

    private CustomArrayList<Integer> customList;

    @BeforeEach
    void setUp() {
        customList = new CustomArrayList<>();
    }

    @Test
    @DisplayName("Add element")
    void shouldAddElementToEnd() {
        assertTrue(customList.add(10));
        assertEquals(1, customList.size());
        assertEquals(10, customList.get(0));
    }

    @Test
    @DisplayName("Grow on full")
    void shouldGrowListWhenFull() {
        for (int i = 0; i < 10; i++) {
            customList.add(i);
        }
        assertEquals(10, customList.size());

        customList.add(10);
        assertEquals(11, customList.size());
    }

    @Test
    @DisplayName("Add all from collection")
    void shouldAddAllElementsFromCollection() {
        assertTrue(customList.addAll(List.of(1, 2, 3)));
        assertEquals(3, customList.size());
        assertEquals(1, customList.get(0));
        assertEquals(2, customList.get(1));
        assertEquals(3, customList.get(2));
    }

    @Test
    @DisplayName("Not add empty collection")
    void shouldNotAddAllElementsFromEmptyCollection() {
        List<Integer> collection = new ArrayList<>();
        assertFalse(customList.addAll(collection));
        assertEquals(0, customList.size());
    }

    @Test
    @DisplayName("Add all and grow")
    void shouldAddAllAndGrow() {
        for (int i = 0; i < 5; i++) {
            customList.add(i);
        }
        List<Integer> collection = Arrays.asList(5, 6, 7, 8, 9, 10);
        assertTrue(customList.addAll(collection));

        assertEquals(11, customList.size());
        assertEquals(0, customList.get(0));
        assertEquals(10, customList.get(10));
        assertEquals(5, customList.get(5));

    }

    @Test
    @DisplayName("Add null collection exception")
    void shouldThrowExceptionWhenAddingNullCollection() {
        assertThrows(NullPointerException.class, ()-> customList.addAll(null));
    }

    @Test
    @DisplayName("Remove by index")
    void shouldRemoveElementByIndex() {
        customList.add(10);
        customList.add(20);
        Integer removedValue = customList.remove(0);
        assertEquals(1, customList.size());
        assertEquals(20, customList.get(0));
        assertEquals(10, removedValue);
    }

    @Test
    @DisplayName("Remove invalid index exception")
    void shouldThrowIndexOutOfBoundsExceptionWhenRemovingWithInvalidIndex() {
        assertThrows(IndexOutOfBoundsException.class, () -> customList.remove(1));
        assertThrows(IndexOutOfBoundsException.class, () -> customList.remove(-1));
    }


    @Test
    @DisplayName("Remove by object")
    void shouldRemoveElementByObject() {
        customList.add(10);
        customList.add(20);
        assertTrue(customList.remove(Integer.valueOf(10)));
        assertEquals(1, customList.size());
        assertEquals(20, customList.get(0));
    }


    @Test
    @DisplayName("Remove object not found")
    void shouldReturnFalseIfObjectNotFoundWhenRemovingByObject() {
        customList.add(10);
        customList.add(20);
        assertFalse(customList.remove(Integer.valueOf(30)));
        assertEquals(2, customList.size());
        assertEquals(10, customList.get(0));
        assertEquals(20, customList.get(1));
    }

    @Test
    @DisplayName("Remove null return false")
    void shouldReturnFalseWhenRemovingNullObject() {
        customList.add(10);
        assertFalse(customList.remove(null));
        assertEquals(1, customList.size());
        assertEquals(10, customList.get(0));
    }

    @Test
    @DisplayName("Remove null or empty false")
    void shouldReturnFalseIfElementDataNullOrEmpty() {
        assertFalse(customList.remove(Integer.valueOf(10)));
    }
    @Test
    @DisplayName("Index of element")
    void shouldReturnIndexOfElement() {
        customList.add(1);
        customList.add(2);
        customList.add(3);
        assertEquals(1, customList.indexOf(2));
        assertEquals(0, customList.indexOf(1));
        assertEquals(2, customList.indexOf(3));
    }

    @Test
    @DisplayName("Index of not found -1")
    void shouldReturnMinusOneIfElementNotFound() {
        customList.add(1);
        customList.add(2);
        assertEquals(-1, customList.indexOf(4));
        assertEquals(-1, customList.indexOf(null));
    }

    @Test
    @DisplayName("Index of null element")
    void shouldReturnIndexOfNullElement() {
        customList.add(1);
        customList.add(null);
        customList.add(3);
        assertEquals(1, customList.indexOf(null));
    }

    @Test
    @DisplayName("Get by index")
    void shouldReturnElementAtIndex() {
        customList.add(1);
        customList.add(2);
        assertEquals(1, customList.get(0));
        assertEquals(2, customList.get(1));
    }

    @Test
    @DisplayName("Get index exception")
    void shouldThrowIndexOutOfBoundsExceptionWhenGet() {
        assertThrows(IndexOutOfBoundsException.class, () -> customList.get(-1));
        assertThrows(IndexOutOfBoundsException.class, () -> customList.get(1));
    }

    @Test
    @DisplayName("Add by index")
    void shouldInsertElementAtIndex() {
        customList.add(1);
        customList.add(2);
        customList.add(1, 3);
        assertEquals(3, customList.get(1));
        assertEquals(2, customList.get(2));
    }

    @Test
    @DisplayName("Add by index exception")
    void shouldThrowIndexOutOfBoundsExceptionWhenAddAtIndex() {
        assertThrows(IndexOutOfBoundsException.class, () -> customList.add(-1, 1));
        assertThrows(IndexOutOfBoundsException.class, () -> customList.add(2, 1));
    }


    @Test
    @DisplayName("Grow with capacity")
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

    @Test
    @DisplayName("Clear list")
    void shouldClearTheList() {
        customList.add(1);
        customList.add(2);
        customList.clear();
        assertEquals(0,customList.size());
        assertTrue(customList.isEmpty());
    }

    @Test
    @DisplayName("Contains element")
    void shouldCheckIfContainsElement() {
        customList.add(1);
        customList.add(2);
        assertTrue(customList.contains(1));
        assertTrue(customList.contains(2));
        assertFalse(customList.contains(3));
        assertFalse(customList.contains(null));
    }

    @Test
    @DisplayName("Is empty")
    void shouldCheckIfIsEmpty() {
        assertTrue(customList.isEmpty());
        customList.add(1);
        assertFalse(customList.isEmpty());
    }

    @Test
    @DisplayName("Sort list")
    void shouldSortTheList() {
        customList.add(5);
        customList.add(2);
        customList.add(8);
        customList.add(1);
        customList.add(3);
        customList.sort(Comparator.naturalOrder());
        assertEquals(1, customList.get(0));
        assertEquals(2, customList.get(1));
        assertEquals(3, customList.get(2));
        assertEquals(5, customList.get(3));
        assertEquals(8, customList.get(4));
    }

    @Test
    @DisplayName("Reverse sort list")
    void shouldSortTheListWithReverseComparator() {
        customList.add(5);
        customList.add(2);
        customList.add(8);
        customList.add(1);
        customList.add(3);
        customList.sort(Comparator.reverseOrder());
        assertEquals(8, customList.get(0));
        assertEquals(5, customList.get(1));
        assertEquals(3, customList.get(2));
        assertEquals(2, customList.get(3));
        assertEquals(1, customList.get(4));
    }


    @Test
    @DisplayName("List to string")
    void shouldGetStringRepresentationOfTheList() {
        customList.add(1);
        customList.add(2);
        customList.add(3);
        assertEquals("1 2 3", customList.toString());
    }

    @Test
    @DisplayName("Null list to string")
    void shouldReturnEmptyWhenElementDataIsNullAndCallToString() {
        CustomArrayList<String> list = new CustomArrayList<>();
        assertEquals("", list.toString());
    }

    @Test
    @DisplayName("Empty list to string")
    void shouldReturnEmptyStringWhenSizeIsZeroAndCallToString() {
        assertEquals("", customList.toString());
    }

    @Test
    @DisplayName("Add element String")
    void shouldAddElementString() {
        CustomArrayList<String> stringList = new CustomArrayList<>();
        assertTrue(stringList.add("test"));
        assertEquals(1, stringList.size());
        assertEquals("test", stringList.get(0));
    }
    @Test
    @DisplayName("Add all from collection String")
    void shouldAddAllElementsFromStringCollection() {
        CustomArrayList<String> stringList = new CustomArrayList<>();
        List<String> collection = Arrays.asList("a", "b", "c");
        assertTrue(stringList.addAll(collection));
        assertEquals(3, stringList.size());
        assertEquals("a", stringList.get(0));
        assertEquals("b", stringList.get(1));
        assertEquals("c", stringList.get(2));
    }

    @Test
    @DisplayName("Add element Double")
    void shouldAddElementDouble() {
        CustomArrayList<Double> doubleList = new CustomArrayList<>();
        assertTrue(doubleList.add(10.5));
        assertEquals(1, doubleList.size());
        assertEquals(10.5, doubleList.get(0));
    }

    @Test
    @DisplayName("Add all from collection Double")
    void shouldAddAllElementsFromDoubleCollection() {
        CustomArrayList<Double> doubleList = new CustomArrayList<>();
        List<Double> collection = Arrays.asList(1.1, 2.2, 3.3);
        assertTrue(doubleList.addAll(collection));
        assertEquals(3, doubleList.size());
        assertEquals(1.1, doubleList.get(0));
        assertEquals(2.2, doubleList.get(1));
        assertEquals(3.3, doubleList.get(2));
    }

    @Test
    @DisplayName("Add a lot of elements")
    void shouldAddALotOfElements() {
        int count = 1000000;
        for (int i = 0; i < count; i++) {
            customList.add(i);
        }
        assertEquals(count, customList.size());
        for (int i = 0; i < count; i++) {
            assertEquals(i, customList.get(i));
        }
    }
    @Test
    @DisplayName("Add a lot of elements and remove")
    void shouldAddALotOfElementsAndRemove() {
        int count = 10000000;
        for (int i = 0; i < count; i++) {
            customList.add(i);
        }
        for (int i = count -1; i >= 0 ; i--) {
            customList.remove(i);
        }
        assertEquals(0, customList.size());
    }
    @Test
    @DisplayName("Sort large data set")
    void shouldSortLargeDataSet() {
        int count = 10000000;
        List<Integer> randomList = new Random().ints(count, 0, 1000000)
                .boxed().collect(Collectors.toList());
        customList.addAll(randomList);
        customList.sort(Comparator.naturalOrder());
        IntStream.range(0, count -1).forEach(i -> assertTrue(customList.get(i) <= customList.get(i+1)));
    }
}
