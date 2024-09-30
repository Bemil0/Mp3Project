package pkg;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.DisplayMode;
import java.awt.GraphicsEnvironment;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.filechooser.FileSystemView;

import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.AutoDetectParser;
import org.apache.tika.sax.BodyContentHandler;

public class one {
	private JFrame frame;
	private JPanel panel, titlePanel, albumPanel, artistPanel;
	private JButton button;
	private ArrayList<song> songList = new ArrayList<song>();
	private File musicDirectory;
	private AutoDetectParser parser = new AutoDetectParser();
	private BodyContentHandler handler = new BodyContentHandler(-1);
	private Metadata metadata = new Metadata();

	
	private ArrayList<ArrayList<song>> albumList = new ArrayList<ArrayList<song>>();
	
	public one() {
		//JOptionPane.showMessageDialog(null, "Direc not found", "title", JOptionPane.INFORMATION_MESSAGE);
		DisplayMode screen = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDisplayMode();
		frame = new JFrame("Title");
		frame.setLayout(new BorderLayout());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(740, 860);
		frame.setLocation(screen.getWidth()/2-frame.getWidth()/2, screen.getHeight()/2-frame.getHeight()/2);
		/*button = new JButton("Select Music Directory");
		button.setBounds(150, 200, 200, 30);
		button.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				chooseDirectory();
				//testingStuff();
				for(int i=0; i<songList.size(); i++) {
					System.out.println(songList.get(i));
				}
			}
		});*/
		//panel = new JPanel(new GridLayout(0, 3));
		JMenuBar menuBar = new JMenuBar();
		JMenu file = new JMenu("File");
		JMenuItem load = new JMenuItem("Load");
		JMenuItem save = new JMenuItem("Save");
		load.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				chooseDirectory();
			}
		});
		save.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
			}
		});
		file.add(load);
		file.add(save);
		menuBar.add(file);
		frame.setJMenuBar(menuBar);		
		
		panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
		panel.setBackground(Color.red);
		titlePanel = new JPanel();
		titlePanel.setLayout(new BoxLayout(titlePanel, BoxLayout.Y_AXIS));
		titlePanel.setBackground(Color.green);
		albumPanel = new JPanel();
		albumPanel.setLayout(new BoxLayout(albumPanel, BoxLayout.Y_AXIS));
		albumPanel.setBackground(Color.green);
		artistPanel = new JPanel();
		artistPanel.setLayout(new BoxLayout(artistPanel, BoxLayout.Y_AXIS));
		artistPanel.setBackground(Color.green);
		panel.add(titlePanel);
		panel.add(albumPanel);
		panel.add(artistPanel);
		
		JScrollPane scroll = new JScrollPane(panel);
		frame.add(scroll);
		
		frame.setVisible(true);
	}
	
	public void chooseDirectory() {
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setCurrentDirectory(FileSystemView.getFileSystemView().getRoots()[0]);
		fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		if(fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
			musicDirectory = fileChooser.getSelectedFile();
			loadSongs(musicDirectory);
			
			
			
			frame.setVisible(true);
		}
	}
	
	public void loadSongs(File directory) { //fin. adding more will mess this up
		for(File f : directory.listFiles()) {
			if(f.isDirectory()) {
				loadSongs(f);
			}else if(f.getName().contains(".flac")) {
				metadata = null;
				metadata = new Metadata();
				try { parser.parse(new FileInputStream(f),  handler,  metadata); 
				} catch (Exception e) { e.printStackTrace();}
				songList.add(new song(f, metadata.get("dc:title"), metadata.get("xmpDM:artist"), metadata.get("xmpDM:album"), Integer.parseInt(metadata.get("xmpDM:trackNumber"))));
				titlePanel.add(new JLabel(songList.get(songList.size()-1).getTitle()));
				albumPanel.add(new JLabel(songList.get(songList.size()-1).getAlbum()));
				artistPanel.add(new JLabel(songList.get(songList.size()-1).getArtist()));
				
				if(checkAlbum(songList.get(songList.getSize()-1))){
					
				}
			}else if(f.getName().contains(".mp3")) {
				try { parser.parse(new FileInputStream(f),  handler,  metadata);
				} catch (Exception e) { e.printStackTrace();}
				songList.add(new song(f, metadata.get("dc:title"), metadata.get("xmpDM:artist"), metadata.get("xmpDM:album"), Integer.parseInt(metadata.get("xmpDM:trackNumber"))));
				titlePanel.add(new JLabel(songList.get(songList.size()-1).getTitle()));
				albumPanel.add(new JLabel(songList.get(songList.size()-1).getAlbum()));
				artistPanel.add(new JLabel(songList.get(songList.size()-1).getArtist()));
			}
		}
	}
	
	public boolean checkAlbum(Song s){
		if(s.getAlbum().equals()){
			
		}
		//check if the song's album matches any of the albums in albumList
		for(ArrayList<song> ar : albumList){
			if(ar.getName()
		}
	}
	public void testingStuff() {
		try {
			
			
			InputStream stream = one.class.getClassLoader().getResourceAsStream("Earthmover.flac");
			File audio = new File("C:\\Emils Stuff\\meals music\\Dan Barrett\\Giles Corey\\Hinterkaifeck\\03. Wounded Wolf.mp3");
			//File hint = new File("C:\\Emils Stuff\\meals music\\Dan Barrett\\Giles Corey\\Hinterkaifeck");
			FileInputStream fip = new FileInputStream(audio);
			//FileInputStream fs = new FileInputStream(hint);
			
			parser.parse(stream,  handler,  metadata); 
			int a = Integer.parseInt(metadata.get("xmpDM:trackNumber"));
			System.out.println(a);
			
			System.out.println(metadata.get("length"));
			
			
		//	File audio = new File("C:\\Users\\Emil\\eclipse-workspace\\Mp3player\\src\\resources\\01 Knife Throwing Contest.wav");
			//File audiomp3 = new File("C:\\Users\\Emil\\eclipse-workspace\\Mp3player\\src\\resources\\01 Knife Throwing Contest.mp3.properties");
			
		
			
			
			
			
			
			
			
			
			
			
			
			
			//AudioInputStream audioIn = AudioSystem.getAudioInputStream(audio);
			//Clip clip = AudioSystem.getClip();
			//clip.open(audioIn);
			//clip.start();
		}catch(Exception e){  System.out.println(e);}
	}

	public static void main(String[] args) {
		new one();
	}

}
