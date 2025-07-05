package com.romantrippel.dictionary.parsers;

import com.romantrippel.dictionary.dto.OxfordWordDto;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component
public class OxfordHtmlParser {

    private static final String URL = "https://www.oxfordlearnersdictionaries.com/wordlists/oxford3000-5000";

    public List<OxfordWordDto> parse() {
        List<OxfordWordDto> results = new ArrayList<>();

        try {
            Document doc = Jsoup.connect(URL)
                    .userAgent("Mozilla/5.0")
                    .get();

            Elements items = doc.select("ul.top-g > li");

            for (Element li : items) {
                String word = li.attr("data-hw");
                String level = li.attr("data-ox5000");
                String pos = Objects.requireNonNull(li.selectFirst("span.pos")).text();

                Element usDiv = li.selectFirst("div.pron-us");
                String audio = usDiv != null ? usDiv.attr("data-src-mp3") : null;

                results.add(new OxfordWordDto(word, level, pos, audio));
            }

        } catch (IOException e) {
            throw new RuntimeException("Error parsing Oxford wordlist", e);
        }

        return results;
    }

}
