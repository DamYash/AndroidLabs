package com.example.androidlabs;

public class DatabaseSchema {

    public static final class Messages {
        public static final String NAME = "messages_";

        public static final class Columns {
            public static final String ID=Messages.NAME + "id";
            public static final String IS_SEND = Messages.NAME + "is_send";
            public static final String TEXT = Messages.NAME + "text";
        }
    }
}