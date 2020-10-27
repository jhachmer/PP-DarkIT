package de.hshannover.inform.virusbuster;

/**
 * Enumeration of existing scenes
 * to manage filepaths
 * 
 * @author Jannes Hachmer
 *
 */

public enum Scenes {
	MAIN_MENU("/de/hshannover/inform/virusbuster/view/MainMenu.fxml"),
	HELP_MENU("/de/hshannover/inform/virusbuster/view/HelpMenu.fxml"),
	DIFFICULTY_MENU("/de/hshannover/inform/virusbuster/view/DifficultyMenu.fxml"),
	VICTORY("/de/hshannover/inform/virusbuster/view/Victory.fxml"),
	LOSS("/de/hshannover/inform/virusbuster/view/Loss.fxml"),
	EASTER_EGG("/de/hshannover/inform/virusbuster/view/EasterEgg.fxml"),
	GAME_EASY("/de/hshannover/inform/virusbuster/view/GameEasy.fxml"),
	GAME_MEDIUM("/de/hshannover/inform/virusbuster/view/GameMedium.fxml"),
	GAME_HARD("/de/hshannover/inform/virusbuster/view/GameHard.fxml");
    
	private String fxmlFile;
	
	Scenes(String fxmlFile) {
		this.fxmlFile = fxmlFile;
	}
	
	/**
	 * Returns path to the corresponding fxml file
	 * 
	 * @return path as a String
	 */
	public String getFxmlFile() {
		return fxmlFile;
	}

}
