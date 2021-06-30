package com.example.autenticationfireb;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
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

public class Login extends AppCompatActivity {

    EditText email, pasword;
    Button loginbut;
    TextView txtCreate;
    ProgressBar progressBar;
    FirebaseAuth fauth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email=findViewById(R.id.txtEmailLog);
        pasword=findViewById(R.id.txtConLog);
        progressBar=findViewById(R.id.progressBar2);
        fauth=FirebaseAuth.getInstance();
        loginbut=findViewById(R.id.btnLogin);
        txtCreate=findViewById(R.id.crearCuenta);

        loginbut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String ema = email.getText().toString().trim();
                String contra = pasword.getText().toString().trim();

                if(TextUtils.isEmpty(ema)){
                    email.setError("Email es requerido");
                    return;
                }
                if(TextUtils.isEmpty(contra)){
                    pasword.setError("Contraseña requerida");
                    return;
                }
                if(contra.length()<6){
                    pasword.setError("La contraseña no tiene que ser mayo a 6 caracteres");
                    return;
                }
                progressBar.setVisibility(view.VISIBLE);

                fauth.signInWithEmailAndPassword(ema,contra).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(Login.this, "Usuario Logeado correctamente", Toast.LENGTH_SHORT);
                            startActivity(new Intent(getApplicationContext(), MenuDrawerActivity.class));
                        }else{
                            Toast.makeText(Login.this, "Se produjo un error, vuelve a intentarlo", Toast.LENGTH_LONG).show();
                            progressBar.setVisibility(View.GONE);
                        }
                    }
                });
            }
        });
        txtCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Login.this, Registro.class));
            }
        });
    }
}