package ru.job4j.ood.lsp;

public class LSPExample {
    public int solution(int a, int b) {
        if (a < 0 || b < 0) {
            throw new IllegalArgumentException();
        }
        return a + b;
    }
}

/*
Нарушение LSP - предусловие усиленно в подклассе.
 */
class ChildLSPExample extends LSPExample {
    @Override
    public int solution(int a, int b) {
        if (a < 10 || b < 10) {
            throw new IllegalArgumentException();
        }
        return a + b;
    }
}



