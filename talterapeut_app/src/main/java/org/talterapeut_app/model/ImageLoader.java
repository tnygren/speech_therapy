package org.talterapeut_app.model;

import org.talterapeut_app.Word;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;

import com.vaadin.server.FileResource;
import com.vaadin.ui.Image;


public class ImageLoader {
	/**
	 * Returns an arraylist of photos representing all .jpg files under a given folder
	 * (including subfolders as well).
	 * 
	 * @param path
	 *            the string representing the path to the folder. If path does
	 *            not correspond to an actual folder in the filesystem, returns
	 *            an empty set.
	 */
	
	public static ArrayList<Image> loadImages(String path){
		
		File folder = new File(path);
		
		ArrayList<Image> imglist = new ArrayList<Image>();	
		addImagesToList(folder, imglist);
		return imglist;
		
	}
	
	// Find all JPG files in folder and add them to a list.
	// Requires folder to be an actual folder on disk and set != null.
	protected static void addImagesToList(File folder, ArrayList<Image> list) {
		for (File f : findJPGFiles(folder)) {
			System.out.println("Loading... " + f.getAbsolutePath());
			//Resource res = new ThemeResource(f.getAbsolutePath());
			FileResource res = new FileResource(f);
			list.add(new Image("", res));
		}
	}
	
	/**
	 * Reads JPGs and audio files and creates Words from them.
	 * Uses filename as key (or id) for Word.
	 * TODO implementation
	 * 
	 * @param folder to look for files
	 * @return list of Words
	 */
	public ArrayList<Word> getWords(File folder) {
		ArrayList<Word> words = new ArrayList<>();	
		for (File f : findJPGFiles(folder)) {
			Word word = new Word(f.getAbsolutePath(), null, null, null); // TODO implementation
			words.add(word);
		}
		return words;
	}
	
	// Return all the JPG files that are immediate children of folder.
	// Requires folder to be an actual folder on disk.
	public static File[] findJPGFiles(File folder) {
		return folder.listFiles(new FilenameFilter() {
			public boolean accept(File dir, String name) {
				int i = name.lastIndexOf('.');
				return "jpg".equals(name.substring(i + 1).toLowerCase());
			}
		});
	}

	/*
	// Return all the immediate subfolders of folder.
	// Requires folder to be an actual folder on disk.
	private static File[] findSubFolders(File folder) {
		return folder.listFiles(new FileFilter() {
			public boolean accept(File pathname) {
				return (pathname.isDirectory());
			}
		});
	}
	*/
}
