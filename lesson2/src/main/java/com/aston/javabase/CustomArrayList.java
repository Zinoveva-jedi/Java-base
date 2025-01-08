package com.aston.javabase;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;

/**
 * <p>
 * Данный класс представляет собой упрощенную реализацию ArrayList,
 * хранящего элементы в массиве. Он обеспечивает основные операции
 * добавления, удаления элементов, сортировки и преобразования списка
 * в строковое представление.
 * </p>
 *
 * @param <E> Тип элементов, хранящихся в списке.
 * @author Natalia Zinoveva
 */
public class CustomArrayList<E extends Comparable<E>> implements Cloneable, Serializable {

    private static final int DEFAULT_CAPACITY = 10;
    private int size = 0;
    private Object[] elementData;

    /**
     * <p>
     * Конструктор класса, создает пустой список с начальной емкостью по умолчанию (10 элементов).
     * </p>
     * <p>
     * Внутренний массив, используемый для хранения элементов, будет иметь
     * начальную емкость равную {@link #DEFAULT_CAPACITY}.
     * </p>
     */
    public CustomArrayList() {
        this.elementData = new Object[DEFAULT_CAPACITY];
    }

    /**
     * <p>
     * Создает пустой список с заданной начальной емкостью.
     * </p>
     * <p>
     * Внутренний массив, используемый для хранения элементов, будет иметь
     * начальную емкость, указанную в параметре {@code initialCapacity}.
     * </p>
     *
     * @param initialCapacity Начальная емкость списка.
     * @throws IllegalArgumentException Если {@code initialCapacity} меньше 0.
     */
    public CustomArrayList(int initialCapacity) {
        if (initialCapacity > 0) {
            this.elementData = new Object[initialCapacity];
        } else if (initialCapacity == 0) {
            this.elementData = new Object[]{};
        } else {
            throw new IllegalArgumentException("Illegal Capacity: " +
                    initialCapacity);
        }
    }

    /**
     * <p>
     * Создает список, инициализированный элементами заданной коллекции.
     * </p>
     * <p>
     * Элементы коллекции будут скопированы во внутренний массив списка.
     * Размер списка будет равен размеру переданной коллекции.
     * </p>
     *
     * @param c Коллекция, элементы которой нужно скопировать в новый список.
     * @throws NullPointerException Если переданная коллекция {@code c} равна {@code null}.
     */
    public CustomArrayList(Collection<? extends E> c) {
        if (c == null) {
            throw new NullPointerException("Cannot create list from null collection");
        }
        Object[] temp = c.toArray();
        if ((size = temp.length) != 0) {
            elementData = Arrays.copyOf(temp, size, Object[].class);
        } else {
            elementData = new Object[]{};
        }
    }


    /**
     * <p>
     * Возвращает текущий размер списка.
     * </p>
     *
     * @return Количество элементов, хранящихся в списке.
     */
    public int size() {
        return size;
    }


    /**
     * <p>
     * Удаляет все элементы из списка.
     * </p>
     * <p>
     * Обнуляет ссылки на все элементы и сбрасывает размер списка до 0.
     */
    public void clear() {
        for (int i = elementData.length - 1; i >= 0; i--) {
            elementData[i] = null;
        }
        size = 0;
    }

    /**
     * <p>
     * Проверяет, является ли список пустым.
     * </p>
     *
     * @return {@code true}, если список не содержит элементов,
     * {@code false} в противном случае.
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * <p>
     * Проверяет, содержит ли список указанный элемент.
     * </p>
     * <p>
     * Использует метод {@link #indexOf(Object)} для поиска элемента.
     * </p>
     *
     * @param o элемент, наличие которого нужно проверить.
     * @return {@code true}, если список содержит указанный элемент, {@code false} в противном случае.
     */
    public boolean contains(Object o) {
        return indexOf(o) >= 0;
    }

    /**
     * <p>
     * Возвращает индекс первого вхождения указанного элемента в списке.
     * </p>
     * <p>
     * Использует метод {@link #indexOfRange(Object, int, int)} для поиска элемента в диапазоне от 0 до текущего размера списка.
     * </p>
     *
     * @param o Элемент, индекс которого нужно найти.
     * @return Индекс первого вхождения элемента, если он найден, или -1, если элемент не найден.
     */
    public int indexOf(Object o) {
        return indexOfRange(o, 0, size);
    }


    /**
     * Ищет первое вхождение указанного объекта в пределах заданного диапазона этого списка.
     *
     * <p>Если объект равен {@code null}, этот метод ищет первый элемент {@code null} в пределах указанного диапазона.
     * В противном случае он ищет первый элемент, который равен указанному объекту, используя метод {@code equals}.
     *
     * @param o     объект, который нужно найти
     * @param start начальный индекс диапазона (включительно)
     * @param end   конечный индекс диапазона (не включительно)
     * @return индекс первого вхождения указанного объекта в пределах диапазона или {@code -1}, если объект не найден.
     * @throws IndexOutOfBoundsException если индексы {@code start} или {@code end} выходят за пределы допустимого диапазона
     *                                   ({@code start < 0 || end > size() || start > end}).
     */
    public int indexOfRange(Object o, int start, int end) {
        if (o == null) {
            for (int i = start; i < end; i++) {
                if (elementData[i] == null) {
                    return i;
                }
            }
        } else {
            for (int i = start; i < end; i++) {
                if (o.equals(elementData[i])) {
                    return i;
                }
            }
        }
        return -1;
    }

    /**
     * Возвращает элемент, находящийся в указанной позиции в этом списке.
     *
     * @param index индекс возвращаемого элемента
     * @return элемент, находящийся в указанной позиции в этом списке
     * @throws IndexOutOfBoundsException если индекс выходит за допустимые пределы
     *                                   ({@code index < 0 || index >= size()})
     */
    public E get(int index) {
        if (index > size || index < 0) {
            throw new IndexOutOfBoundsException();
        }
        return (E) elementData[index];
    }

    /**
     * Вставляет указанный элемент в указанную позицию в этом списке.
     * Сдвигает элемент, находящийся в этой позиции (если он есть), и все последующие элементы вправо (увеличивая их индексы).
     *
     * @param index   индекс, в который должен быть вставлен указанный элемент
     * @param element элемент, который должен быть вставлен
     * @throws IndexOutOfBoundsException если индекс выходит за допустимые пределы
     *                                   ({@code index < 0 || index > size()})
     */
    public void add(int index, E element) {
        if (index > size || index < 0) {
            throw new IndexOutOfBoundsException();
        }
        if (size == elementData.length) {
            grow();
        }
        System.arraycopy(elementData, index, elementData, index + 1, (size - index));
        elementData[index] = element;
        size++;
    }

    /**
     * Добавляет указанный элемент в конец этого списка.
     *
     * @param element элемент, который нужно добавить в список
     * @return {@code true} (как указано в {@link Collection#add})
     */
    public boolean add(E element) {
        if (size == elementData.length) {
            grow();
        }
        elementData[size] = element;
        size++;
        return true;
    }

    private Object[] grow() {
        return elementData = grow(size);
    }

    /**
     * Добавляет все элементы из указанной коллекции в конец этого списка в том порядке,
     * в котором они возвращаются итератором указанной коллекции.
     *
     * @param c коллекция, содержащая элементы, которые нужно добавить в этот список
     * @return {@code true}, если этот список был изменен в результате вызова,
     * {@code false} в противном случае (если коллекция пуста).
     * @throws NullPointerException если указанная коллекция равна {@code null}
     */
    public boolean addAll(Collection<? extends E> c) {
        if (c.isEmpty()) {
            return false;
        }
        Object[] a = c.toArray();
        if (a.length > (elementData.length - size)) {
            elementData = grow(size + a.length);
        }
        System.arraycopy(a, 0, elementData, size, a.length);
        size += a.length;
        return true;
    }

    /**
     * Увеличивает вместимость внутреннего массива, при необходимости.
     *
     * <p>Если текущий массив не инициализирован или пуст, то создается новый массив
     * с вместимостью, равной большему из значений {@code DEFAULT_CAPACITY} или {@code minCapacity}.
     * В противном случае, новая вместимость вычисляется как 1.5 от текущей вместимости,
     * к которой прибавляется 1, но не меньше чем {@code minCapacity}, затем создается новый массив
     * с этой вместимостью, в который копируются старые элементы.
     *
     * @param minCapacity минимальная требуемая вместимость нового массива.
     * @return ссылка на новый массив с увеличенной вместимостью.
     */
    public Object[] grow(int minCapacity) {
        if (elementData != null || elementData.length > 0) {
            int newDefaultCapacity = elementData.length * 3 / 2 + 1;
            int newCapacity = newDefaultCapacity >= minCapacity ? newDefaultCapacity : minCapacity;
            return elementData = Arrays.copyOf(elementData, newCapacity);
        } else {
            return elementData = new Object[Math.max(DEFAULT_CAPACITY, minCapacity)];
        }
    }

    /**
     * Удаляет элемент в указанной позиции в этом списке.
     * Сдвигает все последующие элементы влево (уменьшая их индексы).
     *
     * @param index индекс элемента, который нужно удалить
     * @return элемент, который был удален из списка
     * @throws IndexOutOfBoundsException если индекс выходит за допустимые пределы
     *                                   ({@code index < 0 || index >= size()})
     */
    public E remove(int index) {
        if (index > size || index < 0) {
            throw new IndexOutOfBoundsException();
        }
        E removeValue = (E) elementData[index];
        System.arraycopy(elementData, index + 1, elementData, index, (size - index - 1));
        elementData[size - 1] = null;
        size--;
        return removeValue;
    }

    /**
     * Удаляет первое вхождение указанного элемента из этого списка, если он присутствует.
     * Если элемент присутствует, то сдвигает все последующие элементы влево (уменьшая их индексы).
     *
     * @param o элемент, который нужно удалить из этого списка, если он присутствует
     * @return {@code true}, если этот список был изменен в результате вызова (т.е. элемент был удален),
     * {@code false} в противном случае.
     */
    public boolean remove(Object o) {
        if (elementData == null || elementData.length < 1 || o == null) {
            return false;
        }
        for (int i = 0; i < size; i++) {
            if (elementData[i].equals(o)) {
                remove(i);
                return true;
            }
        }
        return false;
    }

    /**
     * Сортирует этот список в соответствии с порядком, определяемым заданным компаратором.
     * Используется алгоритм быстрой сортировки.
     *
     * @param c компаратор, используемый для сравнения элементов списка.
     * @throws NullPointerException если указанный компаратор равен {@code null}
     */
    public void sort(Comparator<? super E> c) {
        QuickSort<E> quicksort = new QuickSort<>();
        quicksort.sort(elementData, 0, size - 1, c);
    }

    /**
     * Возвращает строковое представление этого списка.
     * Строковое представление состоит из списка элементов.
     *
     * @return строковое представление этого списка. Если список пуст - вернет пустую строку,
     * если массив элементов не инициализирован - вернет null
     */
    @Override
    public String toString() {
        if (elementData == null) return null;
        if (size < 1) return "";
        StringBuilder sb = new StringBuilder(elementData[0] + " ");
        for (int i = 1; i < size - 1; i++) {
            sb.append(elementData[i] + " ");
        }
        sb.append(elementData[size - 1]);
        return sb.toString();
    }

}
