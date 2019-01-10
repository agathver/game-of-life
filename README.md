# Game of Life

Simulation of [Conway's Game of Life](https://en.wikipedia.org/wiki/Conway's_Game_of_Life)

## Usage

java -jar game-of-life.jar org.example.gameoflife.App EPOCHS [initial state file]

The initial state file describes the initial state of the cells. If not supplied, data is read
from STDIN

### File format

The format of the state file is simple.

Ordered pair of (x,y) co-ordinates are written in a single line. Each of them represent a live cell.

```
1,1 0,0 0,1 1,1
```

The above is a square.

## Development

You need Gradle

1. Generate a distribution with `gradle build`
2. Run tests with `gradle test`
3. Run the application with `gradle run --args EPOCHS`
