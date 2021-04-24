/*
 * Developed by Luuuuuis (@realluuuuuis)
 * Last modified 24.04.21, 23:40.
 * Copyright (c) 2021.
 */

package de.luuuuuis.gesetzelibrary.misc;

import lombok.SneakyThrows;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

public class DefaultInterceptor implements Interceptor {

    @SneakyThrows(IOException.class)
    @NotNull
    @Override
    public Response intercept(@NotNull Chain chain) {

        Request request = chain.request()
                .newBuilder()
                .header("Content-Type", "application/json")
                .header("User-Agent", "GesetzeAPI/1.0 (" + System.getProperty("os.name") + "; " + Runtime.version() + ")")
                .build();

        return chain.proceed(request);
    }
}
