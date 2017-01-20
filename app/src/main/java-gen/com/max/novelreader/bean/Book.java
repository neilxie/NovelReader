package com.max.novelreader.bean;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT. Enable "keep" sections if you want to edit. 
/**
 * Entity mapped to table "BOOK".
 */
public class Book {

    private Long id;
    private String novelId;
    private String name;
    private String coverUrl;
    private String description;
    private String lastChapterId;
    private String lastUpdateTime;
    private String readChapterId;
    private String readChapterName;
    private Integer readPosition;
    private String readPageTxt;
    private Integer readCount;
    private Integer unreadCount;
    private String siteId;
    private String downloadUrl;
    private String localPath;
    private Integer downloadStatus;
    private String author;
    private String category;

    public Book() {
    }

    public Book(Long id) {
        this.id = id;
    }

    public Book(Long id, String novelId, String name, String coverUrl, String description, String lastChapterId, String lastUpdateTime, String readChapterId, String readChapterName, Integer readPosition, String readPageTxt, Integer readCount, Integer unreadCount, String siteId, String downloadUrl, String localPath, Integer downloadStatus, String author, String category) {
        this.id = id;
        this.novelId = novelId;
        this.name = name;
        this.coverUrl = coverUrl;
        this.description = description;
        this.lastChapterId = lastChapterId;
        this.lastUpdateTime = lastUpdateTime;
        this.readChapterId = readChapterId;
        this.readChapterName = readChapterName;
        this.readPosition = readPosition;
        this.readPageTxt = readPageTxt;
        this.readCount = readCount;
        this.unreadCount = unreadCount;
        this.siteId = siteId;
        this.downloadUrl = downloadUrl;
        this.localPath = localPath;
        this.downloadStatus = downloadStatus;
        this.author = author;
        this.category = category;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNovelId() {
        return novelId;
    }

    public void setNovelId(String novelId) {
        this.novelId = novelId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCoverUrl() {
        return coverUrl;
    }

    public void setCoverUrl(String coverUrl) {
        this.coverUrl = coverUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLastChapterId() {
        return lastChapterId;
    }

    public void setLastChapterId(String lastChapterId) {
        this.lastChapterId = lastChapterId;
    }

    public String getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(String lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    public String getReadChapterId() {
        return readChapterId;
    }

    public void setReadChapterId(String readChapterId) {
        this.readChapterId = readChapterId;
    }

    public String getReadChapterName() {
        return readChapterName;
    }

    public void setReadChapterName(String readChapterName) {
        this.readChapterName = readChapterName;
    }

    public Integer getReadPosition() {
        return readPosition;
    }

    public void setReadPosition(Integer readPosition) {
        this.readPosition = readPosition;
    }

    public String getReadPageTxt() {
        return readPageTxt;
    }

    public void setReadPageTxt(String readPageTxt) {
        this.readPageTxt = readPageTxt;
    }

    public Integer getReadCount() {
        return readCount;
    }

    public void setReadCount(Integer readCount) {
        this.readCount = readCount;
    }

    public Integer getUnreadCount() {
        return unreadCount;
    }

    public void setUnreadCount(Integer unreadCount) {
        this.unreadCount = unreadCount;
    }

    public String getSiteId() {
        return siteId;
    }

    public void setSiteId(String siteId) {
        this.siteId = siteId;
    }

    public String getDownloadUrl() {
        return downloadUrl;
    }

    public void setDownloadUrl(String downloadUrl) {
        this.downloadUrl = downloadUrl;
    }

    public String getLocalPath() {
        return localPath;
    }

    public void setLocalPath(String localPath) {
        this.localPath = localPath;
    }

    public Integer getDownloadStatus() {
        return downloadStatus;
    }

    public void setDownloadStatus(Integer downloadStatus) {
        this.downloadStatus = downloadStatus;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

}