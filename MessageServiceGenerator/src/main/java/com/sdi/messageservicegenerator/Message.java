package com.sdi.messageservicegenerator;


    public class Message {
        private String message;
        private String key;
        private Integer priority;
        private Integer delay;
        private Boolean hasCallBack;

        public Message(final String message, final String key, final Integer priority, final Integer delay, final Boolean hasCallBack) {
            this.message = message;
            this.key = key;
            this.priority = priority;
            this.delay = delay;
            this.hasCallBack = hasCallBack;
        }

        public Message() {
        }

        public String getMessage() {
            return this.message;
        }

        public String getKey() {
            return this.key;
        }

        public Integer getPriority() {
            return this.priority;
        }

        public Integer getDelay() {
            return this.delay;
        }

        public Boolean getHasCallBack() {
            return this.hasCallBack;
        }

        public void setMessage(final String message) {
            this.message = message;
        }

        public void setKey(final String key) {
            this.key = key;
        }

        public void setPriority(final Integer priority) {
            this.priority = priority;
        }

        public void setDelay(final Integer delay) {
            this.delay = delay;
        }

        public void setHasCallBack(final Boolean hasCallBack) {
            this.hasCallBack = hasCallBack;
        }
}
