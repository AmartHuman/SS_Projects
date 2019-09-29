/*
 * 
 * Alfredo Martinez
 * 
 * 
 *  Main File that handles UI and talking to the service class
 */
package com.ss.view;

import java.io.IOException;
import java.util.*;

import com.ss.service.*;

public class View {

	public static void main(String[] args) throws NumberFormatException, IOException {

		Service controller = new Service();

		controller.LoadFiles();

		Scanner userInput = new Scanner(System.in);

		/*
		 * Main Loop to keep going around the UI
		 * 
		 * Choice 1 = Author Menu Choice 2 = Book Menu Choice 3 = Publisher Menu
		 */
		while (true) {
			System.out.println(" _________________________________________________________");
			System.out.println("|*LMS ver 0.4                                     -- [] X |");
			System.out.println("|_________________________________________________________|");
			System.out.println("|__Welcome to the LMS System Please Select from the list__|");
			System.out.println("|                                                         |");
			System.out.println("|1)Author                                                 |");
			System.out.println("|2)Book                                                   |");
			System.out.println("|3)Publisher                                              |");
			System.out.println("|4)Display database                                       |");
			System.out.println("|0)Exit                                                   |");
			System.out.println("|_________________________________________________________|");
			System.out.print("| > ");
			Character choice = userInput.next().charAt(0);
			System.out.println("|_                                                        |_");

			// Author Menu to call the service and edit the database
			if (choice == '1') {
				while (true) {

					int authorId = 0;
					boolean bookCheck = false;
					String authorName = " ";
					System.out.println("  |___________________Edit Author Data______________________|");
					System.out.println("  |1.Create Author                                          |");
					System.out.println("  |2.Read Author                                            |");
					System.out.println("  |3.Update Author                                          |");
					System.out.println("  |4.Delete Author                                          |");
					System.out.println("  |0.Main Menu                                              |");
					System.out.println("  |_________________________________________________________|");
					System.out.print("  | > ");
					Character authorChoice = userInput.next().charAt(0);

					switch (authorChoice) {
					case '1':
						userInput.nextLine();
						System.out.println("  | Enter in an Author Name");
						System.out.println("  | ");
						System.out.print("  | Author Name: ");
						authorName = userInput.nextLine();
						controller.createAuthorObject(authorName, 0);
						break;
					case '2':
						controller.viewAuthorsDatabase();
						break;
					case '3':
						userInput.reset();
						controller.viewAuthorsDatabase();
						System.out.println("  | Please Select an Author to update in the database");
						System.out.print("  |>");
						try {
							authorId = userInput.nextInt();
							userInput.nextLine();
							System.out.print("  | Enter in new name: ");
							authorName = userInput.nextLine();
							controller.updateAuthor(authorId, authorName);
						} catch (InputMismatchException e) {
							System.out.println("  |  Not a valid ID format!");
						}

						break;
					case '4':
						userInput.nextLine();
						controller.viewAuthorsDatabase();

						System.out.print("  | > ");
						try {
							authorId = userInput.nextInt();
							while (true) {
								bookCheck = controller.deleteConnectedBooksAuthors(authorId);
								if (bookCheck != true) {
									break;
								}
							}
							controller.deleteAuthorObject(authorId);
						} catch (InputMismatchException e) {
							System.out.println("  |  Not a valid ID format!");

						}
						break;
					case '0':
						break;
					default:
						System.out.println("  | No such Entry Format");
					}
					if (authorChoice == '0') {
						break;
					}

				} // End of Author Menu loop

				// Book Menu to call the service and edit the database
			} else if (choice == '2') {
				while (true) {
					System.out.println("  |___________________Edit Books Data_______________________|");
					System.out.println("  |1.Create Book                                            |");
					System.out.println("  |2.Read Book                                              |");
					System.out.println("  |3.Update Book                                            |");
					System.out.println("  |4.Delete Book                                            |");
					System.out.println("  |0.Main Menu                                              |");
					System.out.println("  |_________________________________________________________|");
					System.out.print("  | > ");
					Character BookChoice = userInput.next().charAt(0);
					switch (BookChoice) {
					case '1':
						userInput.nextLine();
						String newBook = " ";
						String newAuth = " ";
						String newPublisher = " ";
						String newPublisherAddress = " ";
						int bookId = 0;
						System.out.println("  |__________|Please Enter in data for the new Book|________|");
						System.out.println("  |__________\\_____________________________________/________|");
						System.out.println("  |                                                         |");
						System.out.print("  | Book Title: ");
						newBook = userInput.nextLine();
						System.out.print("  | Author Name: ");
						newAuth = userInput.nextLine();
						System.out.print("  | Publishers Name: ");
						newPublisher = userInput.nextLine();
						System.out.print("  | Publishers Address: ");
						newPublisherAddress = userInput.nextLine();
						System.out.println("  |_________________________________________________________|");
						controller.createBookObject(newBook, newAuth, newPublisher, newPublisherAddress);
						break;
					case '2':
						controller.viewBooksDatabase();
						break;
					case '3':
						controller.viewBooksDatabase();
						System.out.println(" | Select a book by ID to update ");
						System.out.print("  |>");
						try {
							bookId = userInput.nextInt();
							System.out.println("  | What will you update the name to?");
							System.out.print("  |>");
							userInput.nextLine();
							String bookTitle = userInput.nextLine();
							controller.updateBook(bookId, bookTitle);
						} catch (InputMismatchException e) {
							System.out.println("  | Not a valid ID format!");
						}
						controller.viewBooksDatabase();
						break;
					case '4':
						userInput.reset();
						controller.viewBooksDatabase();
						System.out.println("  | Select a book by ID to delete ");
						System.out.print("  |>");
						try {
							bookId = userInput.nextInt();
							controller.deleteBookObject(bookId);
						} catch (InputMismatchException e) {
							System.out.println("  | Not a valid ID format!");
						}
						break;
					case '0':
						break;
					default:
						System.out.println("  | No such Entry Format!");
					}
					if (BookChoice == '0') {
						break;
					}
				}

				// Publisher Menu to call the service and edit the Publisher database
			} else if (choice == '3') {
				while (true) {
					String newPublisher = " ";
					int publisherId = 0;
					String publisherName = "N";
					boolean checkBooks = false;
					String publisherAddress = "N";
					System.out.println("  |___________________Edit Publisher Data___________________|");
					System.out.println("  |1.Create Publisher                                       |");
					System.out.println("  |2.Read Publisher                                         |");
					System.out.println("  |3.Update Publisher                                       |");
					System.out.println("  |4.Delete Publisher                                       |");
					System.out.println("  |0.Main Menu                                              |");
					System.out.println("  |_________________________________________________________|");
					System.out.print("  | > ");
					Character PublisherChoice = userInput.next().charAt(0);
					switch (PublisherChoice) {
					case '1':
						userInput.nextLine();
						System.out.println("Please Enter in data for the new Publisher");
						System.out.print("Publisher Name: ");
						newPublisher = userInput.nextLine() + "/";
						System.out.print("Address Name: ");
						newPublisher += userInput.nextLine();
						controller.createPublisherObject(newPublisher, 0);
						break;
					case '2':
						controller.viewPublishersDatabase();
						break;
					case '3':
						userInput.reset();

						System.out.println("  | What would you like to udpate");
						System.out.println("  |1) Publisher Name");
						System.out.println("  |2) Publisher Address");
						System.out.println("  |3) Both Name and Address");
						System.out.println("  |________________________");
						System.out.print("  |>");
						char publisherChoice = userInput.next().charAt(0);
						controller.viewPublishersDatabase();
						if (publisherChoice == '1') {
							System.out.println("  | Please select a Publisher to update in the database");
							System.out.print("  |>");
							try {
								publisherId = userInput.nextInt();
								userInput.nextLine();
								System.out.println("  | Enter New Name");
								System.out.print("  |>");
								publisherName = userInput.nextLine();
								userInput.reset();
								controller.updatePublisher(publisherId, publisherName, publisherAddress);
							} catch (InputMismatchException e) {
								System.out.println("  | Not a valid ID format!");
							}
						} else if (publisherChoice == '2') {
							System.out.println("  | Please select a Publisher to update in the database");
							System.out.print("  |>");
							try {
								publisherId = userInput.nextInt();
								userInput.nextLine();
								System.out.println("  | Enter New Address");
								System.out.print("  |>");
								publisherAddress = userInput.nextLine();
								controller.updatePublisher(publisherId, publisherName, publisherAddress);
							} catch (InputMismatchException e) {
								System.out.println("  | Not a valid ID format!");
							}
						} else {
							System.out.println("  | Please select a Publisher to update in the database");
							System.out.print("  |>");
							try {
								publisherId = userInput.nextInt();
								userInput.nextLine();
								System.out.println("  | Enter New Name");
								System.out.print("  |>");
								publisherName = userInput.nextLine();
								userInput.reset();
								System.out.println("  | Enter New Address");
								System.out.print("  |>");
								publisherAddress = userInput.nextLine();
								controller.updatePublisher(publisherId, publisherName, publisherAddress);
							} catch (InputMismatchException e) {
								System.out.println("  | Not a valid ID format!");
							}
						}
						break;
					case '4':
						userInput.reset();
						controller.viewPublishersDatabase();
						System.out.print(" |>");
						try {
							publisherId = userInput.nextInt();
							while (true) {
								checkBooks = controller.deleteConnectedBooksPublishers(publisherId);
								if (checkBooks != true) {
									break;
								}
							}
							controller.deletePublisherObject(publisherId);
						} catch (InputMismatchException e) {
							System.out.println("  | Not a valid ID format!");
						}

						break;
					case '0':
						break;
					default:
						System.out.println("  | No such Entry Format");
					}
					if (PublisherChoice == '0') {
						break;
					}
				}
			} else if (choice == '4') {

				System.out.println("  |           _____________________________________         |");
				System.out.println("  |__________/                                     \\________|");
				controller.viewAuthorsDatabase();
				System.out.println("  |           _____________________________________         |");
				System.out.println("  |__________/                                     \\________|");
				controller.viewBooksDatabase();
				System.out.println("  |           _____________________________________         |");
				System.out.println("  |__________/                                     \\________|");
				controller.viewPublishersDatabase();
				System.out.println("  |_________________________________________________________|");
			} else if (choice == '0') {
				break;
			} else {
				System.out.println("  | Not a choice!                                           |");
				System.out.println("  |_________________________________________________________|");
			} //
			System.out.println("  \\_________________________________________________________/");
			System.out.println();
			System.out.println();

		} // ENd of while loop
		System.out.println("  |Thank you for using the LMS System!                      |");
		System.out.println("  |_________________________________________________________|");
		userInput.close();
	}

}
