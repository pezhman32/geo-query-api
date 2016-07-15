package com.goeuro.test;

import com.goeuro.test.IO.IO;
import com.goeuro.test.IO.IOImpl;

public class App {

    public static void main(String[] args) {
	    IO io = new IOImpl();
	    io.out("user input : " + io.read("what's your age?"));
    }
}
