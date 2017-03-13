package com.example;

import de.greenrobot.daogenerator.DaoGenerator;
import de.greenrobot.daogenerator.Entity;
import de.greenrobot.daogenerator.Schema;

public class DaoMaker {

    public static void main(String[] args) {
        Schema schema = new Schema(1, "com.max.novelreader.bean");
        addBook(schema);
        schema.setDefaultJavaPackageDao("com.max.novelreader.dao");

        try {
            new DaoGenerator().generateAll(schema, "app/src/main/java-gen");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void addBook(Schema schema) {
        Entity entity = schema.addEntity("Book");
        entity.addIdProperty();
        entity.addStringProperty("novelId");
        entity.addStringProperty("name");
        entity.addStringProperty("coverUrl");
        entity.addLongProperty("postdate");
        entity.addStringProperty("description");
        entity.addIntProperty("isover");
        entity.addStringProperty("lastChapterId");
        entity.addStringProperty("lastChapterName");
        entity.addStringProperty("lastChapterSiteId");
        entity.addStringProperty("lastChapterUrl");
        entity.addStringProperty("lastUpdateTime");
        entity.addStringProperty("readChapterId");
        entity.addStringProperty("readChapterUrl");
        entity.addStringProperty("readChaterSiteId");
        entity.addStringProperty("readChapterName");
        entity.addIntProperty("readPosition");
        entity.addStringProperty("readPageTxt");
        entity.addIntProperty("readCount");
        entity.addIntProperty("unreadCount");
        entity.addIntProperty("allvisit");
        entity.addStringProperty("siteId");
        entity.addStringProperty("siteName");
        entity.addStringProperty("siteUrl");
        entity.addStringProperty("siteKey");
        entity.addStringProperty("orderId");
        entity.addStringProperty("siteHost");
        entity.addStringProperty("downloadUrl");
        entity.addStringProperty("localPath");
        entity.addIntProperty("downloadStatus");
        entity.addStringProperty("author");
        entity.addStringProperty("authorUrl");
        entity.addStringProperty("categoryId");
        entity.addStringProperty("categoryName");
        entity.addStringProperty("categoryUrl");
        entity.addStringProperty("urlFirst");
        entity.addStringProperty("urlInfo");
        entity.addStringProperty("urlDir");
        entity.addStringProperty("urlDown");
        entity.addStringProperty("urlReadEnd");
        entity.addStringProperty("urlChapterList");
        entity.addStringProperty("urlAddMark");
        entity.addStringProperty("urlVote");
        entity.addStringProperty("urlComment");
    }

}
