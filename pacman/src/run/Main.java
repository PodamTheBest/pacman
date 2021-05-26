package run;

import java.io.File;

/**
 * The application entry point
 *
 * @author Leia
 */
public class Main {
    private static void usage() {
        System.out.println("Usage: java run.Main [resources_directory]");
        System.exit(-1);
    }

    /**
     * Launch the application
     *
     * @param args command line arguments (the resources directory name)
     */
    public static void main(String[] args) {
        File directory = null;

        if (args.length == 0) {
            directory = new File("out/production/Pacman");
        } else if (args.length != 1 || !new File(args[0]).isDirectory()) {
            usage();
        } else {
            directory = new File(args[0]);
        }

        data.Game dataGame = new data.GameImpl(directory);
        logic.Game logicGame = new logic.GameMock(dataGame);
        view.Game viewGame = new view.GameImpl(logicGame);
        viewGame.animate();
    }
}
