package EshanBehal.calculator;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    //firstly the three main widgets used are initialised.

    private EditText result;
    private EditText newNumber;
    private TextView displayOperation;

    // variables to hold operands and type of operations.

    private Double operand1 = null;
    private String pendingOperation = "=";

    private static final String STATE_PENDING_OPERATION = "PendingOperation";
    private static final String STATE_OPERAND1 = "Operand1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        result = (EditText) findViewById(R.id.result);
        newNumber = (EditText) findViewById(R.id.newNumber);
        displayOperation = (TextView) findViewById(R.id.operation);

        //now as buttons are not assigned as fields so assigning them as purely variables.

        Button button0 = (Button) findViewById(R.id.button0);
        Button button1 = (Button) findViewById(R.id.button1);
        Button button2 = (Button) findViewById(R.id.button2);
        Button button3 = (Button) findViewById(R.id.button3);
        Button button4 = (Button) findViewById(R.id.button4);
        Button button5 = (Button) findViewById(R.id.button5);
        Button button6 = (Button) findViewById(R.id.button6);
        Button button7 = (Button) findViewById(R.id.button7);
        Button button8 = (Button) findViewById(R.id.button8);
        Button button9 = (Button) findViewById(R.id.button9);
        Button buttonDot = (Button) findViewById(R.id.buttonDot);

        //now for simplifying the code written the operands are mentioned above and operations to
        //to be performed are written seperately.

        Button buttonEguals = (Button) findViewById(R.id.buttonEquals);
        Button buttonDivide = (Button) findViewById(R.id.buttonDivide);
        Button buttonMultiply = (Button) findViewById(R.id.buttonMultiply);
        Button buttonMinus = (Button) findViewById(R.id.buttonMinus);
        Button buttonPlus = (Button) findViewById(R.id.buttonPlus);

        //after initializing all the buttons as variables now we write code for onClickListner.

        View.OnClickListener listner = new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //to find the button that is tapped.

                Button b = (Button) view;

                //then we are going to app this button.

                newNumber.append(b.getText().toString());
            }
        };
        //now passing this listner to each button 0-9 and dot button so that when any of them
        //clicked we are gonna go ahead.
        button0.setOnClickListener(listner);
        button1.setOnClickListener(listner);
        button2.setOnClickListener(listner);
        button3.setOnClickListener(listner);
        button4.setOnClickListener(listner);
        button5.setOnClickListener(listner);
        button6.setOnClickListener(listner);
        button7.setOnClickListener(listner);
        button8.setOnClickListener(listner);
        button9.setOnClickListener(listner);
        buttonDot.setOnClickListener(listner);

        View.OnClickListener opListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Button b = (Button) view;
                String op = b.getText().toString();
                String value = newNumber.getText().toString();
                try {
                    Double doubleValue = Double.valueOf(value);
                    performOperation(doubleValue, op);
                } catch (NumberFormatException e) {
                    newNumber.setText("");
                }
                pendingOperation = op;
                displayOperation.setText(pendingOperation);
            }
        };

        buttonEguals.setOnClickListener(opListener);
        buttonDivide.setOnClickListener(opListener);
        buttonMultiply.setOnClickListener(opListener);
        buttonMinus.setOnClickListener(opListener);
        buttonPlus.setOnClickListener(opListener);

        // writing here code for my new negation button.

        Button buttonNeg = (Button) findViewById(R.id.buttonNeg);

        buttonNeg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String value = newNumber.getText().toString();
                if(value.length() == 0){
                    newNumber.setText("-");
                }else{
                    try {
                        Double doubleValue = Double.valueOf(value);
                        doubleValue *= -1;
                        newNumber.setText(doubleValue.toString());
                    }catch (NumberFormatException e){
                        //newNumber was "-" or "*" , so clean it.
                        newNumber.setText("");
                    }
                }

            }
        });
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putString(STATE_PENDING_OPERATION, pendingOperation);
        if (operand1 != null) {
            outState.putDouble(STATE_OPERAND1, operand1);
        }
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        pendingOperation = savedInstanceState.getString(STATE_PENDING_OPERATION);
        operand1 = savedInstanceState.getDouble(STATE_OPERAND1);
        displayOperation.setText(pendingOperation);
    }

    //here as we have used performOperation in above code we write below dummy code just to check
    //whether the code works properly or not.
    private void performOperation(Double value , String operation) {
        // displayOperation.setText(operation);
        if (null == operand1) {
            operand1 = value;
        } else {
            if (pendingOperation.equals("=")) {
                pendingOperation = operation;
            }
            // here using switch statement for the implementation of operations.
            switch (pendingOperation) {
                case "=":
                    operand1 = value;
                    break;
                case "/":
                    if (value == 0) {
                        operand1 = 0.0;
                    } else {
                        operand1 /= value;
                    }
                    break;
                case "*":
                    operand1 *= value;
                    break;
                case "-":
                    operand1 -= value;
                    break;
                case "+":
                    operand1 += value;
                    break;
            }
        }

        result.setText(operand1.toString());
        newNumber.setText("");
    }
}