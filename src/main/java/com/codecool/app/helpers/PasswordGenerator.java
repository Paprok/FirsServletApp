package com.codecool.app.helpers;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class PasswordGenerator {
    private int chars = 8;
    private boolean upperCase = false;
    private boolean numbers = false;
    private boolean special = false;
    private List<Character> characterList;

    public void setAvailableSigns(Map<String, String> parsedRequest) {
        if(parsedRequest.get("chars") != null){
            this.chars = Integer.parseInt(parsedRequest.get("chars").trim());
        }
        if(parsedRequest.get("numbers") != null){
            this.numbers = true;
        } else {
            this.numbers =false;
        }
        if(parsedRequest.get("special") != null){
            this.special = true;
        } else {
            this.special =false;
        }
        if(parsedRequest.get("upper") != null){
            this.upperCase = true;
        } else {
            this.upperCase =false;
        }
    }

    public String getPassword(){
        createCharacterList();
        Random random = new Random();
        char[] password = new char[chars];
        for(int i =0; i< chars; i++){
            int index = random.nextInt(this.characterList.size());
            password[i] = this.characterList.get(index);
        }
        return String.valueOf(password);
    }

    private void createCharacterList(){
        this.characterList = new ArrayList<>();
        addCharacter('a',26);
        if(upperCase){
            addCharacter('A',26);
        }
        if(numbers){
            addCharacter('0',10);
        }
        if(special){
            addCharacter('#',4);
            characterList.add('@');
        }
    }

    private void addCharacter(char first, int howMany){
        for(int i = 0; i < howMany; i++){
            characterList.add((char)(first + i));
        }
    }
}
