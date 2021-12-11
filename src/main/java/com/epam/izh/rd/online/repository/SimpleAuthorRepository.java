package com.epam.izh.rd.online.repository;

import com.epam.izh.rd.online.entity.Author;

public class SimpleAuthorRepository implements AuthorRepository{
private Author[] authors = new Author[]{};



    @Override
    public boolean save(Author author) {
        if(findByFullName(author.getName(), author.getLastName()) != null) {
            return false;
        }
        Author[] saveAuthors = new Author[authors.length + 1];
        System.arraycopy(authors, 0, saveAuthors, 0, authors.length);
        saveAuthors [authors.length] = author;
        authors = saveAuthors;
        return true;
    }


    @Override
    public Author findByFullName(String name, String lastname) {
        for(Author author : authors) {
            if(author.getName().equals(name) && author.getLastName().equals(lastname)) {
                return author;
            }
        }
        return null;
    }


    @Override
    public boolean remove(Author author) {

        if(findByFullName(author.getName(), author.getLastName()) == null) {
            return false;
        } else {
            Author[]  removeAuthor = new Author[authors.length - 1];
            int i = 0;
            for (Author foundAuthor  : authors) {
                if (foundAuthor != findByFullName(author.getName(), author.getLastName())) {
                    removeAuthor [i] = foundAuthor;
                    i++;
                }
            }
            authors =  removeAuthor ;
            return true;
        }
    }

    @Override
    public int count() {
        return authors.length;
    }
}
