package step.learning.androidspu121;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    private TextView tvHello;
    private TextView currentTimeTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // підключення розмітки - робота з UI тільки після цієї команди
        setContentView(R.layout.activity_main);
        // View - пробатько всіх UI елементів
        tvHello = findViewById( R.id.main_tv_hello );
        tvHello.setText( R.string.main_tv_hello_text );

        Button btnHello = findViewById( R.id.main_button_hello );
        btnHello.setOnClickListener( this::helloClick );

        // Отримуємо посилання на TextView
        currentTimeTextView = findViewById(R.id.current_time);

        // Оновлюємо час кожну секунду
        final Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                updateCurrentTime();
                handler.postDelayed(this, 1000); // Оновлювати кожну секунду
            }
        });
    }

    // всі обробники подій повинні мати такий прототип, view - sender
    private void helloClick( View view) {
        //tvHello.setText( tvHello.getText() + "!" );
        Intent calcIntent = new Intent( this.getApplicationContext(), CalcActivity.class );
        startActivity( calcIntent );
    }
    // Метод для оновлення поточного часу
    private void updateCurrentTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss", Locale.getDefault());
        String currentTime = sdf.format(new Date());
        currentTimeTextView.setText(currentTime);
    }
}