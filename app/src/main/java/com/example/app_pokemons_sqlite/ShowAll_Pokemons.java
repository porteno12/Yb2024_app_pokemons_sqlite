package com.example.app_pokemons_sqlite;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.app_pokemons_sqlite.databinding.ActivityMainBinding;
import com.example.app_pokemons_sqlite.databinding.ActivityShowAllPokemonsBinding;

import java.util.ArrayList;

public class ShowAll_Pokemons extends AppCompatActivity {
    private ActivityShowAllPokemonsBinding binding;
    SQLiteDatabase db;
    ArrayList<Pokemon> pokemons;
    PokemonAdapter pokeAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // init the binding
        binding = ActivityShowAllPokemonsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        db = openOrCreateDatabase(Utils_Sqlite.DATABASE_NAME, MODE_PRIVATE, null);
        pokemons = Utils_Sqlite.retriveAllPokemons(db);

        pokeAdapter = new PokemonAdapter(ShowAll_Pokemons.this, pokemons);
        binding.lvPokemons.setAdapter(pokeAdapter);

    }
}