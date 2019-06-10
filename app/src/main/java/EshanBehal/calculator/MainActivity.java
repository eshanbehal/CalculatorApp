package EshanBehal.calculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
    private Double operand2 = null;
    private String pendingOperation = "=";


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
        Button buttondot = (Button) findViewById(R.id.buttonDot);

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
        buttondot.setOnClickListener(listner);

        //now as decided to create diffrent onclicklistner for operations.

        View.OnClickListener opListner = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Button b = (Button) view;
                String op = b.getText().toString();
                String value = newNumber.getText().toString();
                if(value.length() != 0){
                    performOperation(value , op);
                }
                pendingOperation = op;
                displayOperation.setText(pendingOperation);


            }
        };

        // now as we have defined onclicklistner we will pass it for operation buttons.

        buttonEguals.setOnClickListener(opListner);
        buttonDivide.setOnClickListener(opListner);
        buttonMultiply.setOnClickListener(opListner);
        buttonMinus.setOnClickListener(opListner);
        buttonPlus.setOnClickListener(opListner);

    }

    //here as we have used performOperation in above code we write below dummy code just to check
    //whether the code works properly or not.
    private void performOperation(String value , String operation) {
        // displayOperation.setText(operation);
        if (null == operand1) {
            operand1 = Double.valueOf(value);
        } else {
            operand2 = Double.valueOf(value);

            if (pendingOperation.equals("=")) {
                pendingOperation = operation;
            }
            // here using switch statement for the implementation of operations.
            switch (pendingOperation) {
                case "=":
                    operand1 = operand2;
                    break;

                case "/":
                    if (operand2 == 0) {
                        operand1 = 0.0;

                    } else {
                        operand1 /= operand2;
                    }
                    break;

                case "*":

                    operand1 *= operand2;
                    break;

                case "-":
                    operand1 -= operand2;
                    break;

                case "+":
                    operand1 += operand2;
                    break;

            }
        }

            result.setText(operand1.toString());
            newNumber.setText("");

        }

    }


