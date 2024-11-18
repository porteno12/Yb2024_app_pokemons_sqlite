package com.example.app_pokemons_sqlite;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.app_pokemons_sqlite.databinding.ActivityMainBinding;
import java.util.Random;

/**
 * MainActivity manages a turn-based battle simulation between the player and CPU.
 * Each turn involves a random damage calculation, health bar update,
 * and strike effect based on Pokémon type.
 */
public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;  // View Binding object to access layout views
    private Random random;  // Random object for generating random damage

    private int playerHealth = 100;  // Initial health for the player
    private int cpuHealth = 100;     // Initial health for the CPU

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        // init the binding
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // init the Random object for generating damage values
        random = new Random();

        // set up the attack button to simulate a battle when clicking
        binding.btnBattle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                simulateBattle();  // start the battle
            }
        });

        // set initial health bar progress
        updateHealthBars();
    }

    /**
     * simulates a turn-based battle.
     * first the player attacks and if the CPU is not defeated,
     * the CPU attacks after a delay.
     */
    private void simulateBattle() {
        // process players attack
        boolean cpuDefeated = processPlayerAttack();

        // if CPU is defeated, no more actions are needed
        if (!cpuDefeated) {
            // delay the CPU attack by 2 seconds
            binding.btnBattle.postDelayed(new Runnable() {
                @Override
                public void run() {
                    processCpuAttack();
                }
            }, 2000);
        }
    }

    /**
     * processes the player attack on the CPU.
     * calculates random damage, updates CPU health, and shows animations.
     * @return true if the CPU is defeated, false otherwise.
     */
    private boolean processPlayerAttack() {
        int damage = calculateDamage();  // Calculate random damage
        cpuHealth -= damage;  // reduce damage from CPU health
        cpuHealth = Math.max(cpuHealth, 0);  // checking health doesn't drop below 0

        // show strike effect based on player Pokémon type
        showStrikeEffect("fire");
        // display the damage dealt to the CPU
        showDamageText(binding.cpuDamageText, damage);

        // update health bars after the attack
        updateHealthBars();

        // check if the CPU is defeated
        if (cpuHealth <= 0) {
            endBattle("CPU Defeated!");
            return true;
        }
        return false;
    }

    /**
     * processes the CPU's attack on the player.
     * calculates random damage, updates player health, and shows animations.
     */
    private void processCpuAttack() {
        int damage = calculateDamage();  // calculate random damage
        playerHealth -= damage;  // reduce damage from player health
        playerHealth = Math.max(playerHealth, 0);  // checks if health doesn't drop below 0

        // show strike effect based on CPU's Pokémon type
        showStrikeEffect("water");
        // display the damage dealt to the player
        showDamageText(binding.playerDamageText, damage);

        // update health bars after the attack
        updateHealthBars();

        // check if the player is defeated
        if (playerHealth <= 0) {
            endBattle("Player Defeated!");
        }
    }

    /**
     * ends the battle and displays the result message on the attack button.
     * disables the attack button to prevent further actions.
     *
     * @param message -  the message to display (e.g., "CPU Defeated!" or "Player Defeated!").
     */
    private void endBattle(String message) {
        binding.btnBattle.setText(message);  // show the result on the button
        binding.btnBattle.setEnabled(false);  // disable further actions
    }

    /**
     * updates the health bars to reflect the current health of the player and CPU.
     */
    private void updateHealthBars() {
        binding.playerHealthBar.setProgress(playerHealth);
        binding.cpuHealthBar.setProgress(cpuHealth);
    }

    /**
     * displays the appropriate strike effect in the middle of the screen based on the attack type.
     * @param type - the type of the attacking Pokémon (e.g., "fire", "water").
     */
    private void showStrikeEffect(String type) {
        int effectResource;

        // determine the strike effect based on the type
        switch (type) {
            case "fire":
                effectResource = R.drawable.flames_strike;  // Fire-type effect
                break;
            case "water":
                effectResource = R.drawable.water_attack;  // Water-type effect
                break;
            case "electric":
                effectResource = R.drawable.lightning_strike;  // Electric-type effect
                break;
            case "forest":
                effectResource = R.drawable.leavs_strike;  // Electric-type effect
                break;
            default:
                effectResource = R.drawable.strike_effect;  // Default effect
        }

        // Set the strike effect image
        binding.strikeEffect.setImageResource(effectResource);
        binding.strikeEffect.setVisibility(View.VISIBLE);

        // Hide the strike effect after a short delay
        binding.strikeEffect.postDelayed(new Runnable() {
            @Override
            public void run() {
                binding.strikeEffect.setVisibility(View.GONE);
            }
        }, 1000);  // Display for 1 second
    }

    /**
     * displays the damage dealt as a text above the target Pokémon.
     * the damage value appears temporarily and fades out after 1 second.
     * @param damageText The TextView to display the damage value.
     * @param damage The amount of damage to display.
     */
    private void showDamageText(final TextView damageText, int damage) {
        damageText.setText("-" + damage);  // Display damage as a negative value
        damageText.setVisibility(View.VISIBLE);

        // hide the damage text after 1 second
        damageText.postDelayed(new Runnable() {
            @Override
            public void run() {
                damageText.setVisibility(View.GONE);
            }
        }, 1000);
    }

    /**
     * calculates a random damage value between 10 and 100.
     * @return The damage value.
     */
    private int calculateDamage() {
        return (random.nextInt(10) + 1) * 10;  // Random damage between 10 and 100
    }
}
