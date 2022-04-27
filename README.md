# jaryjay-sharnon

A command-line app 💻 that helps game masters organize data in a role playing game 🎲 called Sharnon.

My brother and I invented this game years ago — it's essentially D&D, except simpler and less random.

## Features:

- Detailed character info stored in JSON 
  - Name🙂, class🧙‍♂️, equipment⚔️, known spells📜
- Name generator 
  - Randomly chooses a bunch of syllables and puts them together
- Dice roller 🎲
```
Input a command ('help' to show commands): roll 8d5

Rolls: [4, 3, 5, 3, 5, 1, 5, 3]
Total: 29
```
- Easily define new commands 📌
  - Format:
```java
addCommand(new GameCommand( [command name], [argument labels], [command description], [command length], [action] ));
```

Command definition example:
```java
 addCommand(new GameCommand("start", "<name>", "Start a session with a given player name", 2, args -> {
		Human player = ActorLoader.loadHuman(args[1]);
		if (player == null) {
			System.out.println("No player found with name " + args[1] + ".");
		} else {
			System.out.println("Starting session with " + player.getName() + ".");
			transitionState(new PlayState(app, player));
		}
}));
```

## Some cool screenshots
![Starting a session](/screenshots/screenshot1.png)
![Describing an actor](/screenshots/screenshot2.png)
![Names and equipment](/screenshots/screenshot3.png)
![Adding another actor](/screenshots/screenshot4.png)

---

What a neat little program, eh?
