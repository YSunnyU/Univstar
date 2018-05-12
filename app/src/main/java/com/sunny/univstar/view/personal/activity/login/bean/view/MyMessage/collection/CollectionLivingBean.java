package com.sunny.univstar.view.personal.activity.login.bean.view.MyMessage.collection;

import java.util.List;

/**
 * Created by 张玗 on 2018/5/11.
 */

public class CollectionLivingBean {


    /**
     * code : 0
     * message : 成功
     * data : {"pageNum":1,"pageSize":20,"size":3,"startRow":1,"endRow":3,"total":3,"pages":1,"list":[{"coverImg":"http://qiniu.5roo.com/4a8b2f36-1fff-4514-8798-33ebb2908c9e.jpg","endDate":1525782300000,"subscribe":3,"replay":0,"title":"第一节 怎样做一名合格的演员","type":"讲堂","isSubscribe":0,"realname":"陈雅韩","majorIds":"6","teacherId":459,"subscribeNum":43,"price":0,"nickname":"陈雅韩","id":86,"userType":3,"startDate":1525780800000,"liveStatus":2},{"coverImg":"http://qiniu.5roo.com/8ba2e389-6b45-499e-9a2c-ce436aafdc15.jpg","endDate":1523280792000,"subscribe":5,"replay":0.1,"title":"\u201c演员初体验\u2018\u2019表演的特点与要求  李苒苒/温雪竹 分享交流课 精彩片段","type":"讲堂","isSubscribe":0,"realname":"李苒苒","majorIds":"6","teacherId":262,"subscribeNum":100,"price":0.1,"nickname":"李苒苒","id":76,"userType":4,"startDate":1523280790000,"liveStatus":2},{"coverImg":"http://qiniu.5roo.com/8f3e8c1f-6355-4e92-a60e-c64bc8dc4edd.jpg","endDate":1523281766000,"subscribe":3,"replay":9.9,"title":"第五节 我对你们的希望","type":"讲堂","isSubscribe":0,"realname":"李苒苒","majorIds":"6","teacherId":262,"subscribeNum":100,"price":9.9,"nickname":"李苒苒","id":81,"userType":4,"startDate":1523281764000,"liveStatus":2}],"prePage":0,"nextPage":0,"isFirstPage":true,"isLastPage":true,"hasPreviousPage":false,"hasNextPage":false,"navigatePages":8,"navigatepageNums":[1],"navigateFirstPage":1,"navigateLastPage":1,"firstPage":1,"lastPage":1}
     */

    private int code;
    private String message;
    private DataBean data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * pageNum : 1
         * pageSize : 20
         * size : 3
         * startRow : 1
         * endRow : 3
         * total : 3
         * pages : 1
         * list : [{"coverImg":"http://qiniu.5roo.com/4a8b2f36-1fff-4514-8798-33ebb2908c9e.jpg","endDate":1525782300000,"subscribe":3,"replay":0,"title":"第一节 怎样做一名合格的演员","type":"讲堂","isSubscribe":0,"realname":"陈雅韩","majorIds":"6","teacherId":459,"subscribeNum":43,"price":0,"nickname":"陈雅韩","id":86,"userType":3,"startDate":1525780800000,"liveStatus":2},{"coverImg":"http://qiniu.5roo.com/8ba2e389-6b45-499e-9a2c-ce436aafdc15.jpg","endDate":1523280792000,"subscribe":5,"replay":0.1,"title":"\u201c演员初体验\u2018\u2019表演的特点与要求  李苒苒/温雪竹 分享交流课 精彩片段","type":"讲堂","isSubscribe":0,"realname":"李苒苒","majorIds":"6","teacherId":262,"subscribeNum":100,"price":0.1,"nickname":"李苒苒","id":76,"userType":4,"startDate":1523280790000,"liveStatus":2},{"coverImg":"http://qiniu.5roo.com/8f3e8c1f-6355-4e92-a60e-c64bc8dc4edd.jpg","endDate":1523281766000,"subscribe":3,"replay":9.9,"title":"第五节 我对你们的希望","type":"讲堂","isSubscribe":0,"realname":"李苒苒","majorIds":"6","teacherId":262,"subscribeNum":100,"price":9.9,"nickname":"李苒苒","id":81,"userType":4,"startDate":1523281764000,"liveStatus":2}]
         * prePage : 0
         * nextPage : 0
         * isFirstPage : true
         * isLastPage : true
         * hasPreviousPage : false
         * hasNextPage : false
         * navigatePages : 8
         * navigatepageNums : [1]
         * navigateFirstPage : 1
         * navigateLastPage : 1
         * firstPage : 1
         * lastPage : 1
         */

        private int pageNum;
        private int pageSize;
        private int size;
        private int startRow;
        private int endRow;
        private int total;
        private int pages;
        private int prePage;
        private int nextPage;
        private boolean isFirstPage;
        private boolean isLastPage;
        private boolean hasPreviousPage;
        private boolean hasNextPage;
        private int navigatePages;
        private int navigateFirstPage;
        private int navigateLastPage;
        private int firstPage;
        private int lastPage;
        private List<ListBean> list;
        private List<Integer> navigatepageNums;

        public int getPageNum() {
            return pageNum;
        }

        public void setPageNum(int pageNum) {
            this.pageNum = pageNum;
        }

        public int getPageSize() {
            return pageSize;
        }

        public void setPageSize(int pageSize) {
            this.pageSize = pageSize;
        }

        public int getSize() {
            return size;
        }

        public void setSize(int size) {
            this.size = size;
        }

        public int getStartRow() {
            return startRow;
        }

        public void setStartRow(int startRow) {
            this.startRow = startRow;
        }

        public int getEndRow() {
            return endRow;
        }

        public void setEndRow(int endRow) {
            this.endRow = endRow;
        }

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public int getPages() {
            return pages;
        }

        public void setPages(int pages) {
            this.pages = pages;
        }

        public int getPrePage() {
            return prePage;
        }

        public void setPrePage(int prePage) {
            this.prePage = prePage;
        }

        public int getNextPage() {
            return nextPage;
        }

        public void setNextPage(int nextPage) {
            this.nextPage = nextPage;
        }

        public boolean isIsFirstPage() {
            return isFirstPage;
        }

        public void setIsFirstPage(boolean isFirstPage) {
            this.isFirstPage = isFirstPage;
        }

        public boolean isIsLastPage() {
            return isLastPage;
        }

        public void setIsLastPage(boolean isLastPage) {
            this.isLastPage = isLastPage;
        }

        public boolean isHasPreviousPage() {
            return hasPreviousPage;
        }

        public void setHasPreviousPage(boolean hasPreviousPage) {
            this.hasPreviousPage = hasPreviousPage;
        }

        public boolean isHasNextPage() {
            return hasNextPage;
        }

        public void setHasNextPage(boolean hasNextPage) {
            this.hasNextPage = hasNextPage;
        }

        public int getNavigatePages() {
            return navigatePages;
        }

        public void setNavigatePages(int navigatePages) {
            this.navigatePages = navigatePages;
        }

        public int getNavigateFirstPage() {
            return navigateFirstPage;
        }

        public void setNavigateFirstPage(int navigateFirstPage) {
            this.navigateFirstPage = navigateFirstPage;
        }

        public int getNavigateLastPage() {
            return navigateLastPage;
        }

        public void setNavigateLastPage(int navigateLastPage) {
            this.navigateLastPage = navigateLastPage;
        }

        public int getFirstPage() {
            return firstPage;
        }

        public void setFirstPage(int firstPage) {
            this.firstPage = firstPage;
        }

        public int getLastPage() {
            return lastPage;
        }

        public void setLastPage(int lastPage) {
            this.lastPage = lastPage;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public List<Integer> getNavigatepageNums() {
            return navigatepageNums;
        }

        public void setNavigatepageNums(List<Integer> navigatepageNums) {
            this.navigatepageNums = navigatepageNums;
        }

        public static class ListBean {
            /**
             * coverImg : http://qiniu.5roo.com/4a8b2f36-1fff-4514-8798-33ebb2908c9e.jpg
             * endDate : 1525782300000
             * subscribe : 3
             * replay : 0
             * title : 第一节 怎样做一名合格的演员
             * type : 讲堂
             * isSubscribe : 0
             * realname : 陈雅韩
             * majorIds : 6
             * teacherId : 459
             * subscribeNum : 43
             * price : 0
             * nickname : 陈雅韩
             * id : 86
             * userType : 3
             * startDate : 1525780800000
             * liveStatus : 2
             */

            private String coverImg;
            private long endDate;
            private int subscribe;
            private double replay;
            private String title;
            private String type;
            private int isSubscribe;
            private String realname;
            private String majorIds;
            private int teacherId;
            private int subscribeNum;
            private double price;
            private String nickname;
            private int id;
            private int userType;
            private long startDate;
            private int liveStatus;

            public String getCoverImg() {
                return coverImg;
            }

            public void setCoverImg(String coverImg) {
                this.coverImg = coverImg;
            }

            public long getEndDate() {
                return endDate;
            }

            public void setEndDate(long endDate) {
                this.endDate = endDate;
            }

            public int getSubscribe() {
                return subscribe;
            }

            public void setSubscribe(int subscribe) {
                this.subscribe = subscribe;
            }

            public double getReplay() {
                return replay;
            }

            public void setReplay(double replay) {
                this.replay = replay;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public int getIsSubscribe() {
                return isSubscribe;
            }

            public void setIsSubscribe(int isSubscribe) {
                this.isSubscribe = isSubscribe;
            }

            public String getRealname() {
                return realname;
            }

            public void setRealname(String realname) {
                this.realname = realname;
            }

            public String getMajorIds() {
                return majorIds;
            }

            public void setMajorIds(String majorIds) {
                this.majorIds = majorIds;
            }

            public int getTeacherId() {
                return teacherId;
            }

            public void setTeacherId(int teacherId) {
                this.teacherId = teacherId;
            }

            public int getSubscribeNum() {
                return subscribeNum;
            }

            public void setSubscribeNum(int subscribeNum) {
                this.subscribeNum = subscribeNum;
            }

            public double getPrice() {
                return price;
            }

            public void setPrice(double price) {
                this.price = price;
            }

            public String getNickname() {
                return nickname;
            }

            public void setNickname(String nickname) {
                this.nickname = nickname;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getUserType() {
                return userType;
            }

            public void setUserType(int userType) {
                this.userType = userType;
            }

            public long getStartDate() {
                return startDate;
            }

            public void setStartDate(long startDate) {
                this.startDate = startDate;
            }

            public int getLiveStatus() {
                return liveStatus;
            }

            public void setLiveStatus(int liveStatus) {
                this.liveStatus = liveStatus;
            }
        }
    }
}
