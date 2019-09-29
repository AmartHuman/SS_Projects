/*
 * 
 * 
 * BookDao to read and write to the file and create the book list
 * 
 * 
 */
package com.ss.dao;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

import com.ss.model.Book;

public class BookDao {

	public Book book;

	public List<Book> bookList = new ArrayList<Book>();

	public void readBookFile() throws NumberFormatException, IOException {
		String holdLine = " ";
		BufferedReader bufferBooks = new BufferedReader(new FileReader("./resources/books"));

		while ((holdLine = bufferBooks.readLine()) != null) {
			String[] token = holdLine.split("/");

			book = new Book(Integer.parseInt(token[0]), token[1], Integer.parseInt(token[2]),
					Integer.parseInt(token[3]));
			bookList.add(book);

		}
		bufferBooks.close();
	}

	public void WriteBooksFile() throws IOException {
		BufferedWriter bufferWrite = new BufferedWriter(new FileWriter("./resources/books"));

		bookList.forEach(b -> {
			try {
				bufferWrite.write(b.getBookId() + "/" + b.getBookName() + "/" + b.getBookAuthor() + "/"
						+ b.getBookPublisher() + "\n");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
		bufferWrite.close();

	}

}
