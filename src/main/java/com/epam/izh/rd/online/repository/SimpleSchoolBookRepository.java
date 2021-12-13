package com.epam.izh.rd.online.repository;

import com.epam.izh.rd.online.entity.Book;
import com.epam.izh.rd.online.entity.SchoolBook;

import java.util.Arrays;
import java.util.Objects;

public class SimpleSchoolBookRepository implements BookRepository{
private SchoolBook[] schoolBooks = new SchoolBook[]{};
    /**
     * Метод должен сохранять школьную книгу в массив schoolBooks.
     * Массив при каждом сохранении должен увеличиваться в размере ровно на 1.
     * То есть он ровно того размера, сколько сущностей мы в него сохранили.
     * <p>
     * Одну и ту же книгу МОЖНО сохранить в массиве несколько раз, проверки на то, что такая книга уже сохранена, делать не нужно.
     * <p>
     * Если сохранение прошло успешно, метод должен вернуть true.
     */

    @Override
    public boolean save(SchoolBook book) {
        SchoolBook[] saveBook = new SchoolBook[schoolBooks.length + 1];
        System.arraycopy(schoolBooks,0,saveBook,0,schoolBooks.length);
        saveBook[schoolBooks.length] = book;
        schoolBooks = saveBook;
        return true;
    }
    /**
     * Метод должен находить в массиве schoolBooks все книги по имени.
     * <p>
     * Если книги найдены - метод должен их вернуть.
     * Если найденных по имени книг нет, должен вернуться пустой массив.
     */

    @Override
    public Book[] findByName(String name) {

        SchoolBook[] findBook = new SchoolBook[]{};
        for (SchoolBook schoolBook : schoolBooks) {
            if (schoolBook.getName().equals(name)) {
                findBook = Arrays.copyOf(findBook, findBook.length + 1);
                findBook[findBook.length - 1] = schoolBook;
            }
        }
        return findBook;
    }
    /**
     * Метод должен удалять книги из массива schoolBooks по названию.
     * Если книг с одинаковым названием в массиве несколько, метод должен удалить их все.
     * <p>
     * Важно: при удалении книги из массива размер массива должен уменьшиться!
     * То есть, если мы сохранили 2 разные книги и вызвали count() (метод ниже), то он должен вернуть 2.
     * Если после этого мы удалили 1 книгу, метод count() должен вернуть 1.
     * <p>
     * Если хотя бы одна книга была найдена и удалена, метод должен вернуть true, в противном случае,
     * если книга не была найдена, метод должен вернуть false.
     */
    @Override
    public boolean removeByName(String name) {

        if(findByName(name).length == 0) {
            return false;
        } else {
            SchoolBook[] booksCount = new SchoolBook[schoolBooks.length - findByName(name).length];
            int i = 0;
            for(SchoolBook booksFound : schoolBooks) {
                if (Objects.equals(booksFound.getName(),name)) {
                    continue;
                }
                booksCount[i] = booksFound;
                i++;
            }
            schoolBooks = booksCount;
            return true;
        }
    }
    /**
     * Метод возвращает количество сохраненных сущностей в массиве schoolBooks.
     */
    @Override
    public int count() {
        return schoolBooks.length;
    }
}
