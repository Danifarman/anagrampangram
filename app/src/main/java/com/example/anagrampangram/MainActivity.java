package com.example.anagrampangram;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import java.util.Arrays;
import java.util.HashSet;

public class MainActivity extends AppCompatActivity {

    EditText etAnagram1, etAnagram2, etPangram;
    Button btnAnagram, btnPangram;
    TextView tvAnagramResult, tvPangramResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize UI components
        etAnagram1 = findViewById(R.id.etAnagram1);
        etAnagram2 = findViewById(R.id.etAnagram2);
        etPangram = findViewById(R.id.etPangram);

        btnAnagram = findViewById(R.id.btnAnagram);
        btnPangram = findViewById(R.id.btnPangram);

        tvAnagramResult = findViewById(R.id.tvAnagramResult);
        tvPangramResult = findViewById(R.id.tvPangramResult);

        // Anagram Button Click
        btnAnagram.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str1 = etAnagram1.getText().toString().trim();
                String str2 = etAnagram2.getText().toString().trim();

                // Check empty inputs
                if (str1.isEmpty()) {
                    etAnagram1.setError("Field cannot be empty");
                    Toast.makeText(MainActivity.this, "Enter first word", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (str2.isEmpty()) {
                    etAnagram2.setError("Field cannot be empty");
                    Toast.makeText(MainActivity.this, "Enter second word", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Check anagram
                if (isAnagram(str1, str2)) {
                    tvAnagramResult.setText("The strings are Anagrams");
                } else {
                    tvAnagramResult.setText("The strings are NOT Anagrams");
                }
            }
        });

        // Pangram Button Click
        btnPangram.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sentence = etPangram.getText().toString().trim();

                if (sentence.isEmpty()) {
                    etPangram.setError("Field cannot be empty");
                    Toast.makeText(MainActivity.this, "Enter a sentence", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Check pangram
                if (isPangram(sentence)) {
                    tvPangramResult.setText("The sentence is a Pangram");
                } else {
                    tvPangramResult.setText("The sentence is NOT a Pangram");
                }
            }
        });
    }

    // Method to check Anagram
    private boolean isAnagram(String s1, String s2) {

        // Remove spaces and convert to lowercase
        s1 = s1.replaceAll("[^a-zA-Z]", "").toLowerCase();
        s2 = s2.replaceAll("[^a-zA-Z]", "").toLowerCase();

        if (s1.length() != s2.length())
            return false;

        // Convert strings to char array and sort
        char[] arr1 = s1.toCharArray();
        char[] arr2 = s2.toCharArray();

        Arrays.sort(arr1);
        Arrays.sort(arr2);

        return Arrays.equals(arr1, arr2);
    }

    // Method to check Pangram
    private boolean isPangram(String sentence) {

        HashSet<Character> letters = new HashSet<>();
        sentence = sentence.toLowerCase();

        for (char c : sentence.toCharArray()) {
            if (c >= 'a' && c <= 'z') {
                letters.add(c);
            }
        }

        // Pangram must contain 26 letters
        return letters.size() == 26;
    }
}
