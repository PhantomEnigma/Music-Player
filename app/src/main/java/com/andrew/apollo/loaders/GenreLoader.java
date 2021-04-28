/*
 * Copyright (C) 2012 Andrew Neal Licensed under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with the
 * License. You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0 Unless required by applicable law
 * or agreed to in writing, software distributed under the License is
 * distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 */

package com.andrew.apollo.loaders;

import android.content.Context;
import android.database.Cursor;
import android.provider.MediaStore.Audio.Genres;

import com.andrew.apollo.model.Genre;
import com.andrew.apollo.utils.CursorCreator;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Used to query {@link Genres#EXTERNAL_CONTENT_URI} and return
 * the genres on a user's device.
 *
 * @author Andrew Neal (andrewdneal@gmail.com)
 */
public class GenreLoader extends WrappedAsyncTaskLoader<List<Genre>> {

    private static final int MAX_GENRE_COUNT = 4;

    /**
     * Constructor of <code>GenreLoader</code>
     *
     * @param context The {@link Context} to use
     */
    public GenreLoader(Context context) {
        super(context);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Genre> loadInBackground() {
        List<Genre> result = new LinkedList<>();
        // Create the Cursor
        Cursor mCursor = CursorCreator.makeGenreCursor(getContext());
        // Gather the data
        if (mCursor != null) {
            if (mCursor.moveToFirst()) {
                HashMap<String, long[]> group = new HashMap<>();
                do {
                    // get Column information
                    long id = mCursor.getLong(0);
                    String name = mCursor.getString(1);

                    // Split genre groups into single genre names
                    String[] genres = name.split("\\s*;\\s*", MAX_GENRE_COUNT);

                    // solve conflicts. add multiple genre IDs for the same genre name.
                    for (String genre : genres) {
                        long[] ids = group.get(genre);
                        if (ids == null) {
                            ids = new long[MAX_GENRE_COUNT];
                            group.put(genre, ids);
                        }
                        // insert at empty place
                        for (int i = 0; i < MAX_GENRE_COUNT; i++) {
                            if (ids[i] == 0) {
                                ids[i] = id;
                                break;
                            }
                        }
                    }
                } while (mCursor.moveToNext());

                // finish result
                for (Map.Entry<String, long[]> entry : group.entrySet()) {
                    long[] ids = entry.getValue();
                    String name = entry.getKey();
                    Genre genre = new Genre(ids, name);
                    result.add(genre);
                }
            }
            mCursor.close();
        }
        return result;
    }
}