package com.jokeprovider;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class JokeProvider {

    private List<String> jokes = new ArrayList<>();

    public JokeProvider() {
        jokes.add("Q: What did the spider do on the computer?\n A: Made a website!");
        jokes.add("Q: What did the computer do at lunchtime?\n A: Had a byte!");
        jokes.add("Q: What does a baby computer call his father?\n A: Data!");
        jokes.add("Q: Why did the computer keep sneezing?\n A: It had a virus!");
        jokes.add("Q: What is a computer virus? A: A terminal illness!");
        jokes.add("Q: Why was the computer cold? A: It left it's Windows open!");
        jokes.add("Q: Why was there a bug in the computer? A: Because it was looking for a byte to eat?");
        jokes.add("Q: Why did the computer squeak? A: Because someone stepped on it's mouse!");
        jokes.add("Q: What do you get when you cross a computer and a life guard? A: A screensaver!");
        jokes.add("Q: Where do all the cool mice live? A: In their mousepads");
        jokes.add("Q: What do you get when you cross a computer with an elephant? A: Lots of memory!");
    }

    public String getJoke() {
        return jokes.get((new Random()).nextInt((jokes.size())));
    }
}
