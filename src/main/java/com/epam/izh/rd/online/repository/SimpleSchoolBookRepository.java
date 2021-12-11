package com.epam.izh.rd.online.repository;

import com.epam.izh.rd.online.entity.Book;
import com.epam.izh.rd.online.entity.SchoolBook;

import java.util.Arrays;
import java.util.Objects;

public class SimpleSchoolBookRepository implements BookRepository{
private SchoolBook[] schoolBooks = new SchoolBook[]{};



    @Override
    public boolean save(SchoolBook book) {
        SchoolBook[] saveBook = new SchoolBook[schoolBooks.length + 1];
        System.arraycopy(schoolBooks,0,saveBook,0,schoolBooks.length);
        saveBook[schoolBooks.length] = book;
        schoolBooks = saveBook;
        return true;
    }


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

    @Override
    public int count() {
        return schoolBooks.length;
    }
}
