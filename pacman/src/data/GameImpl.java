package data;

import java.awt.*;
import java.io.*;
import java.text.ParseException;
import java.util.List;
import java.util.*;

/**
 * Implementation of the {@link Game} interface that loads game levels from
 * files named "levelN" where N is the level number (the first level number=1)
 * in a directory which path is provided at initialization.
 *
 * @author Leia
 */
public class GameImpl implements Game {

    //--------------------------------------------------------------
    // Attributes
    //--------------------------------------------------------------
    /**
     * the directory that contains the levels files
     */
    private final File directory;

    /**
     * the number of the last loaded level
     */
    private int currentLevel;

    //--------------------------------------------------------------
    // Constructor
    //--------------------------------------------------------------

    /**
     * Constructor
     *
     * @param directory the directory that contains the levels files
     * @pre directory.isDirectory()
     */
    public GameImpl(File directory) {
        assert directory.isDirectory() : "precondition violated";

        this.directory = directory;
    }

    //--------------------------------------------------------------
    // Game overriden methods
    //--------------------------------------------------------------
    @Override
    public Level nextLevel() {
        assert hasNextLevel() : "precondition violated";

        Level level = null;
        try {
            level = loadLevel(new File(directory, "level" + (++currentLevel)));

        } catch (IOException | ParseException e) {
            System.err.println("Error while loading the level " + currentLevel + ": " + e.getMessage());
            if (hasNextLevel()) {
                level = new LevelImpl(new HashSet<>(), new ArrayList<>(), new HashMap<>(), new Properties());
            }
        }

        assert level != null : "postcondition violated";
        return level;
    }

    @Override
    public boolean hasNextLevel() {
        return new File(directory, "level" + (currentLevel + 1)).isFile();
    }

    //--------------------------------------------------------------
    // toString
    //--------------------------------------------------------------

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        // iterate over levels and display each level
        while (hasNextLevel()) {
            builder.append("\n==========================================");
            Level level = nextLevel();
            // display the elements
            for (Element element : level.getElements()) {
                if (element instanceof Pacman) {
                    builder.append("\nPacman ").append(element.getLocation().toString());
                } else if (element instanceof Ghost) {
                    builder.append("\nGhost ").append(element.getLocation());
                }
            }
            // display the properties
            for (Enumeration<?> it = level.getProperties().propertyNames(); it.hasMoreElements(); ) {
                Object name = it.nextElement();
                builder.append(name).append("=").append(level.getProperties().get(name));
            }
            // display the board
            for (int i = 0; i < level.getSize(); i++) {
                builder.append("\n");
                for (int j = 0; j < level.getSize(); j++) {
                    if (level.isWall(j, i)) {
                        builder.append("w");
                    } else {
                        builder.append(level.getFruit(j, i).getKey());
                    }
                }
            }
        }
        return builder.toString();
    }

    //--------------------------------------------------------------
    // Private methods
    //--------------------------------------------------------------

    /**
     * Load a level from the given file. The input file contains 4 parts. Each
     * part is begins with a description line (starting with #).
     *
     * @param file the file that contains the level description
     * @return the level loaded from the file
     * @throws FileNotFoundException if the file cannot be found
     * @throws IOException           if an error occurs while reading the file
     * @throws ParseException        if a line format is not valid
     * @post ret != null
     */
    private Level loadLevel(File file) throws FileNotFoundException, IOException, ParseException {
        Map<Character, Fruit> fruits = new HashMap<>(); // the fruits map (key,fruit)
        Collection<Element> elements = new HashSet<>(); // the ghosts and pacman
        List<String> board = new ArrayList<>(); // the lines that represent the board rows
        Properties properties = new Properties(); // the properties
        int part = 0; // the current part number

        // read and parse the lines from the file
        int lineCpt = 0; // a counter for the lines numbers
        int firstBoardLine = 0; // the number of the first line of the board
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line = reader.readLine(); // read the first line
            while (line != null) {
                lineCpt++;
                if (!line.trim().isEmpty()) {
                    if (line.startsWith("#")) {
                        // the description of a new part (starting with #)
                        part++;
                        if (part == 1) {
                            firstBoardLine = lineCpt;
                        }
                    } else {
                        // a non empty line that belongs to one of the parts
                        switch (part) {
                            case 1: // game board (lines)
                                board.add(line.trim());
                                break;
                            case 2: // ghosts and pacman
                                Element element = parseElement(line.trim(), lineCpt);
                                elements.add(element);
                                break;
                            case 3: // fruits
                                Fruit fruit = parseFruit(line.trim(), lineCpt);
                                fruits.put(fruit.getKey(), fruit);
                                break;
                            case 4: // properties
                                String[] tokens = line.trim().split("=");
                                properties.put(tokens[0].trim(), tokens[1].trim());
                                break;
                        }
                    }
                }
                line = reader.readLine(); // read the next line
            }
        }

        // check the length of the bord lines
        lineCpt = firstBoardLine;
        for (String line : board) {
            lineCpt++;
            if (line.length() != board.size()) {
                throw new ParseException("The line \"" + line + "\" contains " + line.length() + " characters. Should be " + board.size(), lineCpt);
            }
        }

        // make the new Level instance
        return new LevelImpl(elements, board, fruits, properties);
    }

    /**
     * Parse a line representing a fruit
     *
     * @param line   the line in the form key,name,value
     * @param lineNb the line number
     * @return the fruit
     * @throws ParseException if the line format is not valid
     */
    private Fruit parseFruit(String line, int lineNb) throws ParseException {
        String[] tokens = line.split(",");
        if (tokens.length != 3) {
            throw new ParseException("The line \"" + line + "\" contains " + tokens.length + " tokens. Should be 3", lineNb);
        }

        char key = tokens[0].charAt(0); // the key
        String name = tokens[1]; // the name
        int value = Integer.parseInt(tokens[2]); // the value

        if (name.isEmpty()) {
            throw new ParseException("The line \"" + line + "\" contains an empty name", lineNb);
        }
        if (value <= 0) {
            throw new ParseException("The line \"" + line + "\" contains an invalid value: " + value + ". Should be greater than 0", lineNb);
        }

        // make the new Fruit instance
        return new FruitImpl(key, name, value);
    }

    /**
     * Parse a line representing an element
     *
     * @param line   the line in the form name,x,y
     * @param lineNb the line number
     * @return the element
     * @throws ParseException if the line format is not valid
     */
    private Element parseElement(String line, int lineNb) throws ParseException {
        String[] tokens = line.split(",");
        if (tokens.length != 3) {
            throw new ParseException("The line \"" + line + "\" contains " + tokens.length + " tokens. Should be 3", lineNb);
        }

        String name = tokens[0];
        Point point = new Point(Integer.parseInt(tokens[1]), Integer.parseInt(tokens[2]));

        if (name.isEmpty()) {
            throw new ParseException("The line \"" + line + "\" contains an empty name", lineNb);
        }
        if (point.x < 0 || point.y < 0) {
            throw new ParseException("The line \"" + line + "\" contains an invalid value: " + point + ". x and y should be greater or equal to 0", lineNb);
        }

        if ("pacman".equals(name)) {
            // make a new Pacman instance
            return new PacmanImpl(point);
        }
        // make a new Ghost instance
        return new GhostImpl(point, name);
    }
}
