package ru.netology.manager;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ru.netology.domain.Book;
import ru.netology.domain.Product;
import ru.netology.domain.Smartphone;
import ru.netology.manager.ProductManager;
import ru.netology.repository.ProductRepository;

import static org.junit.jupiter.api.Assertions.*;

class ProductManagerTest {
    ProductRepository repository = new ProductRepository();
    ProductManager manager = new ProductManager(repository);

    Product indexZero = new Product(0, "Zero", 0);

    Book b1 = new Book(1, "B1", 10, "BA1");
    Book b2 = new Book(2, "B2", 15, "BA2");
    Book b3 = new Book(3, "B3", 30, "BA1");

    Smartphone s1 = new Smartphone(4, "S1", 500, "SA1");
    Smartphone s2 = new Smartphone(5, "S2", 700, "SA1");
    Smartphone s3 = new Smartphone(6, "S3", 500, "SA2");

    @BeforeEach
    public void setUp() {
        manager.add(indexZero);
        manager.add(b1);
        manager.add(b2);
        manager.add(b3);
        manager.add(s1);
        manager.add(s2);
        manager.add(s3);
    }

    @Test
    public void shouldSearchBookByName() {
        Product[] expected = new Product[]{b1};
        Product[] actual = manager.searchBy(b1.getName());

        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldSearchSmartphoneByName() {
        Product[] expected = new Product[]{s3};
        Product[] actual = manager.searchBy(s3.getName());

        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldSearchNotExistsBookByName() {
        repository.remoteById(2);
        Product[] expected = new Product[]{};
        Product[] actual = manager.searchBy(b2.getName());

        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldSearchNotExistsSmartphoneByName() {
        repository.remoteById(4);
        Product[] expected = new Product[]{};
        Product[] actual = manager.searchBy(s1.getName());

        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldSearchBookByAuthor() {
        Product[] expected = new Product[]{b1, b3};
        Product[] actual = manager.searchBy(b3.getAuthor());

        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldSearchNotExistsSmartphoneByManufacture() {
        Product[] expected = new Product[]{s1, s2};
        Product[] actual = manager.searchBy(s1.getManufacturer());

        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldSearchNotExist() {
        repository.remoteById(1);
        repository.remoteById(2);
        repository.remoteById(3);
        repository.remoteById(4);
        repository.remoteById(5);
        repository.remoteById(6);
        Product[] expected = new Product[]{};
        Product[] actual = manager.searchBy(null);
        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldSearchNotExistProduct() {

        Product[] expected = new Product[]{};
        Product[] actual = manager.searchBy(indexZero.getName());
        assertArrayEquals(expected, actual);
    }
}








