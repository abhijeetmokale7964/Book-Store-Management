package com.bookStore.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "my_book_list")
public class MyBookList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int bookId;
    private String name;
    private String author;
    private String price;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getBookId() {
		return bookId;
	}

	public void setBookId(int bookId) {
		this.bookId = bookId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public MyBookList(int id, int bookId, String name, String author, String price, User user) {
		super();
		this.id = id;
		this.bookId = bookId;
		this.name = name;
		this.author = author;
		this.price = price;
		this.user = user;
	}

	public MyBookList() {
		super();
		// TODO Auto-generated constructor stub
	}

	public MyBookList(int id2, String name2, String author2, String price2, User user2) {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "MyBookList [id=" + id + ", bookId=" + bookId + ", name=" + name + ", author=" + author + ", price="
				+ price + ", user=" + user + "]";
	}

 
    
    
}
