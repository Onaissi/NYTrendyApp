package com.onaissi.nytrendy.models;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.net.URL;

public class Article implements Parcelable {

    private @NonNull String title;
    private @NonNull String section;
    private @NonNull String byLine;
    private @NonNull String abstractDescription;
    private @NonNull URL url;
    private @NonNull String publishDate;
    private @NonNull String source;
    private @Nullable String imageUrl;



    public Article(@NonNull String title, @Nullable String abstractDescription, @NonNull String section, @NonNull String byLine, @NonNull URL url, @NonNull String publishDate, @NonNull String source, @Nullable String imageUrl) {
        this.title = title;
        this.section = section;
        this.byLine = byLine;
        this.url = url;
        this.publishDate = publishDate;
        this.source = source;
        this.imageUrl = imageUrl;
        this.abstractDescription = abstractDescription;

    }

    protected Article(Parcel in) {
        title = in.readString();
        section = in.readString();
        byLine = in.readString();
        abstractDescription = in.readString();
        publishDate = in.readString();
        source = in.readString();
        imageUrl = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString(section);
        dest.writeString(byLine);
        dest.writeString(abstractDescription);
        dest.writeString(publishDate);
        dest.writeString(source);
        dest.writeString(imageUrl);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Article> CREATOR = new Creator<Article>() {
        @Override
        public Article createFromParcel(Parcel in) {
            return new Article(in);
        }

        @Override
        public Article[] newArray(int size) {
            return new Article[size];
        }
    };

    @Nullable
    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(@Nullable String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @NonNull
    public String getTitle() {
        return title;
    }

    public void setTitle(@NonNull String title) {
        this.title = title;
    }

    @NonNull
    public String getSection() {
        return section;
    }

    public void setSection(@NonNull String section) {
        this.section = section;
    }

    @NonNull
    public String getByLine() {
        return byLine;
    }

    public void setByLine(@NonNull String byLine) {
        this.byLine = byLine;
    }

    @NonNull
    public URL getUrl() {
        return url;
    }

    public void setUrl(@NonNull URL url) {
        this.url = url;
    }

    @NonNull
    public String getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(@NonNull String publishDate) {
        this.publishDate = publishDate;
    }

    @NonNull
    public String getSource() {
        return source;
    }

    public void setSource(@NonNull String source) {
        this.source = source;
    }

    @NonNull
    public String getAbstractDescription() {
        return abstractDescription;
    }

    public void setAbstractDescription(@NonNull String abstractDescription) {
        this.abstractDescription = abstractDescription;
    }


    public String description(){
        String desc = this.section + "\n";
        desc += this.source + "\n";
        desc += this.byLine + "\n";;
        desc += this.publishDate;
        return desc;
    }
}

