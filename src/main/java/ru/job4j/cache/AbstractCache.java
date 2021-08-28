package ru.job4j.cache;

import java.lang.ref.SoftReference;
import java.util.HashMap;
import java.util.Map;

/**
 * Создать структуру данных типа кеш. Кеш должен быть абстрактный.
 * То есть необходимо, что бы можно было задать ключ получения объекта кеша
 * и в случае если его нет в памяти, задать поведение загрузки этого объекта в кеш.
 *
 * @param <K> - имя файла
 * @param <V> - содержимое файла
 */
public abstract class AbstractCache<K, V> {
    protected final Map<K, SoftReference<V>> cache = new HashMap<>();

    public void put(K key, V value) {
        SoftReference<V> softValue = new SoftReference<>(value);
        cache.put(key, softValue);
    }

    public V get(K key) {
        if (cache.get(key) == null || cache.get(key).get() == null) {
            load(key);
        }
        return cache.get(key).get();
    }

    protected abstract V load(K key);
}
