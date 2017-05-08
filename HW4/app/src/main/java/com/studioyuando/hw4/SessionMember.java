package com.studioyuando.hw4;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
/**
 * Created by kudouyuandou on 2017/05/05.
 */

public class SessionMember extends RecyclerView.Adapter<SessionMember.SViewHolder>{
    private Context mContext;
    private Cursor mCursor;
    public SessionMember(Context context ,Cursor cursor) {
        this.mCursor = cursor;
        this.mContext = context;
    }

    @Override
    public SessionMember.SViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View viewer = inflater.inflate(R.layout.member , parent,false);
        return new SViewHolder(viewer);
    }

    @Override
    public void onBindViewHolder(SViewHolder holder, int position) {
        if(!mCursor.moveToPosition(position)){
            return;
        }
        String gName = mCursor.getString(mCursor.getColumnIndex(SessionManagerContract.SessionManagerEntry.COLUMN_GNAME));
        int gAge = mCursor.getInt(mCursor.getColumnIndex(SessionManagerContract.SessionManagerEntry.COLUMN_GAGE));
        int gGender = mCursor.getInt(mCursor.getColumnIndex(SessionManagerContract.SessionManagerEntry.COLUMN_GGENDER));
        int id = mCursor.getInt(mCursor.getColumnIndex(SessionManagerContract.SessionManagerEntry._ID));
        holder.idtextView.setText(Integer.valueOf(id).toString());
        holder.gNameTextView.setText(gName);
        holder.ageTextView.setText(Integer.valueOf(gAge).toString());
        holder.itemView.setTag(id);
        if(gGender==1){
            holder.genderImg.setImageResource(R.mipmap.female);
        }
        else {
            holder.genderImg.setImageResource(R.mipmap.male);
            System.out.println("Check gender" +gGender);
        }

    }
    public void swapCursor(Cursor curs){
        if(mCursor!=null){
            mCursor.close();
        }
        mCursor = curs;
        if(curs!=null){
            this.notifyDataSetChanged();
        }
    }
    @Override
    public int getItemCount() {
        return mCursor.getCount();
    }

    class SViewHolder extends RecyclerView.ViewHolder{
        TextView gNameTextView;
        TextView ageTextView;
        TextView idtextView;
        ImageView genderImg;
        public SViewHolder(View itemView) {
            super(itemView);
            genderImg = (ImageView) itemView.findViewById(R.id.gender_img);
            gNameTextView = (TextView)itemView.findViewById(R.id.guest_name_text_view);
            idtextView = (TextView)itemView.findViewById(R.id.id_text_view);
            ageTextView = (TextView)itemView.findViewById(R.id.age_text_view);

        }
    }
 /*  public void onBindViewHolder(SViewHolder holder, int position) {
        if(!mCursor.moveToPosition(position)){
            return;
        }
        String gName = mCursor.getString(mCursor.getColumnIndex(SessionManagerContract.SessionManagerEntry.COLUMN_GNAME));
        int gAge = mCursor.getInt(mCursor.getColumnIndex(SessionManagerContract.SessionManagerEntry.COLUMN_GAGE));
        int gGender = mCursor.getInt(mCursor.getColumnIndex(SessionManagerContract.SessionManagerEntry.COLUMN_GGENDER));
        int id = mCursor.getInt(mCursor.getColumnIndex(SessionManagerContract.SessionManagerEntry._ID));
        holder.idtextView.setText(Integer.valueOf(id).toString());
        holder.gNameTextView.setText(gName);
        holder.ageTextView.setText(Integer.valueOf(gAge).toString());
        holder.itemView.setTag(id);
        if(gGender==1){
            holder.genderImg.setImageResource(R.mipmap.female);
        }
        else {
            holder.genderImg.setImageResource(R.mipmap.male);
            System.out.println("Check gender" +gGender);
        }


    }
    */
}
