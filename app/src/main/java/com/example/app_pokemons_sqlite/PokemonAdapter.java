package com.example.app_pokemons_sqlite;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.app_pokemons_sqlite.databinding.RowPokemonInListviewBinding;

import java.util.ArrayList;

public class PokemonAdapter extends BaseAdapter{
    private Context context;
    private ArrayList<Pokemon> pokemons;
    private RowPokemonInListviewBinding binding;
    public PokemonAdapter(Context context, ArrayList<Pokemon> pokemons) {
        this.context = context;
        this.pokemons = pokemons;
    }

    @Override
    public int getCount() {
        return pokemons.size();
    }

    @Override
    public Object getItem(int position) {
        return pokemons.get(position);
    }

    @Override
    public long getItemId(int position) {
        return pokemons.get(position).getPokemon_id();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        binding = binding.inflate(LayoutInflater.from(context), viewGroup,false);
        view = binding.getRoot();

        Pokemon tmp  = pokemons.get(i);
        binding.pkId.setText(""+tmp.getPokemon_id());
        binding.pkName.setText(tmp.getName());
        binding.pkType.setText(tmp.getType());
        binding.pkPower.setText(""+tmp.getPower());
        return view;
    }
}
