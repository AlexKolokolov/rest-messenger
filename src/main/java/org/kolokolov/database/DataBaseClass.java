package org.kolokolov.database;

import java.util.HashMap;
import java.util.Map;

import org.kolokolov.model.Message;
import org.kolokolov.model.Profile;

public class DataBaseClass {
	
	private static Map<String, Profile> profiles = new HashMap<>(); 
	private static Map<Long, Message> messages = new HashMap<>();
	
	public static Map<String, Profile> getProfiles() {
		return profiles;
	}
	
	public static Map<Long, Message> getMessages() {
		return messages;
	} 
}
