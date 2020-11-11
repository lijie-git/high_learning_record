package bean;

import org.springframework.stereotype.Service;

@Service
public class Book {
    private String bookName ="ssssssssss";

    private String author="sss";

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void printBookInfo() {
        System.out.println("bean.Book Name：" + this.bookName + ",Author：" + this.author);
    }
}