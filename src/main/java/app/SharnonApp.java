package app;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import input.SharnonInputHandler;

public class SharnonApp {

	public static void main(String[] args) {
		SharnonInputHandler inputHandler = new SharnonInputHandler();
		try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
			while (!inputHandler.isQuit()) {
				inputHandler.handle(br.readLine());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
