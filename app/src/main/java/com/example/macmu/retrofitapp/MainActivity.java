package com.example.macmu.retrofitapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.macmu.retrofitapp.Network.NetworkService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity
{
    Context mContext = this;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void btnMethod(View view)
    {
        ListView lv = (ListView)findViewById((R.id.listView1));
        EditText et = (EditText)findViewById(R.id.editText);
        Integer number = 1;
        try
        {
            number = Integer.parseInt(et.getText().toString());
        }
        catch(Exception e)
        {

        }
        NetworkService.getInstance()
                .getJSONApi()
                .getPostWithID(number)
                .enqueue(new Callback<List<Integer>>()
                {
                    @Override
                    public void onResponse(@NonNull Call<List<Integer>> call, @NonNull Response<List<Integer>> response)
                    {
                        List<Integer> post = response.body();
                        ListView lv = (ListView)findViewById((R.id.listView1));
                        Object [] values = post.toArray();
                        Integer[] integerArray = Arrays.copyOf(values, values.length, Integer[].class);
                        ArrayAdapter<Integer> adapter = new ArrayAdapter<Integer>(mContext, android.R.layout.simple_list_item_1, android.R.id.text1, integerArray);
                        lv.setAdapter(adapter);
                    }

                    @Override
                    public void onFailure(@NonNull Call<List<Integer>> call, @NonNull Throwable t)
                    {
                        TextView tv= (TextView)findViewById(R.id.textView);
                        tv.append("Error occurred while getting request!");
                        Log.d("MyApp", t.getMessage());
                    }
                });
    }
    public void btnAverageMethod(View view)
    {

        ListView lv = (ListView)findViewById((R.id.listView1));
        ListAdapter adapter = lv.getAdapter();
        List<Integer> listTmp = new ArrayList<Integer>();
        String [] stringArray = new String [adapter.getCount()];
        String str = adapter.getItem(0).toString();

        for (int i=1;i<adapter.getCount();i++)
        {
            str =  str + "," + adapter.getItem(i).toString() ;
        }

        NetworkService.getInstance().getLocalhostApi()
                .getPostWithID(str)
                .enqueue(new Callback<Float>()
                {
                    @Override
                    public void onResponse(@NonNull Call<Float> call, @NonNull Response<Float> response)
                    {
                        Float average;
                        if(response.body() != null)
                            average = response.body();
                        else
                            average = new Float(0);

                        TextView tx = (TextView)findViewById(R.id.textView);
                        tx.setText(average.toString());
                    }

                    @Override
                    public void onFailure(@NonNull Call<Float> call, @NonNull Throwable t)
                    {
                        TextView tv= (TextView)findViewById(R.id.textView);
                        tv.append("Error occurred while getting request!");
                    }
                });
    }
}
