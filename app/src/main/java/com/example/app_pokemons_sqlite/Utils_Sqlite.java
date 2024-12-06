package com.example.app_pokemons_sqlite;

import static android.content.Context.MODE_PRIVATE;
import static android.database.sqlite.SQLiteDatabase.openOrCreateDatabase;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class Utils_Sqlite {
    public static final String DATABASE_NAME = "db_pokemons";
    public static final String TBL_TRAINER_NAME = "tbl_trainers";
    public static final String TBL_POKEMON_NAME = "tbl_pokemon";
    public static final String TBL_POKEMONS_TRAINER_NAME = "tbl_pokemosTrainers";


    public static void createTables(SQLiteDatabase db) {
        String queryPokemons = "create table if not exists " + TBL_POKEMON_NAME + "(idpk integer primary key autoincrement," +
                "name text, power integer, type text)";
        db.execSQL(queryPokemons);

        String queryTrainers = "create table if not exists " + TBL_TRAINER_NAME + "(idtrn integer primary key autoincrement," +
                "name text, level integer)";
        db.execSQL(queryTrainers);

        String queryPokemonsAndTrainers = "create table if not exists " + TBL_POKEMONS_TRAINER_NAME + "(idpktr integer primary key autoincrement," +
                "idtr integer, idpk integer)";
        db.execSQL(queryPokemonsAndTrainers);
    }

    public static void insert2PokemonTbl(SQLiteDatabase db, Pokemon pk) {
        String insertQuery = "insert into " + TBL_POKEMON_NAME + " values(null, '" + pk.getName() + "', '" + pk.getPower() + "', '" + pk.getType() + "')";
        db.execSQL(insertQuery);
    }

    public static void insert2TrainerTbl(SQLiteDatabase db, Trainer tr) {
        String insertQuery = "insert into " + TBL_TRAINER_NAME + " values(null, '" + tr.getName() + "', '" + tr.getLevel() + "')";
        db.execSQL(insertQuery);
    }

    public static void deletePokemonTbl(SQLiteDatabase db, int id) {
        String deleteQuery = "delete from " + TBL_POKEMON_NAME + " where idpk=" + id;
        db.execSQL(deleteQuery);
    }

    public static void updatePokemon(SQLiteDatabase db, Pokemon p) {
        //String strSQL = "UPDATE myTable SET Column1 = someValue WHERE columnId = "+ someValue;
        String updateQuery = "update " + TBL_POKEMON_NAME + " set name = " + p.getName() + " where idpk = " + p.getPokemon_id();
        db.execSQL(updateQuery);
    }

    public static Pokemon searchStrongestPokemon(SQLiteDatabase db){
        Pokemon tmp = null;
        int max = -1;
        Cursor c = db.rawQuery("select * from "+TBL_POKEMON_NAME, null);
        while (c.moveToNext()){
            tmp = new Pokemon(c.getInt(0), c.getString(1), c.getInt(2), c.getString(3), 0);
            if(max<tmp.getPower()){
                max = tmp.getPower();
            }
        }
        return tmp;
    }

    public static ArrayList<Pokemon> retriveAllPokemons(SQLiteDatabase db) {
        ArrayList<Pokemon> pokemonsList = new ArrayList<>();
        Cursor rawData = db.rawQuery("select * from "+TBL_POKEMON_NAME, null);
        while(rawData.moveToNext()){
            Pokemon tmp = new Pokemon(rawData.getInt(0), rawData.getString(1),
                    rawData.getInt(2), rawData.getString(3), 0 );
            pokemonsList.add(tmp);
        }
        return pokemonsList;
    }


    //public static


}
