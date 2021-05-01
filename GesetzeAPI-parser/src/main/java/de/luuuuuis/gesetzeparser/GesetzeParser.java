/*
 * Developed by Luuuuuis (@realluuuuuis)
 * Last modified 01.05.21, 21:18.
 * Copyright (c) 2021.
 */

/*
 * Developed by Luuuuuis (@realluuuuuis)
 * Last modified 01.05.21, 17:51.
 * Copyright (c) 2021.
 */

package de.luuuuuis.gesetzeparser;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import de.luuuuuis.gesetzeparser.model.SubPage;
import lombok.SneakyThrows;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class GesetzeParser {

    public static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    @SneakyThrows
    public GesetzeParser() {
        String BASE_URL = "https://www.gesetze-im-internet.de/";
        Document overview = getDocument(BASE_URL + "aktuell.html");

        List<String> pages = new ArrayList<>();
        for (Element a : overview.select("a")) {
            if (a.attr("class").equals("alphabet")) {
                pages.add(a.attr("href").replace("./", ""));
            }
        }

        for (String page : pages) {
            Document doc = getDocument(BASE_URL + page);
            for (Element div : doc.select("div")) {
                if (div.attr("id").equals("paddingLR12")) {
                    for (Element a : div.select("a")) {
                        if (a.attr("href").endsWith("index.html")) {
                            String url = BASE_URL + a.attr("href").replace("./", "").replace("index.html", "");
                            String name = a.child(0).text();
                            String title = a.child(0).attr("title");

                            new LawBookParser(this, new SubPage(url, name, title));
                            Thread.sleep(500);
                        }
                    }
                    break;
                }
            }

            Thread.sleep(500);
        }
    }

    public static void main(String[] args) {
        // create dir for laws
        File file = new File("laws/");
        file.mkdir();

        new GesetzeParser();
    }

    @SneakyThrows
    public Document getDocument(String url) {
//        @Cleanup InputStream inputStream = new URL(url).openStream();
//        return Jsoup.parse(inputStream, "UTF-8", url);
        return Jsoup.connect(url).get();
    }

}
