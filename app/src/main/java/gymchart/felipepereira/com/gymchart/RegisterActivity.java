package gymchart.felipepereira.com.gymchart;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;

import Controller.DatabaseController;

public class RegisterActivity extends AppCompatActivity {

    private ConstraintLayout layoutRegister;
    private ImageView userImage;

    public static final int IMAGEM_INTERNA = 12;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        layoutRegister = findViewById(R.id.LayoutRegister);

        Spinner spinner_Gender = findViewById(R.id.spinner_gender);
        ArrayAdapter adapter = ArrayAdapter.createFromResource(this, R.array.gender, android.R.layout.simple_list_item_1);
        spinner_Gender.setAdapter(adapter);

        userImage = findViewById(R.id.user_image);

        userImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showImage(v);
            }
        });

//button register User ---------------------------------------------------------
        Button btn_Register = findViewById(R.id.btn_register);
        btn_Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DatabaseController databaseController = new DatabaseController(getBaseContext());
                EditText name = findViewById(R.id.user_name);
                EditText age = findViewById(R.id.user_age);
                Spinner gender = findViewById(R.id.spinner_gender);
                EditText weight = findViewById(R.id.user_weight);
                EditText height = findViewById(R.id.user_height);
                EditText bodyFat = findViewById(R.id.user_bodyFat);

                String nameString = name.getText().toString();
                int ageInt = Integer.parseInt(age.getText().toString());
                String genderString = gender.getSelectedItem().toString();
                float weightFloat = Float.parseFloat(weight.getText().toString());
                int heightInt = Integer.parseInt(height.getText().toString());
                float bodyFatFloat = Float.parseFloat(bodyFat.getText().toString());
                ///////////////////////////////////////////////////////////////////////
                userImage.buildDrawingCache();
                ///////////////////////////////////////////////////////////////////////
                long result = databaseController.createUser(nameString, ageInt, genderString, weightFloat, heightInt, bodyFatFloat, getBytes(userImage.getDrawingCache()));
                Toast.makeText(getApplicationContext(), "Saved", Toast.LENGTH_LONG).show();

                ShowScreen showScreen = new ShowScreen();
                showScreen.load(RegisterActivity.this, HomeActivity.class);
            }
        });
//////////////////////////////////////////////////////////////////////////////////////////////////
    }
    public void showImage(View view) {

        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        startActivityForResult(intent, IMAGEM_INTERNA);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent){
        if(requestCode == IMAGEM_INTERNA){
            if(resultCode == RESULT_OK){

                Uri imageSelected = intent.getData();
                String [] columns = {MediaStore.Images.Media.DATA};

                Cursor cursor = getContentResolver().query(imageSelected, columns, null, null, null);
                cursor.moveToFirst();

                int indexColumn = cursor.getColumnIndex(columns[0]);
                String pathImg= cursor.getString(indexColumn);
                cursor.close();

                Bitmap bitmap = null;
                try {
                    bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(imageSelected));
                    userImage = findViewById(R.id.user_image);
                    userImage.setImageBitmap(bitmap);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    public static byte[] getBytes(Bitmap bitmap) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 0, stream);
        return stream.toByteArray();
    }

    // convert from byte array to bitmap
    public static Bitmap getPhoto(byte[] image) {
        return BitmapFactory.decodeByteArray(image, 0, image.length);
    }
}
