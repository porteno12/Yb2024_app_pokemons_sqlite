package com.example.app_pokemons_sqlite;

public class Pokemon {
    private int pokemon_id;
    private String name;
    private int power;
    private String type;
    private int image;

    public Pokemon( String name, int power, String type) {
        this.pokemon_id = -1;
        this.name = name;
        this.power = power;
        this.type = type;
        this.image = 0;
    }
    public Pokemon(int pokemon_id, String name, int power, String type, int image) {
        this.pokemon_id = pokemon_id;
        this.name = name;
        this.power = power;
        this.type = type;
        this.image = image;
    }

    public int getPokemon_id() {
        return pokemon_id;
    }

    public void setPokemon_id(int pokemon_id) {
        this.pokemon_id = pokemon_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
