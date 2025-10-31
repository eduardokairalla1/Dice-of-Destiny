# 🎲 Dice of Destiny

A text-based RPG adventure game built in Java. 
Battle monsters, explore dungeons, collect treasures, and survive deadly traps in this dice-driven adventure!

## 📖 About

Dice of Destiny is a turn-based RPG where your fate is determined by the roll of dice. Choose your hero class, navigate through a dangerous dungeon, fight enemies, use items strategically, and face the Dark Lord in an epic final battle.

## ✨ Features

### 🎮 Gameplay
- **3 Playable Classes**: Warrior, Archer, or Wizard - each with unique stats and special attacks
- **Interactive Combat System**: Turn-based battles with dice rolls
  - Normal attacks
  - Special attacks with cooldown system
  - Item usage during battle
  - Escape mechanism
- **Dungeon Exploration**: Navigate through 6 interconnected rooms
- **Multiple Event Types**:
  - ⚔️ Enemy battles (Goblin Scout, Ogre Guardian, Dark Lord)
  - 💣 Deadly traps with dodge mechanics
  - 💎 Treasure chests with valuable items
  - 🤔 Decision-based events with consequences
  - 😴 Rest areas to recover health
  - ✨ Mystery events with random effects

### 🎒 Inventory System
- Collect and manage multiple items
- Use items during combat to heal, boost attack, or increase defense
- Loot defeated enemies for their equipment
- Automatic item stacking

### 🏆 Victory Conditions
- Defeat the Dark Lord in the Throne Room
- Collect legendary equipment
- Survive the dungeon's many dangers

## 📋 Requirements

- **Java**: Version 21 or higher

## 🚀 Installation

1. **Clone the repository:**
   ```bash
   git clone git@github.com:eduardokairalla1/Dice-of-Destiny.git
   cd Dice-of-Destiny
   ```

2. **Verify Java installation:**
   ```bash
   java -version
   ```
   Make sure you have Java 21 or higher installed.

## ▶️ How to Run

### 🐧 Linux / 🍎 macOS

#### Option 1: Using the run script (recommended)
```bash
./scripts/run
```

#### Option 2: Manual compilation and execution
```bash
# Compile
find src/main/java -name "*.java" -type f | xargs javac -d build

# Run
java -cp build src.main.java.Main
```

---

### 🪟 Windows

#### Option 1: Using Command Prompt
```cmd
REM Compile
dir /s /b src\main\java\*.java > sources.txt
javac -d build @sources.txt
del sources.txt

REM Run
java -cp build src.main.java.Main
```

#### Option 2: Using PowerShell
```powershell
# Compile
Get-ChildItem -Path src\main\java -Filter *.java -Recurse | ForEach-Object { $_.FullName } | Out-File -FilePath sources.txt
javac -d build "@sources.txt"
Remove-Item sources.txt

# Run
java -cp build src.main.java.Main
```

#### Option 3: Using Git Bash (if installed)
```bash
./scripts/run
```

## 🎮 How to Play

### Character Creation
1. Enter your hero's name
2. Choose your class:
   - **⚔️ Warrior** (HP: 100, ATK: 15, DEF: 10) - Balanced fighter with Power Strike
   - **🏹 Archer** (HP: 90, ATK: 14, DEF: 8) - Fast and precise with Precision Arrow
   - **🔮 Wizard** (HP: 80, ATK: 18, DEF: 6) - High damage glass cannon with Fireball

### Game Menu
- **🔍 Explore current room** - Trigger random events
- **🚶 Move to another room** - Navigate the dungeon
- **🎒 Check inventory** - View your items
- **📊 View character status** - See your stats
- **🚪 Exit game** - Quit

### Combat Actions
- **⚔️ Attack** - Standard attack with dice roll
- **✨ Special Attack** - Powerful attack (3-turn cooldown)
- **💊 Use Item** - Consume items for healing or buffs
- **🏃 Try to Escape** - 50% chance to flee (dice roll ≥ 4)

### Tips
- 🔍 Explore thoroughly to find items and treasures
- 💊 Use healing potions wisely - they're limited!
- 🛡️ Defense boost items can help survive tough battles
- ⚔️ Save special attacks for strong enemies like the Ogre and Dark Lord
- 🌿 Visit the Underground Garden to rest and heal
- 🚪 Choose doors and paths carefully - some lead to traps!

## 📁 Project Structure

```
java_rpg/
├── src/main/java/
│   ├── Main.java                    # Entry point
│   ├── characters/                  # Character system
│   │   ├── Character.java          # Abstract base class
│   │   ├── heroes/                 # Player classes
│   │   │   ├── Warrior.java
│   │   │   ├── Archer.java
│   │   │   └── Wizard.java
│   │   └── enemies/                # Enemy classes
│   │       └── Enemy.java
│   ├── game/                       # Game logic
│   │   ├── Game.java              # Main game loop
│   │   ├── Room.java              # Dungeon rooms
│   │   ├── Event.java             # Game events
│   │   └── EventType.java         # Event types enum
│   ├── inventory/                  # Inventory system
│   │   └── Inventory.java
│   └── items/                      # Item system
│       ├── Item.java
│       └── EffectType.java        # Item effects enum
├── scripts/
│   └── run                         # Build and run script (Linux/Mac)
├── build/                          # Compiled classes (generated)
├── STORY.md                        # Game lore and story
└── README.md                       # This file
```

## 👥 Authors

- Eduardo Kairalla
- Tomás Cubeiro

## 📜 License

This project was developed for educational purposes as part of a university Object-Oriented Programming course.

## 🤝 Contributing

We welcome contributions to the project! If you'd like to contribute, please follow these steps:

1. Fork the repository
2. Create a new branch (e.g., `feature/my-feature`)
3. Make your changes
4. Submit a pull request

## 🎲 May the Dice Be Ever in Your Favor!

---

**Dice of Destiny** - Where your fate is determined by the roll of dice.
