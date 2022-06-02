package me.bi.videostorage.domain;

import java.util.HashMap;
import java.util.Map;

public enum AuthorityEnum {
    ROLE_USER("ROLE_USER"),
    ROLE_ADMIN("ROLE_ADMIN");

    private final String key;

    private static final Map<String,AuthorityEnum> lookup = new HashMap<>();

    static {
        for(AuthorityEnum auth : AuthorityEnum.values()) {
            lookup.put(auth.key,auth);
        }
    }

    // private
    AuthorityEnum(String key) {
        this.key = key;
    }

    public String getKey() {
        return this.key;
    }

    public static AuthorityEnum get(String key) {
        return lookup.get(key);
    }

    public static boolean containsKey(String key) {
        return lookup.containsKey(key);
    }

}