package org.kolokolov.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.kolokolov.database.DataBaseClass;
import org.kolokolov.model.Profile;

public class ProfileService {
	
	private Map<String, Profile> profiles = DataBaseClass.getProfiles();
	
	public ProfileService() {
		profiles.put("kolokolov", new Profile("kolokolov", "Alex", "Kolokolov"));
	}
	
	public List<Profile> getProfiles() {
		return new ArrayList<Profile>(profiles.values());
	}
	
	public Profile getProfile(String profileName) {
		return profiles.get(profileName);
	}
	
	public Profile addProfile(Profile profile) {
		profiles.put(profile.getProfileName(), profile);
		return profile;
	}
	
	public Profile updateProfile(Profile profile) {
		if ("".equals(profile.getProfileName())) return null;
		profiles.put(profile.getProfileName(), profile);
		return(profile);
	}
	
	public Profile removeProfile(String profileName) {
		return profiles.remove(profileName);
	}
}
