package step.learning.androidspu121;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class CalcActivity extends AppCompatActivity {
    private TextView tvExpression ;
    private TextView tvResult ;

    private double firstNumber = 0;
    private double secondNumber = 0;
    private String operator = "";


    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_calc );

        tvExpression = findViewById( R.id.calc_tv_expression ) ;
        tvExpression.setText( "" );
        tvResult = findViewById( R.id.calc_tv_result ) ;
        tvResult.setText( R.string.calc_btn_0 ) ;

        findViewById( R.id.calc_btn_c ).setOnClickListener( this::clearClick ) ;
        // Пройти циклом по ідентифікаторах calc_btn_[i], всім вказати один обробник
        for( int i = 0; i < 10; i++ ) {
            findViewById(
                    getResources()  // R.
                            .getIdentifier(    // .id (R.id.calc_btn_[i])
                                    "calc_btn_" + i,
                                    "id",
                                    getPackageName()
                            )
            ).setOnClickListener( this::digitClick );
        }
        findViewById(R.id.calc_btn_plus).setOnClickListener(this::operatorClick);
        findViewById(R.id.calc_btn_minus).setOnClickListener(this::operatorClick);
        findViewById(R.id.calc_btn_multiplication).setOnClickListener(this::operatorClick);
        findViewById(R.id.calc_btn_divide).setOnClickListener(this::operatorClick);


        findViewById(R.id.calc_btn_c).setOnClickListener(this::clearClick);
        findViewById(R.id.calc_btn_ce).setOnClickListener(this::clearEntryClick);
        findViewById(R.id.calc_btn_square).setOnClickListener(this::squareClick);
        findViewById(R.id.calc_btn_sqrt).setOnClickListener(this::sqrtClick);
        findViewById(R.id.calc_btn_equal).setOnClickListener(this::equalClick);
    }

    private void digitClick( View view ) {
        String str = tvResult.getText().toString() ;
        if( str.equals( getString( R.string.calc_btn_0 ) ) ) {
            str = "";
        }
        str += ( ( Button ) view ).getText();
        tvResult.setText( str );
    }
    private void clearClick( View view ) {
        tvResult.setText( R.string.calc_btn_0 ) ;
    }
    private void inverseClick( View view ) {
        String str = tvResult.getText().toString() ;
        double arg = getResult( str ) ;
        if( arg == 0 ) {
            tvResult.setText( R.string.calc_div_zero_message );
        }
        else {
            str = "1 / " + str + " =" ;
            tvExpression.setText( str );
            showResult( 1 / arg );
        }
    }
    private void showResult( double res ) {
        String str = String.valueOf( res );
        if( str.length() > 10 ) {
            str = str.substring( 0, 10 ) ;
        }
        str = str.replaceAll( "0", getString( R.string.calc_btn_0 ) ) ;
        tvResult.setText( str );
    }
    private double getResult( String str ) {
        str = str.replaceAll( getString( R.string.calc_btn_0 ), "0" );
        str = str.replaceAll( getString( R.string.calc_btn_comma ), "." );
        return Double.parseDouble( str );
    }

    private void operatorClick(View view) {
        if (!tvResult.getText().toString().isEmpty()) {
            firstNumber = getResult(tvResult.getText().toString());
            operator = ((Button) view).getText().toString();
            tvExpression.setText(tvResult.getText() + " " + operator);
            tvResult.setText(getString(R.string.calc_btn_0));
        }
    }

    private void equalClick(View view) {
        if (!operator.isEmpty() && !tvResult.getText().toString().isEmpty()) {
            secondNumber = getResult(tvResult.getText().toString());
            double result = 0;

            if (operator.equals("+")) {
                result = firstNumber + secondNumber;
            } else if (operator.equals("-")) {
                result = firstNumber - secondNumber;
            } else if (operator.equals("*")) {
                result = firstNumber * secondNumber;
            } else if (operator.equals("/")) {
                if (secondNumber != 0) {
                    result = firstNumber / secondNumber;
                } else {
                    tvResult.setText(R.string.calc_div_zero_message);
                    return;
                }
            }

            showResult(result);
            operator = "";
            firstNumber = result; // Оновити перше число з результатом
            secondNumber = 0;
        }
    }
    private void squareClick(View view) {
        if (!tvResult.getText().toString().isEmpty()) {
            double num = getResult(tvResult.getText().toString());
            double result = num * num;
            showResult(result);
        }
    }

    private void sqrtClick(View view) {
        if (!tvResult.getText().toString().isEmpty()) {
            double num = getResult(tvResult.getText().toString());
            if (num >= 0) {
                double result = Math.sqrt(num);
                showResult(result);
            } else {
                tvResult.setText(R.string.calc_invalid_input);
            }
        }
    }
   private void clearEntryClick(View view) {
        tvResult.setText(R.string.calc_btn_0);
    }
    /*
    Зміна конфігурації
    "+" автоматично визначається потрібний ресурс
    "-" активність перестворюється і втрачається напрацьовані дані
    Для того щоб мати можливість збереження/відновлення цих даних
    задаються наступні обробники:
     */

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putCharSequence( "expression", tvExpression.getText() );
        outState.putCharSequence( "result", tvResult.getText() );
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        tvExpression.setText( savedInstanceState.getCharSequence( "expression" ) );
        tvResult.setText( savedInstanceState.getCharSequence( "result" ) );
    }
}
