package ru.geekbrains.android2.bibli_lesson3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class MainActivity extends AppCompatActivity {

    final String FILENAME = "\\drawable-v24\\sun.jpg";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Observable<Byte> observable = Observable.create(new ObservableOnSubscribe<Byte>(){
            @Override
            public void subscribe (ObservableEmitter<Byte> emitter) throws IOException {
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(openFileInput(FILENAME)));
                emitter.onNext(bufferedReader.readLine());
            }
        });

        Observer<Byte> observer = new Observer<Byte> () {
            @Override
            public void onSubscribe(Disposable d) {
            }

            @Override
            public void onNext(Byte b) {
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(b));
            }

            @Override
            public void onError(Throwable e) {
            }

            @Override
            public void onComplete() {
                convertJpgToPng(bufferedWriter);
            }
        };

        observable.subscribe(observer);

        public void convertJpgToPng (BufferedWriter bufferedWriter) {
            // convert ()
            // save ()
        };

    }
}