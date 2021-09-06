package ru.job4j.ood.lsp;

public class Rectangle {
    private int width;
    private int height;

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int perimeter() {
        return 2 * width + 2 * height;
    }
}

class Square extends Rectangle {
    public void setSide(int side) {
        super.setHeight(side);
        super.setWidth(side);
    }
}

/*
Прямоугольник и квадрат. (Классика от Роберта Мартина)
Нарушение LSP - не правильное наследование, при подстановке
базового типа (Rectangle) метод setSide() для квадрата не
доступен, тем самым можно ошибочно присвоить не правильные
значения сторон.
 */
class Perimeter {
    public static void main(String[] args) {
        Square square = new Square();
        square.setSide(5);
        System.out.println(square.perimeter());

        Rectangle figure = new Square();
        figure.setHeight(5);
        figure.setWidth(6);
        System.out.println(figure.perimeter());
    }
}
