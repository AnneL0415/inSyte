package com.example.insyte;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import java.util.HashSet;

public class JournalEditorActivity extends AppCompatActivity {

    int noteId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_journal_editor);

        EditText journalEditText = (EditText) findViewById(R.id.journalEditText);

        Intent intent = getIntent();
        noteId = intent.getIntExtra("noteId", -1);

        if(noteId != -1) {
            journalEditText.setText(JournalJournal.notes.get(noteId));
        }else{
            JournalJournal.notes.add("");
            noteId = JournalJournal.notes.size() - 1;
            JournalJournal.arrayAdapter.notifyDataSetChanged();
        }

        journalEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                JournalJournal.notes.set(noteId, String.valueOf(s));
                JournalJournal.arrayAdapter.notifyDataSetChanged();

                SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("com.example.insyte", Context.MODE_PRIVATE);

                HashSet<String> set = new HashSet(JournalJournal.notes);
                sharedPreferences.edit().putStringSet("notes", set).apply();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }
}