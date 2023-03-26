package com.sergio.project11;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

public class RegistrarActivity extends AppCompatActivity {

    Spinner spCurso;
    private ArrayAdapter<CharSequence> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar);

        spCurso = findViewById(R.id.spCurso);

        adapter = ArrayAdapter.createFromResource(
                this, R.array.spinner_items, R.layout.spinner_item);
        adapter.setDropDownViewResource(R.layout.spinner_item);

        spCurso.setAdapter(adapter);

        // Establecer el elemento "hint" como el predeterminado
        spCurso.setSelection(0);

        // Evitar que el elemento "hint" sea seleccionable
        spCurso.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    ((TextView) view).setTextColor(Color.GRAY);
                    ((TextView) view).setTextSize(16);
                } else {
                    ((TextView) view).setTextColor(Color.BLACK);
                    ((TextView) view).setTextSize(16);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }


}
