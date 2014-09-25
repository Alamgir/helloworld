package com.example.helloworld;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.*;
import android.view.inputmethod.EditorInfo;
import android.widget.*;
import com.example.helloworld.animation.ImageAnimation;

public class MyActivity extends Activity implements View.OnClickListener, View.OnLongClickListener {
    public LayoutInflater inflater;

    public static final String TAG = "LIFECYCLE";
    public boolean liveText = false;

    Button btnPush;
    Button interfaceBtn;
    ToggleButton liveTextBtn;

    TextView tv;

    EditText editText;

    ImageView fileIcon;

    static LinearLayout listViewContainer;
    ListView listView;
    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.i(TAG, "in onCreate()");

        inflater = getLayoutInflater();

        setContentView(R.layout.main);
        listViewContainer = (LinearLayout)findViewById(R.id.list_view_container);

        if (savedInstanceState == null) {
            getFragmentManager().beginTransaction()
                    .add(R.id.list_view_container, new MainFragment())
                    .commit();
        }

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
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class MainFragment extends Fragment {

        public MainFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);

            ListView listView = (ListView)rootView.findViewById(R.id.list_view);
            final String[] items = {
                    "Journey",
                    "Heart",
                    "Styx",
                    "Foreigner",
                    "Kansas",
                    "Kiss"
            };
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
                    android.R.layout.simple_expandable_list_item_1,
                    items);
            listView.setAdapter(adapter);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                    Toast.makeText(getActivity(), items[position], Toast.LENGTH_SHORT).show();
                }
            });


            return rootView;
        }
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
