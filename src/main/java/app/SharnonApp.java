package app;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import app.state.AppState;
import app.state.MainScreenState;

public class SharnonApp {

	private AppState state = new MainScreenState(this);

	public static void main(String[] args) {
		SharnonApp app = new SharnonApp();
		app.run();
	}

	private void run() {
		state.init();
		System.out.print("Input a command ('help' to show commands): ");
		try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
			while (!state.isQuit()) {
				state.handle(br.readLine().split(" "));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public AppState getState() {
		return state;
	}

	public void setState(AppState state) {
		this.state = state;
	}

}
