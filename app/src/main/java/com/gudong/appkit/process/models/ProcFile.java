/*
 * Copyright (C) 2015. Jared Rummler <jared.rummler@gmail.com>
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
 *
 */
package com.gudong.appkit.process.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by GuDong on 12/10/15 15:17.
 * Contact with 1252768410@qq.com.
 */
public class ProcFile extends File implements Parcelable {
    public final String content;

    protected ProcFile(String path)throws IOException{
        super(path);
        content = readFile(path);
    }

    protected ProcFile(Parcel in){
        super(in.readString());
        this.content = in.readString();
    }

    protected static String readFile(String path) throws IOException{
        BufferedReader reader = null;
        try {
            StringBuilder output = new StringBuilder();
            reader = new BufferedReader(new FileReader(path));
            for(String line = reader.readLine(),newLine = "";line != null;line = reader.readLine()){
                output.append(newLine).append(line);
                newLine = "\n";
            }
            //Logger.i(path + "--> "+output.toString());
            return output.toString();
        }finally {
         if(reader != null){
            reader.close();
         }
        }
    }

    @Override
    public long length() {
        return content.length();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(getAbsolutePath());
        dest.writeString(this.content);
    }

}