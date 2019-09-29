/*
 * 
 * Service class that handles talking to the DAO's that read and wirte to the files
 * 
 */
package com.ss.service;

import java.io.IOException;
import java.util.Random;

import com.ss.dao.*;
import com.ss.model.*;

public class Service {

	// used to change the colors in the console
	public static final String YELLOW_BACKGROUND = "\033[43m";
	public static final String RESET = "\033[0m";
	public static final String CYAN_BACKGROUND = "\033[46m";
	public static final String RED_BACKGROUND = "\033[41m";
	public static final String GREEN_BACKGROUND = "\033[42m";
	public static final String BLUE_BACKGROUND = "\033[44m";

	private static final Random RANDOM = new Random();

	// Create the DAO objects
	AuthorDao authorDao = new AuthorDao();
	BookDao bookDao = new BookDao();
	PublisherDao publisherDao = new PublisherDao();

	/*
	 * Calls to Load Files from the DAO objects
	 */
	public void LoadFiles() {
		try {
			authorDao.readAuthorFile();
		} catch (IOException e) {

			e.printStackTrace();
		}
		try {
			bookDao.readBookFile();
		} catch (NumberFormatException | IOException e) {

			e.printStackTrace();
		}
		try {
			publisherDao.readPublisherFile();
		} catch (IOException e) {

			e.printStackTrace();
		}
	}

	/*
	 * 
	 * 
	 * 
	 */
	public int createAuthorObject(String userInput, int objectId) {

		int generateDatabaseId = RANDOM.nextInt(100000) + 1;
		Integer sizeOfList = authorDao.authorList.size() + 1;
		String[] token = userInput.split("/");
		if (objectId == 0) {
			authorDao.authors = new Author(generateDatabaseId, token[0]);
		} else {
			authorDao.authors = new Author(objectId, token[0]);
		}
		authorDao.authorList.add(authorDao.authors);
		try {
			authorDao.WriteAuthorFile();
		} catch (IOException e) {

			e.printStackTrace();
		}
		System.out.println("  | " + GREEN_BACKGROUND + authorDao.authorList.get(sizeOfList - 1).getAuthorName() + RESET
				+ " Has been added to the Database!");
		if (objectId == 0)
			return generateDatabaseId;
		else
			return objectId;
	}

	public int createPublisherObject(String userInput, int objectId) {

		int generateDatabaseId = RANDOM.nextInt(100000) + 1;

		Integer sizeOfList = publisherDao.publisherList.size() + 1;
		String[] token = userInput.split("/");

		if (objectId == 0) {
			publisherDao.publishers = new Publisher(generateDatabaseId, token[0], token[1]);
		} else {
			publisherDao.publishers = new Publisher(objectId, token[0], token[1]);
		}

		publisherDao.publisherList.add(publisherDao.publishers);
		try {
			publisherDao.WritePublishersFile();
		} catch (IOException e) {

			e.printStackTrace();
		}
		System.out.println("  | " + GREEN_BACKGROUND + publisherDao.publisherList.get(sizeOfList - 1).getPublisherName()
				+ " | " + publisherDao.publisherList.get(sizeOfList - 1).getPublisherAddress() + RESET
				+ " Has been added to the Database!");
		System.out.println("  |______________________________");
		if (objectId == 0) {
			return generateDatabaseId;
		} else
			return objectId;
	}

	/*
	 * 
	 * Functions to create objects
	 * 
	 */
	public void createBookObject(String bookName, String authorName, String publisherName, String publisherAddress) {

		int generateBookId = RANDOM.nextInt(100000) + 1;

		System.out.println("  | Searching for Author in Database...");
		Boolean check = false;
		Integer authorId = 0, publisherId = 0;

		for (Author author : authorDao.authorList) {
			if (author.getAuthorName().equalsIgnoreCase(authorName)) {
				System.out.println("  | FOUND!!!");
				System.out.println("  |______________________________");
				authorId = author.getAuthorId();
				check = true;
				break;
			}
		}
		if (check == false) {
			System.out.println("  | Author not found adding to database!");
			authorId = createAuthorObject(authorName, 0);
		}

		check = false;
		System.out.println("  | Searching for publisher in Database...");
		for (Publisher publisher : publisherDao.publisherList) {
			if (publisher.getPublisherName().equalsIgnoreCase(publisherName)) {
				System.out.println("  | FOUND!");
				System.out.println("  |______________________________");
				publisherId = publisher.getPublisherId();
				check = true;
				break;
			}
		}
		if (check == false) {
			publisherName += "/" + publisherAddress;
			System.out.println("  | Publisher not found adding to database!");
			publisherId = createPublisherObject(publisherName, 0);

		}

		bookDao.book = new Book(generateBookId, bookName, authorId, publisherId);
		bookDao.bookList.add(bookDao.book);
		try {
			bookDao.WriteBooksFile();
		} catch (IOException e) {

			e.printStackTrace();
		}
		System.out.println("  | " + " Has been added to the Database!");
		System.out.println("  |______________________________");
	}
	/*
	 * 
	 * End of Create Objects
	 * 
	 */

	/*
	 * 
	 * Funtions to View the Database
	 * 
	 */
	public void viewAuthorsDatabase() {

		System.out.println("  |__________|           Author DATABASE           |________|");
		System.out.println("  |__________\\_____________________________________/________|");
		System.out.println("  |    |                                                    |");
		System.out.println("  |_ID_|_Names______________________________________________|");
		for (Author author : authorDao.authorList) {
			System.out.print(String.format("%-10s", "  |" + " " + author.getAuthorId()));
			System.out.print(String.format("%-10s", " | " + author.getAuthorName()));
			System.out.println();
		}
	}

	public void viewBooksDatabase() {

		System.out.println("  |__________|           BOOK DATABASE             |________|");
		System.out.println("  |__________\\_____________________________________/________|");
		System.out.println("  |    |                                                    |");
		System.out.println("  |_ID_|_Title__|_Author__|_Publisher_______________________|");
		for (Book book : bookDao.bookList) {
			System.out.print(String.format("%-10s", "  |" + " " + book.getBookId()));
			System.out.print(String.format("%-10s", " | " + " " + book.getBookName()));
			System.out.print(String.format("%-10s", " | " + " " + book.getBookAuthor()));
			System.out.print(String.format("%-10s", " | " + " " + book.getBookPublisher()));
			System.out.println();
		}
	}

	public void viewPublishersDatabase() {

		System.out.println("  |__________|           Publisher DATABASE        |________|");
		System.out.println("  |__________\\_____________________________________/________|");
		System.out.println("  |    |                                                    |");
		System.out.println("  |_ID_|_Names__|______Address______________________________|");
		for (Publisher publisher : publisherDao.publisherList) {
			System.out.print(String.format("%-10s", "  |" + " " + publisher.getPublisherId()));
			System.out.print(String.format("%-10s", " | " + " " + publisher.getPublisherName()));
			System.out.print(String.format("%40s", " | " + " " + publisher.getPublisherAddress()));
			System.out.println();
		}

	}
	/*
	 * End of view databases
	 * 
	 */

	/*
	 * 
	 * Funtions to update objects
	 * 
	 */
	public void updateAuthor(int authorId, String authorName) {

		@SuppressWarnings("unused")
		boolean check = false;
		int index = 0;
		for (Author author : authorDao.authorList) {
			if (author.getAuthorId() == authorId) {
				check = true;
				break;
			}
			index++;
		}

		if (check = true) {
			authorDao.authorList.get(index).setAuthorName(authorName);
			try {
				authorDao.WriteAuthorFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
			System.out.println("  | " + GREEN_BACKGROUND + authorDao.authorList.get(index).getAuthorName() + RESET
					+ " Has been Updated on the Database!");
			System.out.println("  |______________________________");

		} else {
			System.out.println(RED_BACKGROUND + "  | No such ENtry Fount!" + RESET);
			System.out.println("  |_____________________________|");
		}

	}

	public void updateBook(int bookId, String bookTitle) {
		@SuppressWarnings("unused")
		boolean check = false;
		int index = 0;
		for (Book book : bookDao.bookList) {
			if (book.getBookId() == bookId) {
				check = true;
				break;
			}
			index++;
		}

		if (check = true) {
			bookDao.bookList.get(index).setBookName(bookTitle);
			try {
				bookDao.WriteBooksFile();
			} catch (IOException e) {

				e.printStackTrace();
			}
			System.out.println("  | " + GREEN_BACKGROUND + bookDao.bookList.get(index).getBookName() + RESET
					+ " Has been Updated on the Database!");
			System.out.println("  |______________________________");

		} else {
			System.out.println(RED_BACKGROUND + "  | No such ENtry Fount!" + RESET);
			System.out.println("  |_____________________________|");
		}
	}

	public void updatePublisher(int publisherId, String publisherName, String publisherAddress) {

		int index = 0;
		@SuppressWarnings("unused")
		boolean check = false;

		for (Publisher publisher : publisherDao.publisherList) {
			if (publisher.getPublisherId() == publisherId) {
				check = true;
				break;
			}
			index++;
		}
		if (check = true) {
			System.out.println(publisherName);
			if (!publisherName.equals("N"))
				publisherDao.publisherList.get(index).setPublisherName(publisherName);
			if (!publisherAddress.equals("N"))
				publisherDao.publisherList.get(index).setPublisherAddress(publisherAddress);
		}

		System.out.println("    |The database has been updated ");
		System.out.println("    |                              ");
		System.out.println("    |" + publisherDao.publisherList.get(index).getPublisherId() + " "
				+ publisherDao.publisherList.get(index).getPublisherName() + " "
				+ publisherDao.publisherList.get(index).getPublisherAddress());
		System.out.println("    |_______________________________________________________ ");
		System.out.println("   _|                                                       |");
		try {
			publisherDao.WritePublishersFile();
		} catch (IOException e) {

			e.printStackTrace();
		}

	}
	/*
	 * End of update functions
	 * 
	 */

	/*
	 * 
	 * Functions to delete objecst from the databases
	 * 
	 */
	public void deleteAuthorObject(int authorId) {

		int count = 0;

		for (Author author : authorDao.authorList) {
			if (author.getAuthorId().equals(authorId)) {
				break;
			}
			count++;
		}

		System.out.println("  | Author: " + RED_BACKGROUND + authorDao.authorList.get(count).getAuthorName() + RESET
				+ " has been removed!");
		authorDao.authorList.remove(count);

		try {
			authorDao.WriteAuthorFile();
		} catch (IOException e) {

			e.printStackTrace();
		}

	}

	public void deletePublisherObject(int publisherId) {
		int count = 0;

		for (Publisher publisher : publisherDao.publisherList) {
			if (publisher.getPublisherId() == publisherId) {
				break;
			}
			count++;
		}

		System.out.println("  | Publisher: " + RED_BACKGROUND + publisherDao.publisherList.get(count).getPublisherName()
				+ RESET + " has been removed!");
		publisherDao.publisherList.remove(count);
		try {
			publisherDao.WritePublishersFile();
		} catch (IOException e) {

			e.printStackTrace();
		}

	}

	public void deleteBookObject(int objectId) {

		int count = 0;

		for (Book book : bookDao.bookList) {
			if (book.getBookId().equals(objectId)) {
				break;
			}
			count++;
		}
		System.out.println("  | Publisher: " + RED_BACKGROUND + bookDao.bookList.get(count).getBookName() + RESET
				+ " has been removed!");
		bookDao.bookList.remove(count);
		try {
			bookDao.WriteBooksFile();
		} catch (IOException e) {

			e.printStackTrace();
		}

	}

	/*
	 * Delete connected functions are used to check to see if the Author or
	 * Publisher have books in the data base and if they do they will be deleted
	 * with them.
	 * 
	 */
	public boolean deleteConnectedBooksAuthors(int objectId) {
		int count = 0;
		int num = 0;

		for (Book book : bookDao.bookList) {
			if (book.getBookAuthor().equals(objectId)) {
				num = 1;
				break;
			}
			count++;
		}

		if (num == 1) {
			System.out.println(count);
			bookDao.bookList.remove(count);
			return true;
		} else {
			try {
				bookDao.WriteBooksFile();
			} catch (IOException e) {

				e.printStackTrace();
			}
			return false;
		}
	}

	public boolean deleteConnectedBooksPublishers(int objectId) {
		int count = 0;
		int num = 0;

		for (Book book : bookDao.bookList) {
			if (book.getBookPublisher().equals(objectId)) {
				num = 1;
				break;
			}
			count++;
		}

		if (num == 1) {
			System.out.println(count);
			bookDao.bookList.remove(count);
			return true;
		} else {
			try {
				bookDao.WriteBooksFile();
			} catch (IOException e) {

				e.printStackTrace();
			}
			return false;
		}

	}
	/*
	 * 
	 * End of delete functions
	 * 
	 */

}
