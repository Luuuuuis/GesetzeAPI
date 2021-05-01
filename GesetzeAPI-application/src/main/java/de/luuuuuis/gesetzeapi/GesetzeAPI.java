/*
 * Developed by Luuuuuis (@realluuuuuis)
 * Last modified 01.05.21, 20:27.
 * Copyright (c) 2021.
 */

/*
 * Developed by Luuuuuis (@realluuuuuis)
 * Last modified 24.04.21, 23:40.
 * Copyright (c) 2021.
 */

package de.luuuuuis.gesetzeapi;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import de.luuuuuis.gesetzeapi.model.LawBook;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@SpringBootApplication(scanBasePackages = {"de.luuuuuis.gesetzeapi"})
@EnableCaching
public class GesetzeAPI {

    public static final List<LawBook> lawBooks = new ArrayList<>();
    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public GesetzeAPI() {
        File file = new File("/prod/laws");
        Arrays.stream(Objects.requireNonNull(file.list())).filter(s -> s.endsWith(".json")).forEach(this::parseLaws);
    }

    public static void main(String[] args) {
        SpringApplication.run(GesetzeAPI.class, args);
    }


    private void parseLaws(String fileName) {
        StringBuilder stringBuilder = new StringBuilder();

        try (BufferedReader in = new BufferedReader(new FileReader("/prod/laws/" + fileName))) {
            String line;
            while ((line = in.readLine()) != null) {
                stringBuilder.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        LawBook lawBook = gson.fromJson(stringBuilder.toString(), LawBook.class);
        System.out.println("Book: " + lawBook.getName() + " " + lawBook.getTitle());
        lawBooks.add(lawBook);
    }
}
