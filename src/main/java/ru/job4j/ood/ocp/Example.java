package ru.job4j.ood.ocp;

import java.util.ArrayList;

public class Example {
    public int calculatePlus(int a, int b) {
        return a + b;
    }

    public int calculateMinus(int a, int b) {
        return a - b;
    }

    public int calculateMulti(int a, int b) {
        return a * b;
    }

    public int calculateDivide(int a, int b) {
        return a / b;
    }

    /*
    1. Нарушение OCP - был класс Example и по прошествии времени
    понадобилось добавить в него функционал возведения в степень,
    пришлось добавить в класс еще один метод -

    public int calculatePow(int a, int b) {
        return Math.pow(a, b);
    }

    это нарушение OCP - класс должен быть закрыт для изменения.
     */
}
//_____________________________________________________________

class Example2 {
    ArrayList<Integer> arrayList;

    public Example2(ArrayList<Integer> arrayList) {
        this.arrayList = arrayList;
    }
    /*
    2. Нарушение OCP - поля должны представлять тип абстракций, а не
    конкретной реализации.
     */
}
//_____________________________________________________________

class Example3 {

    public ArrayList<Integer> sort(ArrayList<Integer> arrayList) {
        //some logic
        return arrayList;
    }
    /*
    3. Нарушение OCP - параметры метода и возвращаемые типы должны
    быть абстракциями, а не конкретными реализациями.
     */
}
