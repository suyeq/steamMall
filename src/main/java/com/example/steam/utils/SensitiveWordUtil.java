package com.example.steam.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * Author: Suyeq
 * Date: 2019-01-08
 * Time: 19:44
 */
//敏感词过滤初始化
//采用DFA算法
@Component
public class SensitiveWordUtil {


    private Map<String,String> keyWordMap;
    private String replaceString;
    private Map<Character,String> ignoreWord;

//    {
//        try {
//            init();
//            for (int i=0;i< StaticField.IgnoreWords.length;i++){
//                ignoreWord.put(StaticField.IgnoreWords[i],"1");
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

    public SensitiveWordUtil()  {

    }

    public SensitiveWordUtil(String replaceString) throws IOException {
        this.replaceString=replaceString;
        //init();
    }

    public void init() throws IOException {
        Set<String> set=readKeyWordFromFile();
        addKeyWordtoHashMap(set);
        ignoreWord=new HashMap<>();
//        for (int i=0;i< StaticField.IgnoreWords.length;i++){
//            ignoreWord.put(StaticField.IgnoreWords[i],"1");
//        }
    }

    /**
     * 将敏感关键字读出来
     * @return
     * @throws IOException
     */
    private Set<String> readKeyWordFromFile() throws IOException {
        Set<String> set=new HashSet<>();
//        //不指定字符集会乱码
//        InputStreamReader inputStreamReader=new InputStreamReader(new FileInputStream(new File("F://SensitiveWord.txt")),"GBK");
//        BufferedReader bufferedReader=new BufferedReader(inputStreamReader);
//        String line=bufferedReader.readLine();
//        while (line!=null){
//            set.add(line);
//            line=bufferedReader.readLine();
//        }
        //读取敏感词汇
        List<String> list=null;
        System.out.println("11");
        System.out.println(list);
        set.addAll(list);
        System.out.println(set);
        return set;
    }

    /**
     * 将敏感关键词建立起多颗搜索树，以HashMap存贮
     * @param keyWordSet
     * @return
     */
    public void addKeyWordtoHashMap(Set<String> keyWordSet){
        keyWordMap=new HashMap<String,String>();
        String key = null;
        Map nowMap = null;
        Map<String, String> newWorMap = null;
        Iterator<String> iterator = keyWordSet.iterator();
        while(iterator.hasNext()) {
            key = iterator.next();
            nowMap = keyWordMap;
            for (int i = 0; i < key.length(); i++) {
                char keyChar = key.charAt(i);
                Object wordMap = nowMap.get(keyChar);
                if (wordMap != null) {
                    nowMap = (Map) wordMap;
                } else {
                    newWorMap = new HashMap<String, String>();
                    //End为结束标志，1表示为该分支已完成，0表示该分支还未完成
                    newWorMap.put("End", "0");
                    nowMap.put(keyChar, newWorMap);
                    nowMap = newWorMap;
                }
                if (i == key.length() - 1) {
                    nowMap.put("End", "1");
                }
            }
        }
//        System.out.println("语法树");
//        System.out.println(keyWordMap);
    }

    /**
     * 检查从开始出的敏感词检测
     * @param beginIndex
     * @param text
     * @return
     */
    private int checkSensitiveWord(int beginIndex,String text){
        Map newWordMap=keyWordMap;
        int indexCount=0;//记录扫描敏感词而产生的偏移量
        boolean isContainSensitiveWord=false;//用来记录是否检测到敏感词
        char temp=0;
        for (int i=beginIndex;i<text.length();i++){
            temp=text.charAt(i);
            //忽略无效字符
            if (ignoreWord.get(temp)!=null){
                indexCount++;
                continue;
            }
            newWordMap=(Map)newWordMap.get(temp);
            if (newWordMap!=null){
                indexCount++;
                if ("1".equals(newWordMap.get("End"))){
                    isContainSensitiveWord=true;
                }
            }else {
                break;
            }
        }
        //敏感词长度不能小于2
        if (indexCount<2 || !isContainSensitiveWord){
            indexCount=0;
        }
        //System.out.println(indexCount+"jjjj");
        return indexCount;
    }

    /**
     * 替换掉文中的敏感词汇
     * @param replaceChar
     * @param length
     * @return
     */
//    private String getReplaceChars(String replaceChar,int length){
//        String resultReplace = replaceChar;
//        for(int i = 1 ; i < length ; i++){
//            resultReplace += replaceChar;
//        }
//
//        return resultReplace;
//    }
//
    /**
     * 获得文中的敏感词汇
     * @param text
     * @return
     */
    public Set<String> getSensitiveWord(String text){
        Set<String> sensitiveWordList = new HashSet<String>();
        for(int i = 0 ; i < text.length() ; i++){
            int length = checkSensitiveWord(i,text);
            if(length > 0){
                //将敏感词给截取下来，存入集合中
                sensitiveWordList.add(text.substring(i, i+length));
                //text.substring(i,i+length);
                i = i + length - 1;
            }
        }
        return sensitiveWordList;
    }

    /**
     * 指定字符串替换掉敏感词汇
     * @param text
     * @param replaceChar
     * @return
     */
    public String replaceSensitiveWord(String text,String replaceChar) throws IOException {
        init();
        String resultTxt = text;
        Set<String> set = getSensitiveWord(text);
        Iterator<String> iterator = set.iterator();
        String word = null;
        String replaceString = null;
        while (iterator.hasNext()) {
            word = iterator.next();
            //replaceString = getReplaceChars(replaceChar, word.length());
            //System.out.println(word);
            //System.out.println(resultTxt);
            int index=resultTxt.indexOf(word);
            //System.out.println(index+"888");
            resultTxt=resultTxt.substring(0,index)+replaceChar+resultTxt.substring(index+word.length());
           // System.out.println(resultTxt);
            //resultTxt = resultTxt.replaceAll(word, replaceChar);
        }
        return resultTxt;
    }

    /**
     *
     * @param text
     * @return
     */
    public String replaceSensitiveWord(String text){
        String result = text;
        Set<String> set = getSensitiveWord(text);
        Iterator<String> iterator = set.iterator();
        String word = null;
        String replaceString = null;
        while (iterator.hasNext()) {
            word = iterator.next();
            result = result.replaceAll(word, replaceString);
        }
        return result;
    }



}
