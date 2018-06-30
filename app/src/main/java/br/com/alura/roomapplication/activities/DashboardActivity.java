package br.com.alura.roomapplication.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import br.com.alura.roomapplication.R;

public class DashboardActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        Button cadastroAlunos = findViewById(R.id.dash_btn_aluno);
        cadastroAlunos.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int viewId = v.getId();
        Intent intent = null;
        switch (viewId) {
            case R.id.dash_btn_aluno:
                intent = new Intent(this, AlunosActivity.class);
                startActivity(intent);
                break;
        }
    }

}
