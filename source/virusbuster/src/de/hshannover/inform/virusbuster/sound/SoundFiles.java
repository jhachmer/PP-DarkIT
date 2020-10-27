package de.hshannover.inform.virusbuster.sound;

/**
 * Manages Soundfiles and their paths
 * @author Jannes Hachmer
 *
 */
public enum SoundFiles{
    EASTER_EGG("/de/hshannover/inform/virusbuster/sound/neverlucky.wav"),
    VICTORY("/de/hshannover/inform/virusbuster/sound/victory.wav"),
    LOSS("/de/hshannover/inform/virusbuster/sound/error.wav"),
    START("/de/hshannover/inform/virusbuster/sound/start.wav");
    
    private String soundFilePath;
    
    SoundFiles(String soundFilePath){
        this.soundFilePath = soundFilePath;
    }
    
    /**
     * Returns path to the corresponding sound file
     * @return Path as a String
     */
    public String getSoundFilePath() {
        return soundFilePath;
    }
    
}