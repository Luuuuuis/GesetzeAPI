/*
 * Developed by Luuuuuis (@realluuuuuis)
 * Last modified 08.02.22, 19:09.
 * Copyright (c) 2022.
 */

package de.luuuuuis.gesetzeparser;

import de.luuuuuis.gesetzeparser.model.Law;
import de.luuuuuis.gesetzeparser.model.LawBook;
import de.luuuuuis.gesetzeparser.model.SubPage;
import lombok.Cleanup;
import lombok.SneakyThrows;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.FileWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class LawBookParser {

    @SneakyThrows
    public LawBookParser(GesetzeParser gesetzeParser, SubPage subPage) {
        System.out.println("Parsing lawbook: " + subPage.toString());
        Document document = gesetzeParser.getDocument(subPage.getUrl());

        List<Law> laws = new ArrayList<>();

        for (Element table : document.select("table")) {
            for (Element tr : table.select("tr")) {
                for (Element td : tr.select("td")) {
                    if (td.select("a").size() > 0 && td.selectFirst("a").text().contains("§")) {
                        Element a = td.selectFirst("a");
                        Law law = new LawParser(gesetzeParser, subPage.getUrl() + a.attr("href")).parse();
                        laws.add(law);
                    }
                }
            }
            break;
        }

        if (laws.isEmpty()) {
            System.err.println("Not creating " + subPage.getTitle() + " because it has no laws");
            return;
        }

        LawBook lawBook = new LawBook(subPage.getName(), subPage.getTitle(), laws);

        String[] arr = subPage.getUrl().split("/");
        String fName = arr[arr.length - 1].replaceAll("[\\\\/:*?\"<>|]", "_").trim() + ".json";

        @Cleanup FileWriter fileWriter = new FileWriter("laws/" + fName, StandardCharsets.UTF_8);
        fileWriter.write(GesetzeParser.GSON.toJson(lawBook));
        fileWriter.flush();

        System.out.println("Writing " + subPage.getTitle() + " with " + laws.size() + " laws.");
    }
}
