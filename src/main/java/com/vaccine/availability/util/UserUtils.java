package com.vaccine.availability.util;

import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import org.passay.CharacterRule;
import org.passay.EnglishCharacterData;
import org.passay.PasswordGenerator;


public class UserUtils {
	
	
	public static String generatePassayPassword() {
	    PasswordGenerator gen = new PasswordGenerator();
	    EnglishCharacterData lowerCaseChars = EnglishCharacterData.LowerCase;
	    CharacterRule lowerCaseRule = new CharacterRule(lowerCaseChars);
	    lowerCaseRule.setNumberOfCharacters(2);

	    EnglishCharacterData upperCaseChars = EnglishCharacterData.UpperCase;
	    CharacterRule upperCaseRule = new CharacterRule(upperCaseChars);
	    upperCaseRule.setNumberOfCharacters(2);

	    EnglishCharacterData digitChars = EnglishCharacterData.Digit;
	    CharacterRule digitRule = new CharacterRule(digitChars);
	    digitRule.setNumberOfCharacters(2);

	   

	    String password = gen.generatePassword(10, lowerCaseRule, 
	      upperCaseRule, digitRule);
	    return password;
	}

	public static String generateRandomNumber() {
		Random r = new Random();
	    String randomNumber = String.format("%04d", (Object) Integer.valueOf(r.nextInt(1001)));
	    return randomNumber;
	}

}
