package com.capgemini.csvFile;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

import com.opencsv.CSVWriter;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;

public class AddressBookMain {

	static HashMap<String, ArrayList<AddressBookContent>> hm = new HashMap<>();

	public static void main(String[] args)
			throws IOException, CsvDataTypeMismatchException, CsvRequiredFieldEmptyException {

		Scanner sc = new Scanner(System.in);
		System.out.println("Welcome to Address Book\r\n" + "Program in\r\n" + "AddressBookMain class \r\n");

		HashMap<String, ArrayList<AddressBookContent>> hm = new HashMap<>();
		Map<String, ArrayList<AddressBookContent>> cityList = new HashMap<>();
		Map<String, ArrayList<AddressBookContent>> stateList = new HashMap<>();

		String path = "F:\\Capgemini workspace";
		String directory = "Address Book Directory";

		int choice = 1;

		while (choice != 0) {

			System.out.println("Enter \n1 to add\n2 to edit \n3 to delete \n"
					+ "4 to add new address book \n5 to display \n6 to search by name"
					+ "\n7 to search by state \n8 to view by city" + "\n9 to view by state"
					+ "\n10 Print count of contacts in particular city\n"
					+ "11 Print count of contacts in particular state" + "\n12 To sort using first name"
					+ "\n13 To sort using city \n14 To sort using state" + "\n15 To sort using zipcode "
					+ "\n16 to write to file \n17 To read from file\n18 to write to csv file\n19 to read from csv"
					+ "\n0 to exit"); 
			choice = sc.nextInt();
			switch (choice) {

			case 1:
				System.out.println(hm.keySet());
				System.out.println("Enter the name of the address book you want to add contact");
				String name = sc.next();
				Iterator<String> itr = hm.keySet().iterator();
				ArrayList<AddressBookContent> listtmp = new ArrayList<>();
				while (itr.hasNext()) {
					String tmp = itr.next();
					if (tmp.equals(name))
						listtmp = hm.get(name);
				}
				System.out.println("Enter first name");
				String fname = sc.next();
				System.out.println("Enter last name");
				String lname = sc.next();
				System.out.println("Enter Address");
				String add = sc.nextLine();
				sc.nextLine();
				System.out.println("Enter City");
				String city = sc.next();
				System.out.println("Enter state");
				String state = sc.next();
				sc.nextLine();
				System.out.println("Enter zip code");
				int zipcode = sc.nextInt();
				System.out.println("Enter Phone Number");
				String ph = sc.next();
				System.out.println("Enter E-Mail");
				String mail = sc.next();

				AddressBookContent a1 = new AddressBookContent(fname, lname, add, city, state, zipcode, ph, mail);

				// to find the duplicate contacts in the address books
				int count = (int) listtmp.stream().filter(i -> i.equals(a1)).count();
				if (count == 0) {
					listtmp.add(a1);
					hm.put(name, listtmp);
					System.out.println("Contact added successfully to " + name);
				} else
					System.out.println("Duplicate contact found");

				break;

			case 2:
				System.out.println(hm.keySet());
				System.out.println("Enter the name of the address book you want to edit");
				String name1 = sc.next();
				Iterator<String> itr1 = hm.keySet().iterator();
				ArrayList<AddressBookContent> listtmp1 = new ArrayList<>();
				while (itr1.hasNext()) {
					String tmp = itr1.next();
					if (tmp.equals(name1))
						listtmp1 = hm.get(name1);
				}
				System.out.println("Enter first name to edit");
				String first = sc.next();
				sc.nextLine();
				int pos = -1;
				for (int i = 0; i < listtmp1.size(); i++) {
					if (listtmp1.get(i).getFirstName().equals(first))
						pos = i;
				}
				System.out.println("Choose the option to edit");
				System.out.println("1.Edit Last name");
				System.out.println("2.Edit Address");
				System.out.println("3.Edit City");
				System.out.println("4.Edit Zip");
				System.out.println("5.Edit Phone Number");
				System.out.println("6.Edit Email");
				System.out.println("7.Exit");

				int option = sc.nextInt();
				if (option == 1)
					listtmp1.get(pos).setLastName(sc.next());
				else if (option == 2) {
					listtmp1.get(pos).setAddress(sc.nextLine());
					sc.nextLine();
				} else if (option == 3)
					listtmp1.get(pos).setCity(sc.next());
				else if (option == 4)
					listtmp1.get(pos).setZip(sc.nextInt());
				else if (option == 5)
					listtmp1.get(pos).setPhoneNumber(sc.next());
				else if (option == 6)
					listtmp1.get(pos).setEmail(sc.next());
				else
					break;
				hm.put(name1, listtmp1);
				System.out.println("Contact updated succesfully");
				break;
			case 3:
				System.out.println(hm.keySet());
				System.out.println("Enter the name of the address book you want to edit and delete");
				String name2 = sc.next();
				Iterator<String> itr2 = hm.keySet().iterator();
				ArrayList<AddressBookContent> listtmp2 = new ArrayList<>();
				while (itr2.hasNext()) {
					String tmp = itr2.next();
					if (tmp.equals(name2))
						listtmp2 = hm.get(name2);
				}
				System.out.println("Enter first name to delete the contact");
				String firstdel = sc.next();
				sc.nextLine();
				int pos1 = -1;
				for (int i = 0; i < listtmp2.size(); i++) {
					if (listtmp2.get(i).getFirstName().equals(firstdel))
						pos1 = i;
				}
				listtmp2.remove(pos1);
				hm.put(name2, listtmp2);
				System.out.println("Contact deleted succesfully");

			case 4:
				System.out.println("Enter the name of the address book");
				String addressBookname = sc.next();
				ArrayList<AddressBookContent> list = new ArrayList<>();
				hm.put(addressBookname, list);
				break;

			case 5:
				System.out.println(hm.keySet());
				System.out.println("Enter the name of the address book you want to display");
				String name3 = sc.next();
				Iterator<String> itr3 = hm.keySet().iterator();
				ArrayList<AddressBookContent> listtmp3 = new ArrayList<>();
				while (itr3.hasNext()) {
					String tmp = itr3.next();
					if (tmp.equals(name3))
						listtmp3 = hm.get(name3);
				}
				for (int i = 0; i < listtmp3.size(); i++) {
					System.out.println(listtmp3.get(i).getFirstName());
					System.out.println(listtmp3.get(i).getLastName());
					System.out.println(listtmp3.get(i).getAddress());
					System.out.println(listtmp3.get(i).getCity());
					System.out.println(listtmp3.get(i).getZip());
					System.out.println(listtmp3.get(i).getEmail());
					System.out.println(listtmp3.get(i).getPhoneNumber());
					System.out.println();
				}
				break;

			case 6:
				System.out.println("Enter the city to search contacts");
				String city1 = sc.next();
				if (hm.isEmpty()) {
					System.out.println("No AddressBook Exists, add new AddressBook First");
					System.exit(0);
				}
				for (Map.Entry<String, ArrayList<AddressBookContent>> ab : hm.entrySet()) {

					List<AddressBookContent> c = ab.getValue().stream().filter(i -> i.getCity().equals(city1))
							.collect(Collectors.toList());
					if (c.size() == 0)
						System.out.println("No entry with city name: " + city1 + " in addressbook " + ab.getKey());

					else
						for (int j = 0; j < c.size(); j++) {
							System.out.println("AddressBook " + ab.getKey() + " Name " + c.get(j).getFirstName() + " "
									+ c.get(j).getLastName());
						}
				}
				break;

			case 7:
				System.out.println("Enter the state to search contacts");
				String state1 = sc.next();
				if (hm.isEmpty()) {
					System.out.println("No AddressBook Exists, add new AddressBook First");
					System.exit(0);
				}
				for (Map.Entry<String, ArrayList<AddressBookContent>> ab : hm.entrySet()) {

					List<AddressBookContent> c = ab.getValue().stream().filter(i -> i.getState().equals(state1))
							.collect(Collectors.toList());

					if (c.size() == 0)
						System.out.println("No entry with state name: " + state1 + " in addressbook " + ab.getKey());

					else
						for (int j = 0; j < c.size(); j++)
							System.out.println("AddressBook " + ab.getKey() + " Name " + c.get(j).getFirstName() + " "
									+ c.get(j).getLastName());
				}
				break;

			case 8:
				System.out.println("Enter the city to view contacts");
				String city11 = sc.next();
				List<AddressBookContent> c = cityList.get(city11);
				for (int j = 0; j < c.size(); j++) {
					System.out.println(c.get(j).getCity());
					System.out.println(" Name " + c.get(j).getFirstName() + " " + c.get(j).getLastName());
				}
				break;

			case 9:
				System.out.println("Enter the state to view contacts");
				String state11 = sc.next();
				List<AddressBookContent> c1 = stateList.get(state11);
				for (int j = 0; j < c1.size(); j++) {
					System.out.println(" Name " + c1.get(j).getFirstName() + " " + c1.get(j).getLastName());
				}
				break;

			case 10:
				System.out.println("Enter the city to view total contacts");
				int city_count = cityList.get(sc.next()).size();
				System.out.println(city_count);
				break;

			case 11:
				System.out.println("Enter the state to view total contacts");
				int state_count = stateList.get(sc.next()).size();
				System.out.println(state_count);
				break;

			case 12:
				System.out.println("Enter the address book to view its sorted contacts");
				String AddressBookName = sc.next();
				if (hm.get(AddressBookName) == null) {
					System.out.println("No addressBook with this name, enter correct address book");
					return;
				}
				hm.get(AddressBookName).stream().sorted((n1, n2) -> n1.getFirstName().compareTo(n2.getFirstName()))
						.map(i -> i.toString()).forEach(y -> System.out.println(y));

			case 13:
				System.out.println("Enter the address book to view its sorted contacts by City");
				String AddressBookName1 = sc.next();
				if (hm.get(AddressBookName1) == null) {
					System.out.println("No addressBook with this name, enter correct address book");
					return;
				}
				hm.get(AddressBookName1).stream().sorted(Comparator.comparing(AddressBookContent::getCity))
						.map(i -> i.toString()).forEach(y -> System.out.println(y));
				break;

			case 14:
				System.out.println("Enter the address book to view its sorted contacts by State");
				String AddressBookName2 = sc.next();
				if (hm.get(AddressBookName2) == null) {
					System.out.println("No addressBook with this name, enter correct address book");
					return;
				}
				hm.get(AddressBookName2).stream().sorted(Comparator.comparing(AddressBookContent::getState))
						.map(i -> i.toString()).forEach(y -> System.out.println(y));
				break;

			case 15:
				System.out.println("Enter the address book to view its sorted contacts by Zip");
				String AddressBookName3 = sc.next();
				if (hm.get(AddressBookName3) == null) {
					System.out.println("No addressBook with this name, enter correct address book");
					return;
				}
				hm.get(AddressBookName3).stream().sorted(Comparator.comparing(AddressBookContent::getZip))
						.map(i -> i.toString()).forEach(y -> System.out.println(y));
				break;

			case 16:
				Path directoryLoc = Paths.get(path + "\\addressbook\\" + directory);
				if (Files.notExists(directoryLoc)) {
					Files.createDirectory(directoryLoc);
				}

				Path fileLoc = Paths.get(directoryLoc + "\\Name" + ".txt");
				if (Files.notExists(fileLoc))
					Files.createFile(fileLoc);

				StringBuffer bufferList = new StringBuffer();
				hm.values().forEach(details -> {
					String data = details.toString().concat("\n");
					bufferList.append(data);
				});
				try {
					Files.write(fileLoc, bufferList.toString().getBytes());
					System.out.println("Contact added to the file successfully");
				} catch (IOException e) {
					e.printStackTrace();
				}
				break;

			case 17:
				Path directoryLoc1 = Paths.get(path + "\\addressbook\\" + directory);
				Path fileLoc1 = Paths.get(directoryLoc1 + "\\Name" + ".txt");
				try {
					System.out.println("The contacts in the all the address books are");
					Files.lines(fileLoc1).map(line -> line.trim()).forEach(line -> System.out.println(line));
				} catch (Exception e) {
					e.getMessage();
				}
				break;

			case 18:
				System.out.println("Enter the name of the address book to add to csv file");
				ArrayList<AddressBookContent> listCsv = hm.get(sc.next());
				Path directoryLoc2 = Paths.get(path + "\\csvFile\\" + directory);
				if (Files.notExists(directoryLoc2)) {
					Files.createDirectory(directoryLoc2);
				}

				Path fileLoc2 = Paths.get(directoryLoc2 + "\\File" + ".csv");
				if (Files.notExists(fileLoc2))
					Files.createFile(fileLoc2);

				try (Writer writer = Files.newBufferedWriter(Paths.get(fileLoc2.toUri()));) {
					StatefulBeanToCsv<ArrayList<AddressBookContent>> beanToCsv = new StatefulBeanToCsvBuilder(writer)
							.withQuotechar(CSVWriter.NO_QUOTE_CHARACTER).build();

					beanToCsv.write(hm.get(listCsv));
				}
			case 19:
				Path pathLoc = Paths.get(path + "\\addressbook\\" + directory);
				Path fileLoc11 = Paths.get(pathLoc + "\\File" + ".csv");
				try (Reader reader = Files.newBufferedReader(Paths.get(fileLoc11.toUri()));) {

					CsvToBean<AddressBookContent> csvToBean = new CsvToBeanBuilder(reader)
							.withType(AddressBookContent.class).withIgnoreLeadingWhiteSpace(true).build();

					Iterator<AddressBookContent> AddressBookIterator = csvToBean.iterator();

					while (AddressBookIterator.hasNext()) {
						AddressBookContent contact = AddressBookIterator.next();
						System.out.println("Firstname : " + contact.firstName);
						System.out.println("Lastname : " + contact.lastName);
						System.out.println("Address : " + contact.address);
						System.out.println("City : " + contact.city);
						System.out.println("State : " + contact.state);
						System.out.println("Zip : " + contact.zip);
						System.out.println("Phone number : " + contact.phNo);
						System.out.println("Email : " + contact.email);
						System.out.println("**********************************");
					}
				}

			default:
			}
		}
		sc.close();
	}

}