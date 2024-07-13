package eu.tutorials.authenticationid;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    EditText etID;
    Button btnSubmit;
    TextView tvResults;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        etID = findViewById(R.id.etID);
        btnSubmit = findViewById(R.id.btnSubmit);
        tvResults = findViewById(R.id.tvResults);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String idNumber = etID.getText().toString().trim();
                if (idNumber.length() == 12) {
                    String dob = idNumber.substring(0, 6);
                    int gender = Integer.parseInt(idNumber.substring(6, 10));
                    int citizenship = Character.getNumericValue(idNumber.charAt(10));
                    String sGender = (gender >= 5000 && gender<=9999) ? "Male" : "Female";
                    String sCitizenship = (citizenship == 0) ? "South African Citizen" : "Permanent Resident";

                    tvResults.setVisibility(View.VISIBLE);
                    tvResults.setText("Date Of Birth: " + dob.substring(0, 2) + "-" + dob.substring(2, 4) + "-" + dob.substring(4) + "\n" +
                            "Gender: " + sGender + "\n" +
                            "Nationality: " + sCitizenship);
                } else {
                    tvResults.setVisibility(View.VISIBLE);
                    tvResults.setText("Enter Valid Input");
                }
            }
        });
    }
}
