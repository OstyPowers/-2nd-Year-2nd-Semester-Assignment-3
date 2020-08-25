package com.example.sokbanv1;

import java.util.ArrayList;
import java.util.List;


public class Game {
	private Level currentLevel;
	private int currentLevelCount = 0;
	private String currentLevelName = "no levels";
	private List<String> levNames = new ArrayList<String>();
	private ArrayList<Level> allMyLevels = new ArrayList<Level>();

	public int getLevelCount() {
		return this.currentLevelCount;
	}
	public List getLevels(){
		return this.allMyLevels;
	}

	public String getCurrentLevelName() {
		return this.currentLevelName;
	}
	public void setCurrentLevel(int level){
		this.currentLevel = allMyLevels.get(level-1);
		this.currentLevelName = currentLevel.getName();
	}
	
	public Level getCurrentLevel() {
		return this.currentLevel;
	}

	public List<String> getLevelNames() {
		return this.levNames;
	}
	
	public String toString() {
		return this.currentLevelName.toString();
	}

	public void addLevel(String name, int height, int width, String leveltext) {
		this.currentLevelName = name;
		this.currentLevelCount++;
		Level level = new Level(name, height, width, leveltext);
		this.currentLevel = level;
		this.allMyLevels.add(level);
		this.levNames.add(name);
	}

	public void move(Direction direction) {
		this.currentLevel.moveCount++;
		this.currentLevel.move(direction);
	}
		
}
