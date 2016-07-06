/*
 * Copyright (C) 2015 Square, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.shaojun.networkdemo.network.http.converter;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.shaojun.networkdemo.network.http.Response;
import com.shaojun.networkdemo.network.http.subscriber.ResultException;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Converter;

final class GsonResponseBodyConverter<T> implements Converter<ResponseBody, T> {
    private final Gson gson;
    private final TypeAdapter<T> adapter;

    GsonResponseBodyConverter(Gson gson, TypeAdapter<T> adapter) {
        this.gson = gson;
        this.adapter = adapter;
    }

    @Override
    public T convert(ResponseBody value) throws IOException {
        JsonReader jsonReader = gson.newJsonReader(value.charStream());
        T response = null;
        try {
//            JLog.d(jsonReader.toString());
            response = adapter.read(jsonReader);
        } catch (Exception e) {
            throw new ResultException(ResultException.DATA_IS_ERROR, e.getMessage());
        } finally {
            value.close();
        }

        if (response != null && ((Response) response).isSuccess()) {
            return response;
        } else {
            throw new ResultException(((Response) response).resultCode, ((Response) response).errorMsg);
        }

    }
}
