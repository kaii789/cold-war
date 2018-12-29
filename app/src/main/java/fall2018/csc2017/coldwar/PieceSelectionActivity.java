package fall2018.csc2017.coldwar;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import java.util.ArrayList;
import fall2018.csc2017.slidingtiles.R;

public class PieceSelectionActivity extends AppCompatActivity {
    protected ArrayList<String> getDiplomatPositions() {
        ArrayList<String> diplomatPositions = new ArrayList<>();

        EditText dip1Input = findViewById(R.id.dip1Input);
        EditText dip2Input = findViewById(R.id.dip2Input);
        EditText dip3Input = findViewById(R.id.dip3Input);
        EditText dip4Input = findViewById(R.id.dip4Input);

        diplomatPositions.add(dip1Input.getText().toString());
        diplomatPositions.add(dip2Input.getText().toString());
        diplomatPositions.add(dip3Input.getText().toString());
        diplomatPositions.add(dip4Input.getText().toString());

        return diplomatPositions;
    }

    protected ArrayList<String> getSpyPositions() {
        ArrayList<String> spyPositions = new ArrayList<>();

        EditText spy1Input = findViewById(R.id.spy1Input);
        EditText spy2Input = findViewById(R.id.spy2Input);
        EditText spy3Input = findViewById(R.id.spy3Input);
        EditText spy4Input = findViewById(R.id.spy4Input);

        spyPositions.add(spy1Input.getText().toString());
        spyPositions.add(spy2Input.getText().toString());
        spyPositions.add(spy3Input.getText().toString());
        spyPositions.add(spy4Input.getText().toString());

        return spyPositions;
    }
}
