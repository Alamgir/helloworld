package com.example.helloworld;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.*;
import com.example.helloworld.animation.ImageAnimation;

public class MyActivity extends Activity implements View.OnClickListener, View.OnLongClickListener {

    public static final String TAG = "LIFECYCLE";
    public boolean liveText = false;

    Button btnPush;
    Button interfaceBtn;
    ToggleButton liveTextBtn;

    TextView tv;

    EditText editText;

    ImageView fileIcon;

    ListView listView;
    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.i(TAG, "in onCreate()");

        setContentView(R.layout.main);
        LinearLayout imageTableContainer = (LinearLayout)findViewById(R.id.imageTableContainer);
        getLayoutInflater().inflate(R.layout.image_table, imageTableContainer);

        btnPush = (Button)findViewById(R.id.button1);
        btnPush.setOnClickListener(this);
        btnPush.setTag(R.id.MAIN_BUTTON_TOUCHED, false);

        interfaceBtn = (Button)findViewById(R.id.interfaceButton);
        interfaceBtn.setOnLongClickListener(this);

        liveTextBtn = (ToggleButton)findViewById(R.id.liveTextToggle);

        fileIcon = (ImageView)findViewById(R.id.imageIcon);

        tv = (TextView)findViewById(R.id.textView);

        editText = (EditText)findViewById(R.id.editText);
        editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if(keyEvent.getAction() == KeyEvent.ACTION_DOWN) {
                    if(i == EditorInfo.IME_NULL) {
                        tv.setText(textView.getText());
                    }
                }
                return true;
            }
        });
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                if(liveTextBtn.isChecked()) {
                    tv.setText(charSequence);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        listView = (ListView)findViewById(R.id.listView);
        final String[] items = {
                "Journey",
                "Heart",
                "Styx",
                "Foreigner",
                "Kansas",
                "Kiss"
        };
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                                                                android.R.layout.simple_expandable_list_item_1,
                                                                items);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Toast.makeText(getApplicationContext(), items[position], Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.i(TAG, "in onStart()");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.i(TAG, "in onResume()");
    }

    @Override
    public void onRestart() {
        super.onRestart();
        Log.i(TAG, "in onRestart()");
    }

    @Override
    public void onPause() {
        Log.i(TAG, "in onPause()");
        super.onPause();
    }

    @Override
    public void onStop() {
        Log.i(TAG, "in onStop()");
        super.onStop();
    }

    @Override
    public void onDestroy() {
        Log.i(TAG, "in onDestroy()");
        super.onDestroy();
    }

    @Override
    public void onClick(View view) {
        int callerId = view.getId();

        switch(callerId) {
            case R.id.button1:
                if(!(Boolean)btnPush.getTag(R.id.MAIN_BUTTON_TOUCHED)) {
                    btnPush.setTag(R.id.MAIN_BUTTON_TOUCHED, true);
                    btnPush.setText("This button has been touched");

                    ImageAnimation.fadeIn(fileIcon);
                    tv.setText("file icon is visible");
                }
                else {
                    btnPush.setText(R.string.button_label);
                    btnPush.setTag(R.id.MAIN_BUTTON_TOUCHED, false);

                    ImageAnimation.fadeOut(fileIcon);
                    tv.setText("file icon is not visible");
                }
                break;
            case R.id.interfaceButton:
                tv.setText("interface button clicked.");
                break;
            default:
                tv.setText("unrecognizable click");
        }
    }

    @Override
    public boolean onLongClick(View view) {
        int callerId = view.getId();

        switch(callerId) {
            case R.id.interfaceButton:
                Toast.makeText(getApplicationContext(), "Interface Button", Toast.LENGTH_SHORT).show();
                tv.setText("interface button clicked.");
                break;
            default:
                tv.setText("unrecognizable click");
        }

        return true;
    }
}
