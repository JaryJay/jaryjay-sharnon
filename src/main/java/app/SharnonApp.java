package app;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.fusesource.jansi.AnsiConsole;

import app.state.AppState;
import app.state.MainScreenState;

public class SharnonApp {

	private AppState state = new MainScreenState(this);

	public static void main(String[] args) {
		AnsiConsole.systemInstall();
//		System.out.println(ansi().eraseScreen().fg(RED).a("Hello").fg(GREEN).a(" World").reset());
		SharnonApp app = new SharnonApp();
		app.run();
	}

	private void run() {
		state.init();
		try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
			while (!state.isQuit()) {
				System.out.print("Input a command ('help' to show commands): ");
				state.handle(br.readLine().split(" "));
				System.out.println();
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
