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
        entity.addStringProperty("description");
        entity.addStringProperty("lastChapterId");
        entity.addStringProperty("lastUpdateTime");
        entity.addStringProperty("readChapterId");
        entity.addStringProperty("readChapterName");
        entity.addIntProperty("readPosition");
        entity.addStringProperty("readPageTxt");
        entity.addIntProperty("readCount");
        entity.addIntProperty("unreadCount");
        entity.addStringProperty("siteId");
        entity.addStringProperty("downloadUrl");
        entity.addStringProperty("localPath");
        entity.addIntProperty("downloadStatus");
        entity.addStringProperty("author");
        entity.addStringProperty("category");
    }

}
