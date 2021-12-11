package com.epam.izh.rd.online.service;

import com.epam.izh.rd.online.entity.Author;
import com.epam.izh.rd.online.entity.Book;
import com.epam.izh.rd.online.entity.SchoolBook;
import com.epam.izh.rd.online.repository.BookRepository;

public class SimpleSchoolBookService implements  BookService {
    private BookRepository<SchoolBook> schoolBookBookRepository;
    private AuthorService authorService;

    public SimpleSchoolBookService(){

    }
    public SimpleSchoolBookService(BookRepository<SchoolBook> schoolBookBookRepository, AuthorService authorService){
        this.schoolBookBookRepository = schoolBookBookRepository;
        this.authorService = authorService;


    }
    @Override
    public boolean save(Book book) {
        SchoolBook diffBook = ((SchoolBook) book);
        if (this.authorService.findByFullName(diffBook.getAuthorName(), diffBook.getAuthorLastName()) != null) {
            this.schoolBookBookRepository.save(diffBook);
            return true;
        }
        return false;
    }

    @Override
    public Book[] findByName(String name) {
        return schoolBookBookRepository.findByName(name);
    }



    @Override
    public int getNumberOfBooksByName(String name) {
        return schoolBookBookRepository.findByName(name).length;
    }

    @Override
    public boolean removeByName(String name) {
        return schoolBookBookRepository.removeByName(name);
    }


    @Override
    public int count() {
        return schoolBookBookRepository.count();
    }

    @Override
    public Author findAuthorByBookName(String name) {

        if (authorService.findByFullName(schoolBookBookRepository.findByName(name)[0].getAuthorName(), schoolBookBookRepository.findByName(name)[0].getAuthorLastName()) != null) {
            return authorService.findByFullName(schoolBookBookRepository.findByName(name)[0].getAuthorName(), schoolBookBookRepository.findByName(name)[0].getAuthorLastName());
        } else
            return null;
    }
}

