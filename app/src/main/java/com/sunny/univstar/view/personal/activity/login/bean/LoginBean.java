package com.sunny.univstar.view.personal.activity.login.bean;

/**
 * Created by 张玗 on 2018/5/7.
 */

public class LoginBean {

    /**
     * code : 1
     * message : cid为空
     * data : {"nickname":"Sunny","mobile":"13718620289","photo":null,"id":885,"token":"eyJhbGciOiJIUzUxMiJ9.eyJyYW5kb21LZXkiOiJ3cXBvd3UiLCJzdWIiOiI4ODUiLCJleHAiOjE1MjYyODc2ODEsImlhdCI6MTUyNTY4Mjg4MX0.EyMEaf9aW9JlbtNRyIN5KH_ofDPA4CFRIFplXN5_rO0d7YOFteqiCOI8SjFKOLraKia1PPW3Nw4e6sOxw0204A"}
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
         * nickname : Sunny
         * mobile : 13718620289
         * photo : null
         * id : 885
         * token : eyJhbGciOiJIUzUxMiJ9.eyJyYW5kb21LZXkiOiJ3cXBvd3UiLCJzdWIiOiI4ODUiLCJleHAiOjE1MjYyODc2ODEsImlhdCI6MTUyNTY4Mjg4MX0.EyMEaf9aW9JlbtNRyIN5KH_ofDPA4CFRIFplXN5_rO0d7YOFteqiCOI8SjFKOLraKia1PPW3Nw4e6sOxw0204A
         */

        private String nickname;
        private String mobile;
        private Object photo;
        private int id;
        private String token;

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
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
