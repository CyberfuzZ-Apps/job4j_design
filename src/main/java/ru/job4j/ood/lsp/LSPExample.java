package ru.job4j.ood.lsp;

public class LSPExample { // Parent
    //some logic
    public int solution(int a, int b) {
        return a + b;
    }
}

class ChildLSPExample extends LSPExample { //Child
    // some OTHER logic
    public int childSolution(int a, int b, int c) {
        return a + b + c;
    }
}

class Main {
    public static void main(String[] args) {

        /* Тут всё нормально работает */
        ChildLSPExample childLSPExample = new ChildLSPExample();


        /* Но если суб-тип заменить на базовый тип,
         * то программа может упасть из-за не правильного наследования -
         * нарушение LSP */
        LSPExample lspExample = new ChildLSPExample(); // здесь метод childSolution() - не доступен.
    }
}



