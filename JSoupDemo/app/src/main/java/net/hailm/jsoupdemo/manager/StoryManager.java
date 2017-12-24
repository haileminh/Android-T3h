package net.hailm.jsoupdemo.manager;

import android.support.annotation.NonNull;

import net.hailm.jsoupdemo.model.Story;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by hai_l on 30/11/2017.
 */

public class StoryManager {
    private static final String BASE_URL = "http://truyencotich.vn/";
    private static final String CATEGORY_ONE = "truyen-dan-gian";

    public StoryManager() {

    }

    public void getOnlineStory(@NonNull final OnStoryFetchListener listener) {
        //
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    List<Story> stories = new ArrayList<>();

                    String link = BASE_URL + CATEGORY_ONE;
                    Document document = Jsoup.connect(link).get();

                    Element element = document.select("div.clear").first();

                    Elements items = element.select("article");

                    for (int i = 0; i < items.size(); i++) {
                        Element item = items.get(i);

                        String articleUrl = item.select("a").first().attr("href");
                        String title = item.select("a").first().attr("title");
                        String description = item.select("p.post-excerpt").first().text();
                        String content = "";
                        String imageUrl = item.select("img").first().attr("src");

                        stories.add(new Story(title, description, content, imageUrl, articleUrl));
                    }
                    if (listener != null) {
                        listener.onResponse(stories, null);
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                    listener.onResponse(null, e);
                }
            }
        }).start();
    }

    public void getStoriesDetail(final String articleUrl, final OnContentFetchListener onContentFetchListener) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Document document = Jsoup.connect(articleUrl).get();

                    Element element = document.select("div.entry-content").first();

                    Elements items = element.select("p");
                    StringBuilder sb = new StringBuilder();
                    for (int i = 0; i < items.size(); i++) {
                        Element item = items.get(i);

                        String content = item.select("p").first().text();

                        sb.append(content).append("\n");
                    }

                    if (onContentFetchListener != null) {
                        onContentFetchListener.onResponse(sb, null);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    onContentFetchListener.onResponse(null, e);
                }
            }
        }).start();
    }

    public interface OnContentFetchListener {
        void onResponse(StringBuilder content, Exception e);
    }

    public interface OnStoryFetchListener {
        void onResponse(List<Story> data, Exception e);
    }

}
