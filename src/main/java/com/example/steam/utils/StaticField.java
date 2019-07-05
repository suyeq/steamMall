package com.example.steam.utils;

import org.springframework.stereotype.Component;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: Suyeq
 * @date: 2019-04-26
 * @time: 11:09
 */
@Component
public class StaticField {

    public final static String COOKIE_KEY="token";

    public final static String EMAIL_KEY="email";

    public final static String SENSITIVE="SteamMall";

    private char [] IGNORE_WORD={'@',' ','#','$','%','^','&','*','(',')'};

    public char[] getIGNORE_WORD(){
        return IGNORE_WORD;
    }

}
