/*
 *  Copyright 2011 Yuri Kanivets
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package com.good.diaodiaode.tebiediao;

import android.content.Context;

import java.util.ArrayList;

/**
 * The simple Array wheel adapter
 *
 * @param <T> the element type
 */
public class ArrayListWheelAdapter<T> extends AbstractWheelTextAdapter {

    // items
    private ArrayList<T> items;

    /**
     * Constructor
     *
     * @param context the current context
     * @param items   the items
     */
    public ArrayListWheelAdapter(Context context, ArrayList<T> items) {
        super(context);
        this.items = items;
    }

    @Override
    public CharSequence getItemText(int index) {
        if (index >= 0 && index < items.size()) {
            T item = items.get(index);
            if (item instanceof LinkageDataBean && item != null) {
                return ((LinkageDataBean) item).getName();
            }
            return null;
        }
        return null;
    }

    @Override
    public int getItemsCount() {
        return items.size();
    }

    public ArrayList<T> getItems() {
        return items;
    }

    public void setItems(ArrayList<T> items) {
        this.items = items;
    }
}
