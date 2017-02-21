package com.max.novelreader.bean;

import com.max.novelreader.http.HttpRequest;

import java.util.List;

/**
 * Created by Administrator on 2017/2/20.
 */

public class RecommandSameBean {


    /**
     * status : 1
     * info : success
     * data : [{"novel":{"id":"7312","name":"天域苍穹","cover":"/public/cover/05/94/95/059495318b0e2943f9257b0c12618f12.jpg"},"source":{"siteid":"7"}}]
     */

    private int status;
    private String info;
    private List<DataBean> data;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * novel : {"id":"7312","name":"天域苍穹","cover":"/public/cover/05/94/95/059495318b0e2943f9257b0c12618f12.jpg"}
         * source : {"siteid":"7"}
         */

        private NovelBean novel;
        private SourceBean source;

        public NovelBean getNovel() {
            return novel;
        }

        public void setNovel(NovelBean novel) {
            this.novel = novel;
        }

        public SourceBean getSource() {
            return source;
        }

        public void setSource(SourceBean source) {
            this.source = source;
        }

        public static class NovelBean {
            /**
             * id : 7312
             * name : 天域苍穹
             * cover : /public/cover/05/94/95/059495318b0e2943f9257b0c12618f12.jpg
             */

            private String id;
            private String name;
            private String cover;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getCover() {
                return HttpRequest.BASE_URL + cover;
            }

            public void setCover(String cover) {
                this.cover = cover;
            }
        }

        public static class SourceBean {
            /**
             * siteid : 7
             */

            private String siteid;

            public String getSiteid() {
                return siteid;
            }

            public void setSiteid(String siteid) {
                this.siteid = siteid;
            }
        }
    }
}
