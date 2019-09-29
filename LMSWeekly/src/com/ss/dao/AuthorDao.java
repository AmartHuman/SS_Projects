/*
 * 
 * 
 * AuthorDao to read and write to the file and create the Author list
 * 
 * 
 */
package com.ss.dao;

import java.io.*;
import java.util.*;

import com.ss.model.Author;

public class AuthorDao {

	public Author authors;

	public List<Author> authorList = new ArrayList<Author>();

	public void readAuthorFile() throws IOException {

		String holdLine = " ";
		BufferedReader bufferAuthors = new BufferedReader(new FileReader("./resources/authors"));

		while ((holdLine = bufferAuthors.readLine()) != null) {
			String[] token = holdLine.split("/");

			authors = new Author(Integer.parseInt(token[0]), token[1]);
			authorList.add(authors);

		}
		bufferAuthors.close();

	}

	public void WriteAuthorFile() throws IOException {

		BufferedWriter bufferWrite = new BufferedWriter(new FileWriter("./resources/authors"));

		authorList.forEach(a -> {
			try {
				bufferWrite.write(a.getAuthorId() + "/" + a.getAuthorName() + "\n");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});

		bufferWrite.close();

	}

}
