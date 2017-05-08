package com.studioyuando.hw4;


import android.content.ContentValues;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.RadioButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

/**
 * Created by kudouyuandou on 2017/05/06.
 */

public class MainActivity extends AppCompatActivity {
    RadioButton maleRadiobutton ;
    RadioButton femaleRadiobutton;
    EditText mNameeditText;
    EditText mAgeeditText;
    RecyclerView mMemList;
    SessionMember memberAdapter;
    private String morderBy;
    private SQLiteDatabase mDb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        morderBy = SessionManagerContract.SessionManagerEntry._ID;
        mMemList = (RecyclerView)findViewById(R.id.member_list_recy_view);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        SessionDatabase managerDbHelper = new SessionDatabase(this);
        mDb = managerDbHelper.getWritableDatabase();
        mMemList.setLayoutManager(new LinearLayoutManager(this));
        memberAdapter = new SessionMember(this,getAllMemList());
        mMemList.setAdapter(memberAdapter);

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }
            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int swipeDir) {
                int id = (int) viewHolder.itemView.getTag();
                removeMember(id);
                memberAdapter.swapCursor(getAllMemList());

            }
        }).attachToRecyclerView(mMemList);
      //  Toast.makeText(getApplicationContext(),"Debug 58: I did onCreate()",Toast.LENGTH_SHORT).show();
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }
    public void addnew(View bn){
        LayoutInflater li = LayoutInflater.from(MainActivity.this);
        View v = li.inflate(R.layout.form, null);

        AlertDialog.Builder alertdiaglogbuilder = new AlertDialog.Builder(MainActivity.this);
        alertdiaglogbuilder.setView(v);
        maleRadiobutton = (RadioButton) v.findViewById(R.id.male_radio_button);
        femaleRadiobutton = (RadioButton)v.findViewById(R.id.female_radio_button);
        mNameeditText = (EditText) v.findViewById(R.id.name_edit_text);
        mAgeeditText = (EditText) v.findViewById(R.id.Age_edit_text);
        alertdiaglogbuilder.setCancelable(true)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                if(mNameeditText.getText().length() ==0 || mAgeeditText.getText().length() ==0){
                                    return;
                                }
                                int gender;
                                gender = maleRadiobutton.isChecked() ? 0:1;
                                System.out.println("Gender Check :" +gender);
                                String gName = mNameeditText.getText().toString();
                                int gAge =1;
                                try {
                                    gAge =  Integer.parseInt(mAgeeditText.getText().toString());
                                }catch (Exception e){
                                    e.printStackTrace();
                                }
                                addnewMember(gName , gAge , gender);
                                memberAdapter.swapCursor(getAllMemList());
                            }
                        })
                .setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });
        AlertDialog alertDialog = alertdiaglogbuilder.create();
        alertDialog.show();
      //  Toast.makeText(getApplicationContext(),"Debug 106: I did addnew()",Toast.LENGTH_SHORT).show();
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.item_sort_by_id:
                morderBy = SessionManagerContract.SessionManagerEntry._ID;
                break;
            case R.id.item_sort_by_name:
                morderBy = SessionManagerContract.SessionManagerEntry.COLUMN_GNAME;
                break;
            case R.id.item_sort_by_gender:
                morderBy = SessionManagerContract.SessionManagerEntry.COLUMN_GGENDER;
                break;
            case R.id.item_sort_by_age:
                morderBy = SessionManagerContract.SessionManagerEntry.COLUMN_GAGE;
                break;
        }
        memberAdapter.swapCursor(getAllMemList());
        return super.onOptionsItemSelected(item);

    }

    public void onRadioButtonClicked(View view) {
        boolean checked = ((RadioButton) view).isChecked();
        switch(view.getId()) {
            case R.id.male_radio_button:
                if (checked)
                    break;
            case R.id.female_radio_button:
                if (checked)
                    break;
        }
        //Toast.makeText(getApplicationContext(),"Debug 146: I did onRadioButtonClicked()",Toast.LENGTH_SHORT).show();
    }

    public long addnewMember(String mName ,int mAge , int mGender){
        ContentValues contentv = new ContentValues();
        contentv.put(SessionManagerContract.SessionManagerEntry.COLUMN_GNAME , mName);
        contentv.put(SessionManagerContract.SessionManagerEntry.COLUMN_GAGE , mAge);
        contentv.put(SessionManagerContract.SessionManagerEntry.COLUMN_GGENDER , mGender);
        return mDb.insert(SessionManagerContract.SessionManagerEntry.TABLE_NAME ,null,contentv);
    }
    public Cursor getAllMemList(){
        return mDb.query(
                SessionManagerContract.SessionManagerEntry.TABLE_NAME,null ,null ,null,null,null,morderBy
        );
    }
    public int removeMember(int id){
        return mDb.delete(SessionManagerContract.SessionManagerEntry.TABLE_NAME , SessionManagerContract.SessionManagerEntry._ID + "=" + id , null);
    }
    public void genderButtonClick(View v){
        switch (v.getId()){
            case  R.id.male_icon:
                maleRadiobutton.setChecked(true);
                femaleRadiobutton.setChecked(false);
                break;
            case R.id.female_icon:
                maleRadiobutton.setChecked(false);
                femaleRadiobutton.setChecked(true);
                break;
        }
      //  Toast.makeText(getApplicationContext(),"Debug 174: I did genderButtonClick()",Toast.LENGTH_SHORT).show();
    }
/*    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.item_sort_by_id:
                morderBy = SessionManagerContract.SessionManagerEntry._ID;
                break;
            case R.id.item_sort_by_name:
                morderBy = SessionManagerContract.SessionManagerEntry.COLUMN_GNAME;
                break;
            case R.id.item_sort_by_gender:
                morderBy = SessionManagerContract.SessionManagerEntry.COLUMN_GGENDER;
                break;
            case R.id.item_sort_by_age:
                morderBy = SessionManagerContract.SessionManagerEntry.COLUMN_GAGE;
                break;
        }
        memberAdapter.swapCursor(getAllMemList());
        return super.onOptionsItemSelected(item);
         Toast.makeText(getApplicationContext(),"Debug 193: I did onOptionsItemSelected()",Toast.LENGTH_SHORT).show();
    }
*/

}
