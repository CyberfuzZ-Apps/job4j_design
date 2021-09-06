package ru.job4j.ood.lsp;

public class LSPException {

    public int sum(int a, int b) {
        return a + b;
    }
}

/* Нарушение LSP - нельзя добавлять исключения
* в переопределенный метод супер-класса, если этого
* исключения не было в супер-классе.
*/
class ChildLSPException extends LSPException {
    @Override
    public int sum(int a, int b) {
        if (a < 0 || b < 0) {
            throw new IllegalArgumentException();
        }
        return super.sum(a, b);
    }
}
