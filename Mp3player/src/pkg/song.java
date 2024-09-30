package pkg;

import java.io.File;

public class song {
		File file;
		String title;
		String artist;
		String album;
		int length;
		int trackNumber;
		
		public song() {
			title = "title";
			artist = "artist";
			album = "album";
		}
		
		public song(File f, String title, String artist, String album, int trackNumber) {
			this.title = title;
			this.artist = artist;
			this.album = album;
			this.file = f;
			this.trackNumber = trackNumber;
		}
		
		public File getFile() {
			return file;
		}
		
		public String getTitle() {
			return title;
		}
		
		public String getArtist() {
			return artist;
		}
		
		public String getAlbum() {
			return album;
		}
		
		public int getLength() {
			return length;
		}
		
		public int getTrackNumber() {
			return trackNumber;
		}
		
		public String toName() {
			return "";
		}
		
		public String toString() {
			return artist+" | "+album+" | "+trackNumber+" | "+title+" | "+length;
			
		}
}
