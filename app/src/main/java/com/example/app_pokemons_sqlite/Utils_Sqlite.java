package com.example.app_pokemons_sqlite;

import static android.content.Context.MODE_PRIVATE;
import static android.database.sqlite.SQLiteDatabase.openOrCreateDatabase;

import android.database.sqlite.SQLiteDatabase;

public class Utils_Sqlite {
    public static final String DATABASE_NAME = "db_pokemons";

  /*  public static void openDb(){
        SQLiteDatabase db = openOrCreateDatabase(Utils_Sqlite.DATABASE_NAME,MODE_PRIVATE,
                null);

    }*/

    public static  void createTables(SQLiteDatabase db){
        String queryPokemons = "create table if not exists tbl_pokemon(idpk integer primary key autoincrement," +
                "name text, power integer, type text)";
        db.execSQL(queryPokemons);
        String queryTrainers = "create table if not exists tbl_trainers(idtrn integer primary key autoincrement," +
                "name text, level integer)";
        db.execSQL(queryTrainers);
        String queryPokemonsAndTrainers = "create table if not exists tbl_pokemosTrainers(idpktr integer primary key autoincrement," +
                "idtr integer, idpk integer)";
        db.execSQL(queryPokemonsAndTrainers);
    }

    public static void insert2PokemonTbl(SQLiteDatabase db, Pokemon pk){
        String query = "insert into tbl_pokemon values(null, '"+pk.getName()+"', '"+pk.getPower()+"', '"+pk.getType()+"')";
        db.execSQL(query);
    }
    public static void deletePokemonTbl(SQLiteDatabase db, int id){
        String query = "delete from tbl_pokemon where idpk="+id;
        db.execSQL(query);
    }
}
