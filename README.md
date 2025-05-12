# WordReducer

**WordReducer** is a Java program that finds all 9-letter words which can be reduced one letter at a time—without rearranging the letters—such that each intermediate word is valid, all the way down to a single-letter word (`"a"` or `"i"`).

This is a recursive word puzzle that demonstrates efficient algorithm design using memoization and set-based lookups.

## 🚀 Features

- Loads a dictionary of valid Scrabble words
- Identifies fully reducible 9-letter words
- Optimized with memoization and constant-time lookups
- Completes in under 2 seconds with a 500k-word dictionary


## 🔧 Requirements

- Java 17 or later
- Internet access (downloads a word list from GitHub)

## 🛠 How to Run

1. Clone the repository or copy the `WordReducer.java` file.
2. Compile and run the program:

```bash
javac WordReducer.java
java WordReducer
```

## 📌 Example Output
```
NUMBER OF RESULTS: 775
LOADING PHASE: 1493
EXECUTION: 242
[ABOUNDING, BOUNDING, BONDING, BODING, BOING, BING, ING, IN, I]
[ABRIDGERS, ABRIDGES, BRIDGES, RIDGES, RIDES, IDES, IDS, IS, I]
[ABROOKING, BROOKING, BOOKING, BOKING, BOING, BING, ING, IN, I]
[ACANTHOUS, ACANTHUS, CANTHUS, CANTUS, CANTS, ANTS, ATS, AS, A]
...
```


