package com.eprworld.nrcl.protobuf;

import com.eprworld.nrcl.protobuf.AddressBookProtos.AddressBook;
import com.eprworld.nrcl.protobuf.AddressBookProtos.Person;

public class ListPeople {
	// Iterates though all people in the AddressBook and prints info about them.
	public static void Print(AddressBook addressBook) {

		for (Person person : addressBook.getPeopleList()) {
			System.out.println("Person ID: " + person.getId());
			System.out.println("  Name: " + person.getName());
			// hasEmail does not exist because in .proto is not marked as 'Optional'
//			if (person.hasEmail()) {
//				System.out.println("  E-mail address: " + person.getEmail());
//			}
			for (Person.PhoneNumber phoneNumber : person.getPhonesList()) {
				switch (phoneNumber.getType()) {
				case MOBILE:
					System.out.print("  Mobile phone #: ");
					break;
				case HOME:
					System.out.print("  Home phone #: ");
					break;
				case WORK:
					System.out.print("  Work phone #: ");
					break;
				default:
					System.out.print("  Unknown phone type ");
					break;
				}
				System.out.println(phoneNumber.getNumber());
			}
		}
	}
}
