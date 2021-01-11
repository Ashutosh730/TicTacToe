package com.example.lionvstiger;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    //varibles
    enum player
    {
        ONE ,TWO,NO
    }

    player current=player.ONE;
    player[] choice=new player[9];
    int [][] winner={{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};
    private boolean flag=true,draw=false;
    private Button restart;
    private GridLayout grid;
    int count=0;
    ImageView tappedimg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        grid=findViewById(R.id.grid);
        restart=findViewById(R.id.restart);
        choice[0]=player.NO;
        choice[1]=player.NO;
        choice[2]=player.NO;
        choice[3]=player.NO;
        choice[4]=player.NO;
        choice[5]=player.NO;
        choice[6]=player.NO;
        choice[7]=player.NO;
        choice[8]=player.NO;

        restart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reset();
            }
        });
    }

    public void imgtapped(View img)
    {
        tappedimg=(ImageView) img;
        int tag=Integer.parseInt(tappedimg.getTag().toString());

        if(choice[tag]==player.NO&&flag){

            choice[tag]=current;

            if(current==player.ONE)
            {
                tappedimg.setImageResource(R.drawable.tiger);
                current=player.TWO;
            }
            else if(current==player.TWO)
            {
                tappedimg.setImageResource(R.drawable.lion);
                current=player.ONE;
            }
            tappedimg.animate().rotationYBy(360).setDuration(800);
            tappedimg.animate().rotationXBy(-360).setDuration(800);

            for(int[] col:winner)
            {
                count++;
                if(choice[col[0]]==choice[col[1]]&&choice[col[1]]==choice[col[2]]&&choice[col[0]]!=player.NO){

                    if(current==player.ONE) {
                        Toast.makeText(this, "LION is winner", Toast.LENGTH_SHORT).show();
                        flag=false;
                        draw=true;
                    }
                    else if(current==player.TWO) {
                        Toast.makeText(this, "TIGER is winner", Toast.LENGTH_SHORT).show();
                        flag=false;
                        draw=true;
                    }
                    restart.setVisibility(View.VISIBLE);
                }
            }
        }

        if(count==72&&!draw) {
            Toast.makeText(this, "draw", Toast.LENGTH_SHORT).show();
            restart.setVisibility(View.VISIBLE);
        }
    }

    private void reset()
    {
        for(int i=0;i<grid.getChildCount();i++)
        {
            ImageView img=(ImageView) grid.getChildAt(i);
            img.setImageDrawable(null);
            choice[i]=player.NO;
        }
        flag=true;
        draw=false;
        current=player.ONE;
        restart.setVisibility(View.GONE);
        count=0;
    }
}
