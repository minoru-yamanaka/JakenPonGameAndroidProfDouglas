package com.example.jogo;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    //declaraçao dos ids
    ImageView jogador1, jogador2;
    ImageButton btPedra, btTesoura, btPapel;
    int jogada1 = 0;
    int jogada2 = 0;



    //criaçao de animação e som

    Animation some;
    Animation aparece;


    MediaPlayer mediaPlayer;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mediaPlayer = MediaPlayer.create(MainActivity.this,R.raw.alex_play);
        some = new AlphaAnimation(1,0);
        aparece = new AlphaAnimation(0,1);

        //duração de tempo
        some.setDuration (1500);
        aparece.setDuration(100);


        //chamar ids
        jogador1 = findViewById(R.id.jogador1);
        jogador2 = findViewById(R.id.jogador2);
        btPapel = findViewById(R.id.btPapel);
        btPedra = findViewById(R.id.btPedra);
        btTesoura = findViewById(R.id.btTesoura);
        some.setAnimationListener(new Animation.AnimationListener() {

            @Override
            public void onAnimationStart(Animation animation) {
                jogador2.setVisibility(View.VISIBLE);
                jogador2.startAnimation(aparece);
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                jogador2.setVisibility(View.INVISIBLE);
                jogador2.startAnimation(aparece);

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });


        aparece.setAnimationListener(new Animation.AnimationListener() {
                                         @Override
                                         public void onAnimationStart(Animation animation) {
                                             sorteioJogoInimigo();
                                             jogador2.setVisibility(View.INVISIBLE);
                                         }

                                         @Override
                                         public void onAnimationEnd(Animation animation) {
                                             verificaJogada();
                                             jogador2.setVisibility(View.VISIBLE);
                                         }

                                         @Override
                                         public void onAnimationRepeat(Animation animation) {

                                         }
                                     });

    }


    //declarações de funçao
    public void tocouBotao(View view){
        tocasom();
        jogador1.setScaleX(-1);
        if(view.getId()== btPedra.getId()){
            jogada1 = 1;
            jogador1.setImageResource(R.drawable.pedra);
        }
        if(view.getId()== btPapel.getId()){
            jogada1 = 2;
            jogador1.setImageResource(R.drawable.papel);
        }
        if(view.getId()== btTesoura.getId()){
            jogada1 = 3;
            jogador1.setImageResource(R.drawable.tesoura);
        }

        jogador2.setImageResource(R.drawable.interrogacao);
        jogador2.startAnimation(some);


    }
    public void sorteioJogoInimigo(){
        Random r = new Random();
        int numRandon = r.nextInt(3);
        switch (numRandon){
            case 0:
                jogador2.setImageResource(R.drawable.pedra);
                jogada2 = 1;
                break;
            case 1:
                jogador2.setImageResource(R.drawable.papel);
                jogada2 = 2;
                break;
            case 2:
                jogador2.setImageResource(R.drawable.tesoura);
                jogada2 = 3;
                break;
            default:
        }

    }
    public void verificaJogada(){
        if (jogada1 == jogada2){
            Toast.makeText(this, "Empate", Toast.LENGTH_SHORT).show();
        }
        if ((jogada1 == 1 && jogada2 ==3) || (jogada1 == 2 && jogada2==1)||(jogada1==3 && jogada2==2)){
            Toast.makeText(this, "Ganhei", Toast.LENGTH_SHORT).show();
        }
        if ((jogada2==1 && jogada1==3)||(jogada2==2 && jogada1==1)||(jogada2==3 && jogada1==2)){
            Toast.makeText(this,"Perdi",Toast.LENGTH_SHORT).show();
        }

    }

    public void tocasom(){
        if (mediaPlayer != null ){
            mediaPlayer.start();
        }
    }// fim tocasom

    @Override
    protected void onDestroy(){
        if (mediaPlayer != null){
            mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer = null;
        }// fim de if
        super.onDestroy();
    }// fim de onDestroy




}// fim de