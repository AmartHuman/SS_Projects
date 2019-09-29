/*
 * 
 * 
 * PublisherDao to read and write to the file and create the Publisher list
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

import com.ss.model.Publisher;

public class PublisherDao {

	public Publisher publishers;

	public List<Publisher> publisherList = new ArrayList<Publisher>();

	public void readPublisherFile() throws IOException {
		String holdLine = " ";
		BufferedReader bufferPublishers = new BufferedReader(new FileReader("./resources/publishers"));

		while ((holdLine = bufferPublishers.readLine()) != null) {
			String[] token = holdLine.split("/");

			publishers = new Publisher(Integer.parseInt(token[0]), token[1], token[2]);
			publisherList.add(publishers);
		}
		bufferPublishers.close();
	}

	public void WritePublishersFile() throws IOException {
		BufferedWriter bufferWrite = new BufferedWriter(new FileWriter("./resources/publishers"));

		publisherList.forEach(p -> {
			try {
				bufferWrite
						.write(p.getPublisherId() + "/" + p.getPublisherName() + "/" + p.getPublisherAddress() + "\n");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});

		bufferWrite.close();

	}

}
