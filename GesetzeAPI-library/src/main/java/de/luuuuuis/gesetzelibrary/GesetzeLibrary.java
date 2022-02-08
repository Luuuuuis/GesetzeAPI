/*
 * Developed by Luuuuuis (@realluuuuuis)
 * Last modified 08.02.22, 17:54.
 * Copyright (c) 2022.
 */

package de.luuuuuis.gesetzelibrary;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import de.luuuuuis.gesetzelibrary.misc.DefaultInterceptor;
import de.luuuuuis.gesetzelibrary.model.Law;
import de.luuuuuis.gesetzelibrary.model.LawBook;
import lombok.Builder;
import lombok.SneakyThrows;
import okhttp3.*;
import org.jetbrains.annotations.Nullable;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

/**
 * The german law maven library by @Luuuuis
 */
public class GesetzeLibrary {

    private final OkHttpClient client;
    private final String baseUrl;
    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    /**
     * Creates an instance of {@link GesetzeLibrary}.
     * Invoke with {@code GesetzeLibrary.builder().build();}
     *
     * @param baseUrl     Your application server. Leave empty to fall back to https://gesetzeapi.luis.team
     * @param enableCache turn on or off request caching. By default off.
     * @param cacheSize   modify the cache size. Recommended are 10MiB (10 * 1024 * 1024)
     */
    @Builder
    private GesetzeLibrary(@Nullable String baseUrl, boolean enableCache, int cacheSize) {
        this.baseUrl = Optional.ofNullable(baseUrl).orElse("https://gesetzeapi.luis.team");

        Cache cache = null;
        if (enableCache) {
            File cacheDir = new File(System.getProperty("java.io.tmpdir"));
            cache = new Cache(cacheDir, cacheSize);
        }

        client = new OkHttpClient.Builder()
                .cache(cache)
                .readTimeout(3, TimeUnit.SECONDS)
                .connectTimeout(3, TimeUnit.SECONDS)
                .retryOnConnectionFailure(true)
                .addInterceptor(new DefaultInterceptor())
                .build();
    }

    @SneakyThrows
    private Response call(String path) {
        Request request = new Request.Builder().url(this.baseUrl + path).build();
        Call call = client.newCall(request);

        return call.execute();
    }


    /**
     * Get an overview of all law books available
     * Watch out: The laws are null and not available at this endpoint.
     * Query for specific books with {@code getLawBook(String lawBook)}.
     *
     * @return A list of all available law books {@link LawBook}
     */
    @SneakyThrows
    public List<LawBook> getLawBooks() {
        Response response = call("/books/");
        return Arrays.asList(gson.fromJson(Objects.requireNonNull(response.body()).string(), LawBook[].class));
    }

    /**
     * Get a LawBook object with a list of all laws
     *
     * @param lawBook The law book you want to query for
     * @return A law book {@link LawBook}
     */
    @SneakyThrows
    public LawBook getLawBook(String lawBook) {
        Response response = call("/books/" + lawBook);

        return gson.fromJson(Objects.requireNonNull(response.body()).string(), LawBook.class);
    }

    /**
     * Find a specific law
     *
     * @param lawBook   The law book you want to query for
     * @param paragraph The paragraph your want to query for
     * @return A Law object with text, title, url, etc. {@link Law}
     */
    @SneakyThrows
    public Law getLaw(String lawBook, String paragraph) {
        Response response = call("/books/" + lawBook + "/" + paragraph);

        return gson.fromJson(Objects.requireNonNull(response.body()).string(), Law.class);
    }

}
