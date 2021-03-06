package com.example.steam.controller;

import com.alibaba.fastjson.JSON;
import com.example.steam.service.GameService;
import com.example.steam.service.RecentGameService;
import com.example.steam.service.UserGameService;
import com.example.steam.utils.ResultMsg;
import com.example.steam.vo.GameDetail;
import com.example.steam.vo.SpecialGame;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: Suyeq
 * @date: 2019-04-27
 * @time: 11:02
 */
@Controller
public class GameController {

//    private Map<String,String> map=new ConcurrentHashMap<>();

    Logger log= LoggerFactory.getLogger(GameController.class);

    @Autowired
    GameService gameService;
    @Autowired
    UserGameService userGameService;
    @Autowired
    RecentGameService recentGameService;

    @ResponseBody
    @RequestMapping("/feturedCarousel")
    public String feturedCarousel(){
        return JSON.toJSONString(ResultMsg.SUCCESS(gameService.findGamesFetured()));
    }

    @ResponseBody
    @RequestMapping("/specialCarousel")
    public String specialCarousel(){
        List<SpecialGame> list=gameService.findSpecialGames();
        return JSON.toJSONString(ResultMsg.SUCCESS(list));
    }

    @ResponseBody
    @RequestMapping("/newRelease_index/{page}")
    public String newRelease(@PathVariable("page")long page){
        long start=System.currentTimeMillis();
        List<GameDetail> list=gameService.findNewRelease(page);
        long end=System.currentTimeMillis();
        long result=end-start;
        log.error(result+"");
        return JSON.toJSONString(ResultMsg.SUCCESS(list));
    }

    @ResponseBody
    @RequestMapping("/hotSell_index/{page}")
    public String hotSell(@PathVariable("page")long page){
        return JSON.toJSONString(ResultMsg.SUCCESS(gameService.findHotSell(page)));
    }

    @ResponseBody
    @RequestMapping("/upComing_index/{page}")
    public String upComing(@PathVariable("page")long page){
        return JSON.toJSONString(ResultMsg.SUCCESS(gameService.findUpComing(page)));
    }

    @ResponseBody
    @RequestMapping("/app/{id}")
    public String oneGameDetail(@PathVariable("id")long id){
        return JSON.toJSONString(ResultMsg.SUCCESS(gameService.findGameById(id)));
    }

    @ResponseBody
    @RequestMapping("/classGame/{typeName}")
    public String findGamesToClassCarouselBy(@PathVariable("typeName")String typeName){
        return JSON.toJSONString(ResultMsg.SUCCESS(gameService.findGamesToClassCarousel(typeName)));
    }

    @ResponseBody
    @RequestMapping("/classGame/newRelease/{typeName}/{page}")
    public String findGamesNewReleaseByType(@PathVariable("typeName")String typeName,
                                            @PathVariable("page")long page){
        return JSON.toJSONString(ResultMsg.SUCCESS(gameService.findGamesNewReleaseByType(typeName,page)));
    }

    @ResponseBody
    @RequestMapping("/classGame/hotSell/{typeName}/{page}")
    public String findGamesHotSellByType(@PathVariable("typeName")String typeName,
                                         @PathVariable("page")long page){
        return JSON.toJSONString(ResultMsg.SUCCESS(gameService.findGamesHotSellByType(typeName,page)));
    }

    @ResponseBody
    @RequestMapping("/classGame/upComing/{typeName}/{page}")
    public String findGamesUpComingByType(@PathVariable("typeName")String typeName,
                                          @PathVariable("page")long page){
        return JSON.toJSONString(ResultMsg.SUCCESS(gameService.findGamesUpComingByType(typeName,page)));
    }

    @ResponseBody
    @RequestMapping("issued/classGame/{typeName}")
    public String findGamesIssuedSumByType(@PathVariable("typeName")String typeName){
        return JSON.toJSONString(ResultMsg.SUCCESS(gameService.findGamesSumByType(typeName)));
    }

    @ResponseBody
    @RequestMapping("upComing/classGame/{typeName}")
    public String findGamesUpComingSumByType(@PathVariable("typeName")String typeName){
        return JSON.toJSONString(ResultMsg.SUCCESS(gameService.findGamesUpComingSumByType(typeName)));
    }

    @ResponseBody
    @RequestMapping("/issued/sum")
    public String findIssuedGameSum(){
        return JSON.toJSONString(ResultMsg.SUCCESS(gameService.findIssuedGamesSum()));
    }

    @ResponseBody
    @RequestMapping("/upcoming/sum")
    public String findUpComingGameSum(){
        return JSON.toJSONString(ResultMsg.SUCCESS(gameService.findUpComingGamesSum()));
    }

    @ResponseBody
    @RequestMapping("/searchresult")
    public String searchResult(@RequestParam("content")String content){
        return JSON.toJSONString(ResultMsg.SUCCESS(gameService.findGamesBySearchContent(content)));
    }

    @ResponseBody
    @RequestMapping("/iscontains")
    public String isContainsGame(@RequestParam("email")String email,
                                 @RequestParam("gameId")long gameId){
        return JSON.toJSONString(ResultMsg.SUCCESS(userGameService.isContains(email,gameId)));
    }

    @ResponseBody
    @RequestMapping("/recentplaygame/{email}")
    public String showRecentGame(@PathVariable("email") String email){
        return JSON.toJSONString(ResultMsg.SUCCESS(userGameService.findThreeRecentGameVoListByEmail(email)));
    }

    @ResponseBody
    @RequestMapping("/game/user/all/{email}")
    public String showAllGamesByUserEmail(@PathVariable("email")String email){
        return JSON.toJSONString(ResultMsg.SUCCESS(gameService.findAllGameShowByEmail(email)));
    }

    @ResponseBody
    @RequestMapping("/game/count/{email}")
    public String gameCountByEmail(@PathVariable("email") String email){
        return JSON.toJSONString(ResultMsg.SUCCESS(gameService.findContainGamesNum(email)));
    }

    /**
     * @return
     */
    @ResponseBody
    @RequestMapping("/game/add")
    public String addGame(@RequestParam("newGameName")String newGameName,
                          @RequestParam("newGameIntroduction")String newGameIntroduction,
                          @RequestParam("newGameAbout")String newGameAbout,
                          @RequestParam("newGameKind")String newGameKind,
                          @RequestParam("newGamePrice")int newGamePrice,
                          @RequestParam("newGameDiscount")int  newGameDiscount,
                          @RequestParam("newGameLowestCpu")String newGameLowestCpu,
                          @RequestParam("newGameLowestOs")String newGameLowestOs,
                          @RequestParam("newGameLowestRam")String newGameLowestRam,
                          @RequestParam("newGameLowestXianka")String newGameLowestXianka,
                          @RequestParam("newGameLowestNetwork")String newGameLowestNetwork,
                          @RequestParam("newGameLowestDirectx")String newGameLowestDirectx,
                          @RequestParam("newGameLowestRom")String newGameLowestRom,
                          @RequestParam("newGameLowestShenka")String newGameLowestShenka,
                          @RequestParam("newGameGoodCpu")String newGameGoodCpu,
                          @RequestParam("newGameGoodOs")String newGameGoodOs,
                          @RequestParam("newGameGoodRam")String newGameGoodRam,
                          @RequestParam("newGameGoodXianka")String newGameGoodXianka,
                          @RequestParam("newGameGoodNetwork")String newGameGoodNetwork,
                          @RequestParam("newGameGoodDirectx")String newGameGoodDirectx,
                          @RequestParam("newGameGoodRom")String newGameGoodRom,
                          @RequestParam("newGameGoodShenka")String newGameGoodShenka){
        long result=gameService.addGame(newGameName,newGameIntroduction, newGameAbout, newGameKind, newGamePrice, newGameDiscount,
                newGameLowestCpu,newGameLowestOs,newGameLowestRam,newGameLowestXianka,newGameLowestNetwork,newGameLowestDirectx,
                 newGameLowestRom,newGameLowestShenka,newGameGoodCpu,newGameGoodOs,newGameGoodRam,newGameGoodXianka,newGameGoodNetwork,
                newGameGoodDirectx,newGameGoodRom, newGameGoodShenka);
        return JSON.toJSONString(ResultMsg.SUCCESS(result));
    }


    @ResponseBody
    @RequestMapping("/game/update")
    public String addGame(@RequestParam("gameId")long gameId,
                          @RequestParam("newGameName")String newGameName,
                          @RequestParam("newGameIntroduction")String newGameIntroduction,
                          @RequestParam("newGameAbout")String newGameAbout,
                          //@RequestParam("newGameKind")String newGameKind,
                          @RequestParam("newGamePrice")int newGamePrice,
                          @RequestParam("newGameDiscount")int  newGameDiscount){
        long result=gameService.updateGame(gameId,newGameName,newGameIntroduction, newGameAbout, null, newGamePrice, newGameDiscount);
        return JSON.toJSONString(ResultMsg.SUCCESS(result));
    }

    @ResponseBody
    @RequestMapping("/game/delete/{gameId}")
    public String deleteGame(@PathVariable("gameId")long gameId){
        return JSON.toJSONString(ResultMsg.SUCCESS(gameService.deleteGame(gameId)));
    }

    @ResponseBody
    @RequestMapping("/game/issued/{gameId}")
    public String publishGame(@PathVariable("gameId")long gameId){
        return JSON.toJSONString(ResultMsg.SUCCESS(gameService.updateGameIssuedStatu(gameId)));
    }






}
