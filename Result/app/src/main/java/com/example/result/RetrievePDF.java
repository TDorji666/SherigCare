package com.example.result;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class RetrievePDF extends AppCompatActivity {
    ListView listview;
    DatabaseReference databaseReference;

    List<putPDF> uploadedPDF;
    EditText editTextStd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrieve_p_d_f);

        listview=findViewById(R.id.listView);
        editTextStd=findViewById(R.id.editText);
        uploadedPDF=new ArrayList<>();
        String Details=getIntent().getStringExtra("Details");



        retrievePDFFiles();
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long l) {
                putPDF putPDF=uploadedPDF.get(i);

                Intent intent=new Intent(Intent.ACTION_VIEW);
                intent.setType("application/pdf");
                intent.setData(Uri.parse(putPDF.getUrl()));
                startActivity(intent);
            }
        });
    }

    private void retrievePDFFiles() {

        String Details=getIntent().getStringExtra("Details");
        String Name=getIntent().getStringExtra("Name");
        databaseReference= FirebaseDatabase.getInstance().getReference("ResultUpload");
        Query query=databaseReference.orderByChild("name").equalTo(Name);
        query.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                if (snapshot.exists()){
                    
                }

            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


//        databaseReference
//                .addValueEventListener(new ValueEventListener() {
////        query.addListenerForSingleValueEvent(new ValueEventListener(){
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                for (DataSnapshot ds : snapshot.getChildren()){
//                putPDF putPDF=ds.getValue(putPDF.class);
//                uploadedPDF.add(putPDF);
//                }
//
//                String[] uploadsName=new String[uploadedPDF.size()];
////
//                for (int i=0; i<uploadsName.length;i++){
//                    uploadsName[i]=uploadedPDF.get(i).getName();
//
//                }
//                ArrayAdapter<String> arrayAdapter=new ArrayAdapter<String>(getApplicationContext(),
//                        android.R.layout.simple_list_item_1,uploadsName){
//
//                    @NonNull
//                    @Override
//                    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
//                        View view=super.getView(position, convertView, parent);
//                        TextView textView=(TextView) view.findViewById(android.R.id.text1);
//                        textView.setTextSize(20);
//                        return view;
//                    }
//                };
//                listview.setAdapter(arrayAdapter);
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//                System.out.println("Failed to Retrieve "+ error.getCode());
//            }
//        });
    }
}