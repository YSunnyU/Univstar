package com.sunny.univstar.model.entity;

/**
 * Created by DELL on 2018/5/11.
 */

public class ReplyListSendEntity {

    /**
     * code : 0
     * message : 成功
     * data : {"photo":"http://qiniu.5roo.com/6e8d4407-4ba8-426e-9717-53732acf8119.jpg","praiseNum":0,"userId":874,"content":"艺术气息来源于生活，以及对生活的追求","realname":"","replyName":"我是五六七八九","nickname":"陈希丶","replyId":784,"replyNum":0,"isPraise":0,"id":557,"userType":1,"createDate":1526035462000}
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
         * photo : http://qiniu.5roo.com/6e8d4407-4ba8-426e-9717-53732acf8119.jpg
         * praiseNum : 0
         * userId : 874
         * content : 艺术气息来源于生活，以及对生活的追求
         * realname :
         * replyName : 我是五六七八九
         * nickname : 陈希丶
         * replyId : 784
         * replyNum : 0
         * isPraise : 0
         * id : 557
         * userType : 1
         * createDate : 1526035462000
         */

        private String photo;
        private int praiseNum;
        private int userId;
        private String content;
        private String realname;
        private String replyName;
        private String nickname;
        private int replyId;
        private int replyNum;
        private int isPraise;
        private int id;
        private int userType;
        private long createDate;

        public String getPhoto() {
            return photo;
        }

        public void setPhoto(String photo) {
            this.photo = photo;
        }

        public int getPraiseNum() {
            return praiseNum;
        }

        public void setPraiseNum(int praiseNum) {
            this.praiseNum = praiseNum;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getRealname() {
            return realname;
        }

        public void setRealname(String realname) {
            this.realname = realname;
        }

        public String getReplyName() {
            return replyName;
        }

        public void setReplyName(String replyName) {
            this.replyName = replyName;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public int getReplyId() {
            return replyId;
        }

        public void setReplyId(int replyId) {
            this.replyId = replyId;
        }

        public int getReplyNum() {
            return replyNum;
        }

        public void setReplyNum(int replyNum) {
            this.replyNum = replyNum;
        }

        public int getIsPraise() {
            return isPraise;
        }

        public void setIsPraise(int isPraise) {
            this.isPraise = isPraise;
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

        public long getCreateDate() {
            return createDate;
        }

        public void setCreateDate(long createDate) {
            this.createDate = createDate;
        }
    }
}
