package ru.job4j.ood.isp;

/*
1. Нарушение ISP - не на всех заправках есть мойка и магазин продуктов.
 */
interface GasStation {
    void refuel();
    void carWashing();
    void byuProducts();
}

/*
2. Нарушение ISP - не во всех магазинах есть отделы с бытовой химией и медикаментами.
 */
interface Shop {
    void foods();
    void chemicals();
    void medicines();
}

/*
3. Нарушение ISP - не всегда для работы с БД требуется чтение/запись файлов.
 */
interface BDSupport {
    void readFromBD();
    void writeToBD();
    void readFromFile();
    void writeToFile();
}
