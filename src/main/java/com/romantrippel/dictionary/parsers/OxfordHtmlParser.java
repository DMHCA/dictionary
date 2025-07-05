package com.romantrippel.dictionary.parsers;

import com.romantrippel.dictionary.dto.OxfordWordDto;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class OxfordHtmlParser {

    private static final Logger logger = LoggerFactory.getLogger(OxfordHtmlParser.class);

    private static final String URL = "https://www.oxfordlearnersdictionaries.com/wordlists/oxford3000-5000";

    public List<OxfordWordDto> parse() {
        List<OxfordWordDto> results = new ArrayList<>();

        try {
            logger.info("Connecting to Oxford wordlist page: {}", URL);

            Document doc = Jsoup.connect(URL)
                    .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 " +
                            "(KHTML, like Gecko) Chrome/115.0 Safari/537.36")
                    .timeout(10_000)
                    .followRedirects(true)
                    .get();

            Elements items = doc.select("ul.top-g > li");

            logger.info("Found {} words on page", items.size());

            for (Element li : items) {
                String word = li.attr("data-hw");
                String level = li.attr("data-ox5000");
                Element posElem = li.selectFirst("span.pos");
                String pos = posElem != null ? posElem.text() : "";

                Element usDiv = li.selectFirst("div.pron-us");
                String audio = usDiv != null ? usDiv.attr("data-src-mp3") : null;

                results.add(new OxfordWordDto(word, level, pos, audio));
            }

        } catch (IOException e) {
            logger.error("Failed to parse Oxford wordlist", e);
            throw new RuntimeException("Error parsing Oxford wordlist", e);
        }

        return results;
    }

}