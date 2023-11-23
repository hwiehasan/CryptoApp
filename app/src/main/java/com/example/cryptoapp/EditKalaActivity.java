package com.example.cryptoapp;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

//import es.dmoral.toasty.Toasty;

public class EditKalaActivity extends AppCompatActivity {

    CardView chooseImage;
    ImageView image;
    TextView title, description, price;
    Button edit_product_button;
    private Bitmap bitmap = null;
    ActivityResultLauncher<Intent> launcher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // باید قبل از لود شدن صفحه تعریف کنیم
        launcher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if (result.getResultCode() == Activity.RESULT_OK) {
                            Intent data = result.getData();
                            Uri picUri = data.getData();

                            try {
                                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), picUri);
                                image = findViewById(R.id.image_edit_product);
                                image.setImageBitmap(bitmap);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                });
        setContentView(R.layout.activity_edit_kala);

        chooseImage = findViewById(R.id.choose_image_edit_product);
        image = findViewById(R.id.image_edit_product);
        title = findViewById(R.id.title_edit_product);
        description = findViewById(R.id.description_edit_product);
        price = findViewById(R.id.price_edit_product);
        edit_product_button = findViewById(R.id.button_edit_product);

        Intent productData = getIntent();
        String product_id = productData.getStringExtra("PRODUCTID");

        String user_id = productData.getStringExtra("USERID");

        String product_image = productData.getStringExtra("PRODUCTIMAGE");

        Picasso.get().load(product_image).into(image);
        title.setText(productData.getStringExtra("PRODUCTTITLE"));
        description.setText(productData.getStringExtra("PRODUCTDESCRIPTION"));
        price.setText(productData.getStringExtra("PRODUCTPRICE"));

        chooseImage.setOnClickListener(view -> {
            showFileChooser();
        });

        edit_product_button.setOnClickListener(view -> {
            //Toasty.info(EditKalaActivity.this, product_id, Toast.LENGTH_SHORT,true).show();

            KalaClass product = new KalaClass();
            product.setkID(Integer.parseInt(product_id));
            product.setUserID(Integer.parseInt(user_id));
            product.setImage(product_image);
            product.setkName(String.valueOf(title.getText()));
            product.setDescription(String.valueOf(description.getText()));
            product.setPrice(Integer.parseInt(String.valueOf(price.getText())));





            if (product.getkName().equals("")) {
                title.setBackgroundColor(0x1FFF0000);
                //Toasty.error(EditKalaActivity.this, "Error! invalid title.", Toast.LENGTH_SHORT, true).show();
                Toast.makeText(EditKalaActivity.this, "Error! invalid Name.", Toast.LENGTH_SHORT).show();


            } else {
                title.setBackgroundColor(0x1F00FF00);
                if (product.getDescription().equals("")) {
                    description.setBackgroundColor(0x1FFF0000);
                    //Toasty.error(EditKalaActivity.this, "Error! invalid description.", Toast.LENGTH_SHORT, true).show();
                    Toast.makeText(EditKalaActivity.this, "Error! invalid description.", Toast.LENGTH_SHORT).show();
                } else {
                    title.setBackgroundColor(0x00FFFFFF);
                    description.setBackgroundColor(0x1F00FF00);
                    if (String.valueOf(product.getPrice()).equals("")) {
                        price.setBackgroundColor(0x1FFF0000);
                        //Toasty.error(EditKalaActivity.this, "Error! invalid price.", Toast.LENGTH_SHORT, true).show();
                        Toast.makeText(EditKalaActivity.this, "Error! invalid price.", Toast.LENGTH_SHORT).show();
                    } else {
                        description.setBackgroundColor(0x00FFFFFF);
                        price.setBackgroundColor(0x00FFFFFF);
//                        DBAdapter local_db = new DBAdapter(EditProductActivity.this);
//                        local_db.open();
//                        local_db.updateProduct(String.valueOf(product.getId()), String.valueOf(product.getUserId()), product.getTitle(), product.getDescription(), product.getPrice());
//                        local_db.close();
                        uploadProductData(product);
                    }
                }
            }





        });
    }

    private void uploadProductData(KalaClass product) {
        VolleyMultipartRequest volleyMultipartRequest = new VolleyMultipartRequest(Request.Method.POST, Globals.SERVER + "/api/submit-edit-kala.php",
                new Response.Listener<NetworkResponse>() {
                    @Override
                    public void onResponse(NetworkResponse response) {
                        try {
                            JSONObject result = new JSONObject(new String(response.data));
                            if (result.getString("message").equals("success")) {
                                //Toasty.success(EditKalaActivity.this, "Successfully Edited Product", Toast.LENGTH_SHORT, true).show();
                                Toast.makeText(EditKalaActivity.this, "Successfully Edited Product", Toast.LENGTH_SHORT).show();

                                Intent go_to_product_detail = new Intent(EditKalaActivity.this, ShowKalaActivity.class);
                                go_to_product_detail.putExtra("ID", String.valueOf(product.getkID()));
                                go_to_product_detail.putExtra("USERID", String.valueOf(product.getUserID()));
                                go_to_product_detail.putExtra("IMAGE", String.valueOf(product.getImage()));
                                go_to_product_detail.putExtra("TITLE", String.valueOf(product.getkName()));
                                go_to_product_detail.putExtra("DESCRIPTION", String.valueOf(product.getDescription()));
                                go_to_product_detail.putExtra("PRICE", String.valueOf(product.getPrice()));
                                startActivity(go_to_product_detail);
                                finish();
//                                Intent go_to_main = new Intent(EditProductActivity.this, MainActivity.class);
//                                startActivity(go_to_main);
//                                finish();
                            } else {
                                //Toasty.error(EditKalaActivity.this, result.getString("message"), Toast.LENGTH_SHORT, true).show();
                                Toast.makeText(EditKalaActivity.this, result.getString("message"), Toast.LENGTH_SHORT).show();

                            }

                        } catch (JSONException e) {
                            throw new RuntimeException(e);
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //Toasty.error(EditKalaActivity.this, "Error! server connection", Toast.LENGTH_SHORT, true).show();
                        Toast.makeText(EditKalaActivity.this, "Error! server connection", Toast.LENGTH_SHORT).show();

                        Log.d("Server Connection!", error.getMessage());
                    }
                }) {

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new Hashtable<String, String>();
                try {
                    params.put("PRODUCTID", URLEncoder.encode(String.valueOf(product.getkID()), "UTF-8"));
                    params.put("TITLE", URLEncoder.encode(product.getkName(), "UTF-8"));
                    params.put("DESCRIPTION", URLEncoder.encode(product.getDescription(), "UTF-8"));
                    params.put("PRICE", URLEncoder.encode(String.valueOf(product.getPrice()), "UTF-8"));
                } catch (UnsupportedEncodingException e) {
                    throw new RuntimeException(e);
                }
                return params;
            }
            @Override
            protected Map<String, DataPart> getByteData() {
                Map<String, DataPart> params = new HashMap<>();
                if (bitmap != null) {
                    long imageName = System.currentTimeMillis();
                    DataPart data = new DataPart(imageName + ".png", getFileDataFromDrawable(bitmap));
                    params.put("IMAGE", data);
                }
                return params;
            }
        };

        Volley.newRequestQueue(this).add(volleyMultipartRequest);
    }

    public byte[] getFileDataFromDrawable(Bitmap bitmap) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 90, byteArrayOutputStream);
        return byteArrayOutputStream.toByteArray();
    }

    private void showFileChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        launcher.launch(intent);
    }
}