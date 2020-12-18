package bean;

import org.springframework.stereotype.Service;

@Service
public class Book {
    public static void main(String[] args){
        int num = 5078;
        int a = num/1000;
        int b =num/100%10;
        int c =num/10%10;
        int d =num%10;
        System.out.println(b*100+c*10+d);
    }

    private static int pan(int num) {
        // TODO Auto-generated method stub
        int count = 0;
        while (num > 1) {
            num /= 10;
            count++;
        }
        return count + 1;
    }
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