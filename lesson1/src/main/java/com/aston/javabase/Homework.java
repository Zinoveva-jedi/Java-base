package com.aston.javabase;


import java.util.*;
import java.util.stream.Collectors;

public class Homework {

    public static void main(String[] args) {
        String stringToTern = "I love Java";
        int[] ints = {1, 2, 2, 3, 4, 5, 5, 6, 7, 8, 8, 9};
        int[] arr = {10, 15, 23, 11, 44, 13, 66, 1, 6, 47};
        String string = "Hello world";
        String string2 = "    fly me    to the moon    ";

        turnString1(stringToTern);
        turnString2(stringToTern);
        turnString3(stringToTern);
        System.out.println(turnString4(stringToTern));
        System.out.println();

        getDistinctNumbers1(ints);
        getDistinctNumbers2(ints);
        System.out.println();

        System.out.println(findSecondMaxElement1(arr));
        System.out.println(findSecondMaxElement2(arr));
        System.out.println(findSecondMaxElement3(arr));
        System.out.println(findSecondMaxElement4(arr));
        System.out.println(findSecondMaxElement5(arr));
        System.out.println();

        System.out.println(lengthOfLastWord(string));
        System.out.println(lengthOfLastWord(string2));
        System.out.println();

        System.out.println(isPalindrome("abc"));
        System.out.println(isPalindrome("112233"));
        System.out.println(isPalindrome("aba"));
        System.out.println(isPalindrome("112211"));
        System.out.println(isPalindrome("I love JavaavaJ evol I"));
    }

    // ЗАДАЧА 1
    // Перевернуть строку и вывести на консоль
    // String string = "I love Java";
    public static void turnString1(String string) {
        StringBuilder str = new StringBuilder(string);
        System.out.println(str.reverse());
    }

    public static void turnString2(String string) {
        StringBuilder reversed = new StringBuilder();
        for (int i = string.length() - 1; i >= 0; i--) {
            reversed.append(string.charAt(i));
        }
        System.out.println(reversed);
    }

    public static void turnString3(String string) {
        String reversed = string.chars()
                .mapToObj(c -> (char) c)
                .collect(Collectors.collectingAndThen(Collectors.toList(), list -> {
                    Collections.reverse(list);
                    return list.stream()
                            .map(String::valueOf)
                            .collect(Collectors.joining());
                }));
        System.out.println(reversed);
    }

    public static String turnString4(String string) {
        if (string.length() <= 1) return string;

        return turnString4(string.substring(1)) + string.charAt(0);
    }


    // ЗАДАЧА 2
    // int[] ints = {1, 2, 2, 3, 4, 5, 5, 6, 7, 8, 8, 9};
    // Удалить дубликаты из массива и вывести в консоль
    public static void getDistinctNumbers1(int[] ints) {
        List<Integer> distinctList = Arrays.stream(ints)
                .distinct()
                .boxed()
                .toList();
        System.out.println(distinctList);
    }

    public static void getDistinctNumbers2(int[] ints) {
        Set<Integer> distinctSet = Arrays.stream(ints).boxed().collect(Collectors.toSet());
        System.out.println(distinctSet);
    }


    // ЗАДАЧА 3
    // Дан массив, заполненный уникальными значениями типа int.
    // int[] arr = {10, 15, 23, 11, 44, 13, 66, 1, 6, 47};
    // Необходимо найти элемент, который меньше максимума, но больше всех остальных.
    public static Integer findSecondMaxElement1(int[] arr) {
        Arrays.sort(arr);

        return arr[arr.length - 2];
    }

    public static Integer findSecondMaxElement2(int[] arr) {
        Integer maxElement = Arrays.stream(arr).max().getAsInt();
        List<Integer> list = new java.util.ArrayList<>(Arrays.stream(arr).boxed().toList());
        list.remove(maxElement);

        return list.stream().max(Integer::compareTo).stream().toList().get(0);
    }

    public static Integer findSecondMaxElement3(int[] arr) {
        return Arrays.stream(arr)
                .boxed()
                .sorted((a, b) -> b - a)
                .skip(1)
                .findFirst()
                .orElse(null);
    }

    public static Integer findSecondMaxElement4(int[] arr) {

        int max = Integer.MIN_VALUE;
        int secondMax = Integer.MIN_VALUE;

        for (int num : arr) {
            if (num > max) {
                secondMax = max;
                max = num;
            } else if (num > secondMax && num < max) {
                secondMax = num;
            }
        }

        return secondMax;
    }

    public static Integer findSecondMaxElement5(int[] arr) {

        PriorityQueue<Integer> queue = new PriorityQueue<>(Comparator.reverseOrder()); // min heap
        for (int num : arr) {
            queue.add(num);
        }

        if (queue.size() < 2) {
            return null;
        }

        queue.poll();

        return queue.peek();
    }


    // ЗАДАЧА 4
    // Найти длину последнего слова в строке. В строке только буквы и пробелы.
    // "Hello world" - 5
    // "    fly me    to the moon    " - 4
    public static Integer lengthOfLastWord(String string) {
        if (string == null || string.isEmpty()) {
            return 0;
        }
        string = string.trim();

        String[] words = string.split(" ");
        String lastWord = words[words.length - 1];
        if (lastWord.contains(".") || lastWord.contains("!")) return lastWord.length() - 1;
        return lastWord.length();
    }


    // ЗАДАЧА 5
    // Определить, что строка является палиндромом
    // Сложность по памяти O(1), не создавать новые String, StringBuilder
    // Примеры:
    // abc - false
    // 112233 - false
    // aba - true
    // 112211 - true
    public static boolean isPalindrome(String string) {
        if(string == null || string.isEmpty()) return true;

        char[] chars = string.toCharArray();
        int i2 = chars.length - 1;
        int mid = (chars.length - 1)/2;
        for (int i = 0; i < mid; i++) {
            if (chars[i] != chars[i2]) return false;
            i2--;
        }
        return true;
    }

}

