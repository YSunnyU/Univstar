package com.sunny.univstar.view.message;

import java.util.List;

/**
 * Created by 张玗 on 2018/5/12.
 */

public class AboveMessageBean {

    /**
     * code : 0
     * message : 成功
     * data : [{"type":"MSG_ORDER","content":"暂无提醒","createDate":1526091478103,"isRead":0},{"type":"MSG_PRAISE","content":"Sunny赞了你的艺考圈作品","createDate":1526090856297,"isRead":0},{"type":"MSG_COMMENTS","content":"我是五六七八九回复了你的评论!","createDate":1525992007357,"isRead":0},{"type":"MSG_HOMEWOK","content":"暂无提醒","createDate":1526091478104,"isRead":0},{"type":"MSG_UNIVSTAR","content":"暂无提醒","createDate":1526091478104,"isRead":0},{"type":"MSG_ATTENTION","content":"有新的好友关注了你!","createDate":1526000804079,"isRead":0}]
     */

    private int code;
    private String message;
    private List<DataBean> data;

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

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * type : MSG_ORDER
         * content : 暂无提醒
         * createDate : 1526091478103
         * isRead : 0
         */

        private String type;
        private String content;
        private long createDate;
        private int isRead;

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public long getCreateDate() {
            return createDate;
        }

        public void setCreateDate(long createDate) {
            this.createDate = createDate;
        }

        public int getIsRead() {
            return isRead;
        }

        public void setIsRead(int isRead) {
            this.isRead = isRead;
        }
    }
}
