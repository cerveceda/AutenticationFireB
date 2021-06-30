package com.example.autenticationfireb;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Registro extends AppCompatActivity {
    EditText NC, EMAIL, PAS, PHONE;
    Button Registro;
    TextView loginBtn;
    FirebaseAuth fauth;

    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        NC=findViewById(R.id.txtNombre);
        EMAIL=findViewById(R.id.txtEmail);
        PAS=findViewById(R.id.txtConstrasena);
        PHONE=findViewById(R.id.txtTelefono);
        Registro=findViewById(R.id.btnRegistro);
        loginBtn=findViewById(R.id.lblAqui);
        progressBar=findViewById(R.id.progressBar);
        fauth=FirebaseAuth.getInstance();
        
        //if(fauth.getCurrentUser()!=null){
            //startActivity(new Intent(getApplicationContext(), Login.class));
        //}

        Registro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = EMAIL.getText().toString().trim();
                String password = PAS.getText().toString().trim();

                if(TextUtils.isEmpty(email)){
                    EMAIL.setError("Email es requerido");
                    return;
                }
                if(TextUtils.isEmpty(password)){
                    PAS.setError("Contraseña requerida");
                    return;
                }
                if(password.length()<6){
                    PAS.setError("La contraseña no tiene que ser mayo a 6 caracteres");
                    return;
                }
                progressBar.setVisibility(view.VISIBLE);

                fauth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(Registro.this, "Usuario creado correctamente", Toast.LENGTH_SHORT);
                            startActivity(new Intent(Registro.this, Login.class));
                        }else{
                            Toast.makeText(Registro.this, "Se produjo un error, usuario no creado", Toast.LENGTH_LONG).show();
                            progressBar.setVisibility(View.GONE);
                        }
                    }
                });
            }
        });
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Login.class));
            }
        });
    }
}