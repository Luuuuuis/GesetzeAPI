/*
 * Developed by Luuuuuis (@realluuuuuis)
 * Last modified 08.02.22, 18:28.
 * Copyright (c) 2022.
 */

package de.luuuuuis.gesetzeparser;

import de.luuuuuis.gesetzeparser.model.Law;
import lombok.RequiredArgsConstructor;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.util.StringJoiner;

@RequiredArgsConstructor
public class LawParser {

    private final GesetzeParser gesetzeParser;
    private final String url;

    public Law parse() {
        Document document = gesetzeParser.getDocument(url);
        Law.LawBuilder lawBuilder = Law.builder().url(url);

        for (Element span : document.select("span")) {
            if (span.hasAttr("class") && span.attr("class").equals("jnenbez")) {
                lawBuilder.paragraph(span.text());
            } else if (span.hasAttr("class") && span.attr("class").equals("jnentitel")) {
                lawBuilder.title(span.text());
            }
        }

        StringJoiner sj = new StringJoiner("\n");
        for (Element div : document.select("div")) {
            if (div.hasAttr("class") && div.attr("class").equals("jurAbsatz")) {
                sj.add(div.text());
            }
        }

        lawBuilder.text(sj.toString());

        return lawBuilder.build();
    }
}
