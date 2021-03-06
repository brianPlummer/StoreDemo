package com.nytimes.storedemo.rest;

import android.app.Application;
import android.content.Context;

import com.google.common.io.ByteStreams;
import com.nytimes.storedemo.R;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.inject.Inject;
import javax.inject.Singleton;

import rx.Observable;

/**
 * Created by 206847 on 12/13/15.
 */
@Singleton
public class ArticleApi {

    private Context context;

    @Inject
    public ArticleApi(Application context) {
        this.context = context;
    }

    public Observable<String> getArticles() {
        String text = getFromRawRes(R.raw.articles);
        return Observable.fromCallable(() -> text);
    }

    private String getFromRawRes(int resId) {
        String text = "";
        InputStream inputStream = context.getResources().openRawResource(resId);
        ByteArrayOutputStream bos = new ByteArrayOutputStream(1024*60);
        try {
            ByteStreams.copy(inputStream, bos);
            text = bos.toString();
        } catch (IOException e)
        {} finally {
            if (bos != null)
                try {
                    bos.close();
                } catch (IOException e) {}
            if(inputStream != null)
                try {
                    inputStream.close();
                } catch (IOException e) {}
        }
        return text;
    }

}
