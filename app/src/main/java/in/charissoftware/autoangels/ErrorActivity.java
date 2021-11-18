package in.charissoftware.autoangels;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Objects;

public class ErrorActivity extends AppCompatActivity {

    Button continueButton;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_error);
        Objects.requireNonNull(getSupportActionBar()).setTitle("Opps! Something went wrong");

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        continueButton = findViewById(R.id.errorbutton);

        continueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ErrorActivity.this, MainActivity.class);
                startActivity(intent);

            }
        });
    }
}
