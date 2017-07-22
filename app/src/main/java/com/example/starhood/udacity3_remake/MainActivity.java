package com.example.starhood.udacity3_remake;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    //LinearLayout startLayout, firstLayout, secondLayout, thirdLayout, fourthLayout, fifthLayout, sixthLayout;
    LinearLayout layout[],viewLayout;
    EditText Question1ET,Question2ET;
    Button submitButton;
    RadioGroup Question3RG,Question4RG;
    //CheckBox Q5CB1, Q5CB2, Q5CB3, Q6CB1, Q6CB2, Q6CB3;
    View view[];
    CheckBox Question5CB[],Question6CB[];

    int currentQuestion=0;
    int score=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        layout=new LinearLayout[7];
        layout[0]=(LinearLayout)findViewById(R.id.start_test_layout);
        layout[1]=(LinearLayout)findViewById(R.id.first_question_layout);
        layout[2]=(LinearLayout)findViewById(R.id.Second_question_layout);
        layout[3]=(LinearLayout)findViewById(R.id.third_question_layout);
        layout[4]=(LinearLayout)findViewById(R.id.fourth_question_layout);
        layout[5]=(LinearLayout)findViewById(R.id.fifth_question_layout);
        layout[6]=(LinearLayout)findViewById(R.id.sixth_question_layout);

        viewLayout=(LinearLayout)findViewById(R.id.view_layout);

        submitButton=(Button)findViewById(R.id.submit_button);

        Question1ET=(EditText)findViewById(R.id.first_question_answer);
        Question2ET=(EditText)findViewById(R.id.second_question_answer);

        Question3RG=(RadioGroup)findViewById(R.id.Q3RG);
        Question4RG=(RadioGroup)findViewById(R.id.Q4RG);

        Question5CB=new CheckBox[3];
        Question5CB[0]=(CheckBox)findViewById(R.id.Q5CB1);
        Question5CB[1]=(CheckBox)findViewById(R.id.Q5CB2);
        Question5CB[2]=(CheckBox)findViewById(R.id.Q5CB3);

        Question6CB=new CheckBox[3];
        Question6CB[0]=(CheckBox)findViewById(R.id.Q6CB1);
        Question6CB[1]=(CheckBox)findViewById(R.id.Q6CB2);
        Question6CB[2]=(CheckBox)findViewById(R.id.Q6CB3);

        view=new View[10];
        view[0]=(View)findViewById(R.id.v1);
        view[1]=(View)findViewById(R.id.v2);
        view[2]=(View)findViewById(R.id.v3);
        view[3]=(View)findViewById(R.id.v4);
        view[4]=(View)findViewById(R.id.v5);
        view[5]=(View)findViewById(R.id.v6);



    }

    public void buttonClicked(View view) {
        if(checkQuestion())
        updateScreen();
        else return;

    }

    //update the layout
    private void updateScreen() {
        if(currentQuestion<6){
        layout[currentQuestion++].setVisibility(View.INVISIBLE);
        layout[currentQuestion].setVisibility(View.VISIBLE);}
        else end();
    }

    /**
     * check question number and answer
     * */
    private boolean checkQuestion() {
        if(currentQuestion==1)
            return checkFirstQuestionAnswer();
        else if(currentQuestion==2)
            return checkSecondQuestionAnswer();
        else if(currentQuestion==3)
            return checkthirdQuestionAnswer();
        else if(currentQuestion==4)
            return checktfouthQuestionAnswer();
        else if(currentQuestion==5)
            return checkfifthQuestionAnswer();
        else if(currentQuestion==6)
            return checktsixthQuestionAnswer();
        else return false;

    }

    //CheckBox Questions
    private boolean checkfifthQuestionAnswer() {
        int cflag=0,aflag=0;
        for(int i=0;i<3;i++)
            if(Question5CB[i].isChecked())
            {   cflag++;
                if(Question5CB[i].getText().toString().equals("Football")
                        ||Question5CB[i].getText().toString().equals("Basketball"))
                    aflag++;
            }

        if (cflag==2&&aflag==2)
            correctAnswer();
        else
            wrongAnswer();
        return true;
    }

    private boolean checktsixthQuestionAnswer() {
        int cflag=0,aflag=0;
        for(int i=0;i<3;i++)
            if(Question6CB[i].isChecked())
            {   cflag++;
                if(Question6CB[i].getText().toString().equals("Yes")
                        ||Question6CB[i].getText().toString().equals("NO")
                        ||Question6CB[i].getText().toString().equals("Two Crying Babies"))
                    aflag++;
                Log.v(Question6CB[i].getText().toString(), Integer.toString(aflag));
            }

        if (cflag==3&&aflag==3)
            correctAnswer();
        else
            wrongAnswer();
        currentQuestion++;

        return true;
    }


    //RadioButton Questions
    private boolean checkthirdQuestionAnswer() {

        int selectedId = Question3RG.getCheckedRadioButtonId();
        RadioButton selectedRadioButton = (RadioButton)findViewById(selectedId);
        if(selectedRadioButton==null) {
            showToast(getString(R.string.fillchoice));
            return false;
        }
        if(selectedRadioButton.getText().toString().equals("Pen"))
            correctAnswer();
        else
            wrongAnswer();
        return true;
    }

    private boolean checktfouthQuestionAnswer() {
        int selectedId = Question4RG.getCheckedRadioButtonId();
        RadioButton selectedRadioButton = (RadioButton)findViewById(selectedId);
        if(selectedRadioButton==null) {
            showToast(getString(R.string.fillchoice));
            return false;
        }
        if(selectedRadioButton.getText().toString().equals("Crying baby"))
            correctAnswer();
        else
            wrongAnswer();
        return true;
    }


    //Edittext questions
    private boolean checkSecondQuestionAnswer() {
        Log.v(Question2ET.getText().toString(),"12");
        if(Question2ET.getText().toString().equals("21"))

            correctAnswer();
        else
            wrongAnswer();
        return true;
    }

    private boolean checkFirstQuestionAnswer() {
        Log.v(Question1ET.getText().toString(),"12");
        if(Question1ET.getText().toString().equals("12"))
            correctAnswer();
        else
            wrongAnswer();
        return true;
    }




    //change the color if the answer where wrong
    private void wrongAnswer() {
        view[currentQuestion-1].setBackgroundColor(Color.rgb(102, 153, 153));
        showToast(getString(R.string.score,score));

    }

    //change the color if the answer where correct
    private void correctAnswer() {
        view[currentQuestion-1].setBackgroundColor(Color.rgb(0,255,0));
        score++;
        showToast(getString(R.string.score,score));

    }

    void showToast(String message)
    {
        Toast toast=Toast.makeText(this,message,Toast.LENGTH_LONG);
        toast.show();

    }

    /**
     * hide the startup layout and shows the first question to start the quiz
     * */
    public void startQuiz(View view) {
        layout[0].setVisibility(View.INVISIBLE);
        layout[++currentQuestion].setVisibility(View.VISIBLE);
        viewLayout.setVisibility(View.VISIBLE);
        submitButton.setVisibility(View.VISIBLE);

    }

    //gameover messagebox
    void end(){


        AlertDialog.Builder dlgAlert  = new AlertDialog.Builder(this);
        dlgAlert.setMessage(getString(R.string.finishMessage,score));
        dlgAlert.setTitle(getString(R.string.messageBoxTitle));
        dlgAlert.setPositiveButton("Ok",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        //dismiss the dialog
                        return;
                    }
                });
        dlgAlert.setCancelable(true);
        dlgAlert.create().show();
        return;


    }
}
