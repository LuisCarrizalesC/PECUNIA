package com.example.pecunia_elibeluies;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class PerfilUsuario extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.perfil_usuario);

        ImageButton ingreso = findViewById(R.id.ibIngreso);
        ImageButton egreso = findViewById(R.id.ibEgreso);

        //aqui manda al layout de fijos al hacer clic en ingreso
        ingreso.setOnClickListener(new  View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(PerfilUsuario.this, "", Toast.LENGTH_SHORT).show();

                Intent intentFijo = new Intent(PerfilUsuario.this, Fijos.class);
                PerfilUsuario.this.startActivity(intentFijo);
            }
        });

        //aqui manda a layout egreso
        egreso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(PerfilUsuario.this, "", Toast.LENGTH_SHORT).show();

                Intent intentFijo = new Intent(PerfilUsuario.this, Fijos.class);
                PerfilUsuario.this.startActivity(intentFijo);
            }
        });
    }
}
