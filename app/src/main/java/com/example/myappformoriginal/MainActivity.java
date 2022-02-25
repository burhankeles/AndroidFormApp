package com.example.myappformoriginal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.vishnusivadas.advanced_httpurlconnection.PutData;

public class MainActivity extends AppCompatActivity {
    TextInputEditText textInputEditTextName, textInputEditTextSurname, textInputEditTextEmail, textInputEditTextPhone;
    CheckBox checkboxStudent;
    Spinner spinnerDepartment;
    Button buttonSubmit;
    TextView textViewSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textInputEditTextName = findViewById(R.id.name);
        textInputEditTextSurname = findViewById(R.id.surname) ;
        textInputEditTextEmail = findViewById(R.id.email);
        textInputEditTextPhone = findViewById(R.id.phone);
        buttonSubmit = findViewById(R.id.button);
        checkboxStudent = (CheckBox) findViewById(R.id.student);
        spinnerDepartment = (Spinner)findViewById(R.id.department);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.departments, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerDepartment.setAdapter(adapter);
        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {
                String name,surname,email,phone,student,department;
                name = String.valueOf(textInputEditTextName.getText());
                surname = String.valueOf(textInputEditTextSurname.getText());
                email = String.valueOf(textInputEditTextEmail.getText());
                phone = String.valueOf(textInputEditTextPhone.getText());
                if (checkboxStudent.isChecked()) {
                    student = "Yes";
                }else{
                    student = "No";
                }
                department = spinnerDepartment.getSelectedItem().toString();
                if(!name.equals("") && !surname.equals("") && !email.equals("")&&!phone.equals("")) {
                    //Start ProgressBar first (Set visibility VISIBLE)
                    Handler handler = new Handler(Looper.getMainLooper());
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            //Starting Write and Read data with URL
                            //Creating array for parameters
                            String[] field = new String[6];
                            field[0] = "name";
                            field[1] = "surname";
                            field[2] = "email";
                            field[3] = "phone";
                            field[4] = "student";
                            field[5] = "department";
                            //Creating array for data
                            String[] data = new String[6];
                            data[0] = name;
                            data[1] = surname;
                            data[2] = email;
                            data[3] = phone;
                            data[4] = student;
                            data[5]= department;
                            PutData putData = new PutData("http://192.168.56.1/AppForm/submit.php", "POST", field, data);
                            if (putData.startPut()) {
                                if (putData.onComplete()) {
                                    String result = putData.getResult();
                                        if(result.equals("Insert Successful")){
                                            Toast.makeText(getApplicationContext(),result,Toast.LENGTH_SHORT).show();
                                        }
                                        else{
                                            Toast.makeText(getApplicationContext(),result,Toast.LENGTH_SHORT).show();
                                        }
                                }
                            }
                            //End Write and Read data with URL
                        }
                    });
                }
                else{
                    Toast.makeText(getApplicationContext(),"All fields required!",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}