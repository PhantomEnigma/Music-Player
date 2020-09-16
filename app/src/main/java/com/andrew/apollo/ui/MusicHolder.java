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

package com.andrew.apollo.ui;

import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.andrew.apollo.R;

import java.lang.ref.WeakReference;

/**
 * Used to efficiently cache and recyle the {@link View}s used in the artist,
 * album, song, playlist, and genre adapters.
 *
 * @author Andrew Neal (andrewdneal@gmail.com)
 */
public class MusicHolder {

    /**
     * This is the overlay ontop of the background artist, playlist, or genre
     * image
     */
    public WeakReference<RelativeLayout> mOverlay;

    /**
     * This is the background artist, playlist, or genre image
     */
    public WeakReference<ImageView> mBackground;

    /**
     * This is the artist or album image
     */
    public WeakReference<ImageView> mImage;

    /**
     * This is the first line displayed in the list or grid
     * <p>
     * {@code #getView()} of a specific adapter for more detailed info
     */
    public WeakReference<TextView> mLineOne;

    /**
     * This is displayed on the right side of the first line in the list or grid
     * <p>
     * {@code #getView()} of a specific adapter for more detailed info
     */
    public WeakReference<TextView> mLineOneRight;

    /**
     * This is the second line displayed in the list or grid
     * <p>
     * {@code #getView()} of a specific adapter for more detailed info
     */
    public WeakReference<TextView> mLineTwo;

    /**
     * This is the third line displayed in the list or grid
     * <p>
     * {@code #getView()} of a specific adapter for more detailed info
     */
    public WeakReference<TextView> mLineThree;

    /**
     * Constructor of <code>ViewHolder</code>
     */
    public MusicHolder(final View view) {
        super();
        // Initialize mOverlay
        mOverlay = new WeakReference<>((RelativeLayout) view.findViewById(R.id.image_background));

        // Initialize mBackground
        mBackground = new WeakReference<>((ImageView) view.findViewById(R.id.list_item_background));

        // Initialize mImage
        mImage = new WeakReference<>((ImageView) view.findViewById(R.id.image));

        // Initialize mLineOne
        mLineOne = new WeakReference<>((TextView) view.findViewById(R.id.line_one));

        // Initialize mLineOneRight
        mLineOneRight = new WeakReference<>((TextView) view.findViewById(R.id.line_one_right));

        // Initialize mLineTwo
        mLineTwo = new WeakReference<>((TextView) view.findViewById(R.id.line_two));

        // Initialize mLineThree
        mLineThree = new WeakReference<>((TextView) view.findViewById(R.id.line_three));
    }

    /**
     *
     */
    public final static class DataHolder {

        /**
         * This is the ID of the item being loaded in the adapter
         */
        public long mItemId;

        /**
         * This is the first line displayed in the list or grid
         * <p>
         * {@code #getView()} of a specific adapter for more detailed info
         */
        public String mLineOne;

        /**
         * This is displayed on the right side of the first line in the list or grid
         * <p>
         * {@code #getView()} of a specific adapter for more detailed info
         */
        public String mLineOneRight;

        /**
         * This is the second line displayed in the list or grid
         * <p>
         * {@code #getView()} of a specific adapter for more detailed info
         */
        public String mLineTwo;

        /**
         * This is the third line displayed in the list or grid
         * <p>
         * {@code #getView()} of a specific adapter for more detailed info
         */
        public String mLineThree;

        /**
         * Constructor of <code>DataHolder</code>
         */
        public DataHolder() {
            super();
        }

    }
}
