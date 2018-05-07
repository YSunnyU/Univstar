package com.sunny.univstar.model.entity;

/**
 * Created by DELL on 2018/5/5.
 */

public class RegisterCodeEntity {


    /**
     * code : 0
     * message : 成功
     * data : {"nickname":"2121273","mobile":null,"photo":null,"id":840,"token":"eyJhbGciOiJIUzUxMiJ9.eyJyYW5kb21LZXkiOiI1dDk3bnciLCJzdWIiOiI4NDAiLCJleHAiOjE1MjYxMDk3NDAsImlhdCI6MTUyNTUwNDk0MH0.JA0oB1fQf1_OUV37vwx2WNVMIiWmww5PaWVhWyLj7aOQkASUHzKqRuiAFyuYQJpDQdQy7o4OEe2RpmJ6G9IxBw"}
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
         * nickname : 2121273
         * mobile : null
         * photo : null
         * id : 840
         * token : eyJhbGciOiJIUzUxMiJ9.eyJyYW5kb21LZXkiOiI1dDk3bnciLCJzdWIiOiI4NDAiLCJleHAiOjE1MjYxMDk3NDAsImlhdCI6MTUyNTUwNDk0MH0.JA0oB1fQf1_OUV37vwx2WNVMIiWmww5PaWVhWyLj7aOQkASUHzKqRuiAFyuYQJpDQdQy7o4OEe2RpmJ6G9IxBw
         */

        private String nickname;
        private Object mobile;
        private Object photo;
        private int id;
        private String token;

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public Object getMobile() {
            return mobile;
        }

        public void setMobile(Object mobile) {
            this.mobile = mobile;
        }

        public Object getPhoto() {
            return photo;
        }

        public void setPhoto(Object photo) {
            this.photo = photo;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }
    }
}
