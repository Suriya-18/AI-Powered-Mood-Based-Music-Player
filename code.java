import javax.sound.sampled.*;
import javax.swing.*;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class SeptherMusicPlayer {

    private static final Map<String, String> moodToSong = new HashMap<>();

    static {
        moodToSong.put("happy", "music/happy.wav");
        moodToSong.put("sad", "music/sad.wav");
        moodToSong.put("relaxed", "music/relaxed.wav");
        moodToSong.put("excited", "music/excited.wav");
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Septher - Mood Music Player");
        JTextField inputField = new JTextField();
        JButton playButton = new JButton("Play Music");

        playButton.addActionListener(e -> {
            String mood = detectMood(inputField.getText().toLowerCase());
            if (mood != null) {
                playMusic(moodToSong.get(mood));
            } else {
                JOptionPane.showMessageDialog(frame, "Mood not recognized.");
            }
        });

        frame.setLayout(new java.awt.FlowLayout());
        frame.add(new JLabel("Enter how you feel:"));
        frame.add(inputField);
        frame.add(playButton);
        frame.setSize(300, 150);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    private static String detectMood(String text) {
        if (text.contains("happy") || text.contains("joy")) return "happy";
        if (text.contains("sad") || text.contains("down")) return "sad";
        if (text.contains("calm") || text.contains("chill")) return "relaxed";
        if (text.contains("excited") || text.contains("energy")) return "excited";
        return null;
    }

    private static void playMusic(String filePath) {
        try {
            File audioFile = new File(filePath);
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);
            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);
            clip.start();
        } catch (Exception e) {
            System.out.println("Error playing music: " + e.getMessage());
        }
    }
}
