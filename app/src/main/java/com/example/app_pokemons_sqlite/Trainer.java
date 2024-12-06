package com.example.app_pokemons_sqlite;

public class Trainer {
    private int trainer_id;
    private String name;
    private int level;

    public Trainer(int trainer_id, String name, int level) {
        this.trainer_id = trainer_id;
        this.name = name;
        this.level = level;
    }
    public Trainer(String name, int level) {
        this.trainer_id = 0;
        this.name = name;
        this.level = level;
    }
    public int getTrainer_id() {
        return trainer_id;
    }

    public void setTrainer_id(int trainer_id) {
        this.trainer_id = trainer_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
}
