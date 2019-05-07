var detailValue=12000;
var detail=
    {
        detailLeft:$('#highlight_slider_left'),
        detailRight:$('#highlight_slider_right'),
        detailTimer:null,
        init:function () {
            var that=this;
            this.detailRight.click(function () {
                that.detailCarouselRight();
            });
            this.detailLeft.click(function () {
                that.detailCarouselLeft();
            });
        },

        showGameSystemNeed:function(id,type){
            $.ajax({
                url:"/systemneed/"+id,
                type:"POST",
                async:false,
                success:function (data) {
                    data=eval("("+data+")");
                    console.log(data)
                    var systemId='#systemNeed'+type;
                    $(systemId).empty();
                    var systemNeed='';
                    systemNeed+='<li><strong>操作系统:</strong> '+data.msg.operatingSystem+'<br></li>';
                    systemNeed+='<li><strong>处理器:</strong> '+data.msg.cpu+'<br></li>';
                    systemNeed+='<li><strong>内存:</strong> '+data.msg.ram+'<br></li>';
                    systemNeed+='<li><strong>图形:</strong> '+data.msg.graphicsCard+'<br></li>';
                    systemNeed+='<li><strong>DirectX 版本:</strong> '+data.msg.directx+'<br></li>';
                    systemNeed+='<li><strong>网络:</strong> '+data.msg.network+'<br></li>';
                    systemNeed+='<li><strong>存储空间:</strong> '+data.msg.rom+'<br></li>';
                    systemNeed+='<li><strong>声卡:</strong> '+data.msg.soundCard+'<br></li>';
                    $(systemId).append(systemNeed);
                },
                error:function () {

                }
            })
        },

        showGameDetail:function(that){
            $.ajax({
                url:"/app/"+1,
                type:"POST",
                async:false,
                success:function (data) {
                    data=eval("("+data+")");
                    if (data.code==200){
                        $('#appName')[0].innerHTML=data.msg.gameName;
                        //$('#appBg')[0].setAttribute('style','background-image: url("'+data.msg.imageIntro2+'");');
                        $('#appPosterImage >img')[0].setAttribute('src',data.msg.posterImage);
                        $('#appDescription')[0].innerHTML=data.msg.gameIntroduction;
                        var date = new Date(data.msg.issuedDate);
                        Y = date.getFullYear() + '年';
                        M = date.getMonth()+1 + '月';
                        D = date.getDate() + '日';
                        $('#appReleaseDate>div.date')[0].innerHTML=Y+M+D;
                        $('#appTags').empty();
                        var tags='';
                        var length=data.msg.label.length>6?6:data.msg.label.length;
                        for (var i=0;i<length;i++){
                            tags+='<a href="#" class="app_tag" > '+data.msg.label[i]+' </a>&nbsp;';
                        }
                        tags+='<div class="app_tag add_button" onclick="ShowAppTagModal()">+</div>';
                        tags=$(tags);
                        $('#appTags').append(tags);
                        var imageIntro='<div class="highlight_player_area_spacer"><img src="https://store.st.dl.bscstorage.net/public/images/game/game_highlight_image_spacer.gif"></div>';
                        imageIntro+='<div class="highlight_player_item highlight_screenshot" style="display: block;">\n' +
                            '                                                <div class="screenshot_holder">\n' +
                            '                                                    <a class="highlight_screenshot_link"><img src="';
                        imageIntro+=data.msg.imageIntro1+'"></a></div></div>';
                        imageIntro+='<div class="highlight_player_item highlight_screenshot" style="display: none;">\n' +
                            '                                                <div class="screenshot_holder">\n' +
                            '                                                    <a class="highlight_screenshot_link"><img src="';
                        imageIntro+=data.msg.imageIntro2+'"></a></div></div>';
                        imageIntro+='<div class="highlight_player_item highlight_screenshot" style="display: none;">\n' +
                            '                                                <div class="screenshot_holder">\n' +
                            '                                                    <a class="highlight_screenshot_link"><img src="';
                        imageIntro+=data.msg.imageIntro3+'"></a></div></div>';
                        imageIntro+='<div class="highlight_player_item highlight_screenshot" style="display: none;">\n' +
                            '                                                <div class="screenshot_holder">\n' +
                            '                                                    <a class="highlight_screenshot_link"><img src="';
                        imageIntro+=data.msg.imageIntro4+'"></a></div></div>';
                        imageIntro+='<div class="highlight_player_item highlight_screenshot" style="display: none;">\n' +
                            '                                                <div class="screenshot_holder">\n' +
                            '                                                    <a class="highlight_screenshot_link"><img src="';
                        imageIntro+=data.msg.imageIntro5+'"></a></div></div>';
                        $('#highlight_player_area').empty();
                        $('#highlight_player_area').append(imageIntro);
                        var thumbnail='<div class="highlight_selector"></div>';
                        thumbnail+='<div class="highlight_strip_item highlight_strip_screenshot"><img style="width: 115px;height: 65px;" src="';
                        thumbnail+=data.msg.imageIntro1+'"></div>';
                        thumbnail+='<div class="highlight_strip_item highlight_strip_screenshot"><img style="width: 115px;height: 65px;" src="';
                        thumbnail+=data.msg.imageIntro2+'"></div>';
                        thumbnail+='<div class="highlight_strip_item highlight_strip_screenshot"><img style="width: 115px;height: 65px;" src="';
                        thumbnail+=data.msg.imageIntro3+'"></div>';
                        thumbnail+='<div class="highlight_strip_item highlight_strip_screenshot"><img style="width: 115px;height: 65px;" src="';
                        thumbnail+=data.msg.imageIntro4+'"></div>';
                        thumbnail+='<div class="highlight_strip_item highlight_strip_screenshot"><img style="width: 115px;height: 65px;" src="';
                        thumbnail+=data.msg.imageIntro5+'"></div>';
                        $('#highlight_strip_scroll').empty();
                        $('#highlight_strip_scroll').append(thumbnail);
                        var gameDeveloper='<b>名称:</b> '+data.msg.gameName.substring(0,30)+'...'+'<br>';
                        gameDeveloper+='<b>类型: </b>';
                        for (var i=0;i<data.msg.type.length;i++){
                            if (i==0){
                                gameDeveloper+='<a href="javascript:volid(0);">'+data.msg.type[i]+'</a>';
                            } else {
                                gameDeveloper+='<a href="javascript:volid(0);">, '+data.msg.type[i]+'</a>';
                            }
                        }
                        gameDeveloper+='<br>';
                        gameDeveloper+='<div class="dev_row">\n' +
                            '                                            <b>开发商:</b>\n' +
                            '                                            <a href="javascript:volid(0);">FromSoftware</a>\n' +
                            '                                        </div>\n' +
                            '                                        <div class="dev_row">\n' +
                            '                                            <b>发行商:</b>\n' +
                            '                                            <a href="javascript:volid(0);">Activision (Excluding Japan and Asia)</a>\n' +
                            '                                            , <a href="javascript:volid(0);">FromSoftware (Japan)</a>\n' +
                            '                                            , <a href="javascript:volid(0);">方块游戏 (Asia)</a>\n' +
                            '                                        </div>';
                        gameDeveloper+='<b>发行日期:</b> '+(Y+M+D)+'<br>';
                        $('#gameDeveloper').empty();
                        $('#gameDeveloper').append(gameDeveloper);
                        $('#gameBuyName')[0].innerHTML='购买 '+data.msg.gameName;
                        var price='';
                        if (data.msg.discount>0){
                            price+='<div class="discount_pct">-'+(100-data.msg.discount)+'%</div>';
                            price+='<div class="discount_prices"><div class="discount_original_price">¥ '+data.msg.gamePrice+'</div>';
                            price+='<div class="discount_final_price">¥ '+Math.ceil(data.msg.gamePrice*(data.msg.discount/100))+'</div></div>';
                        }else {
                            price+='<div class="discount_final_price">¥ '+data.msg.gamePrice+'</div>';
                        }
                        $('#gamePrice').empty();
                        $('#gamePrice').append(price);
                        var gameAbout='';
                        gameAbout+='<h2>关于这款游戏</h2>';
                        var finalGameAbout=data.msg.gameAbout.replace('/n/r','<br>');
                        finalGameAbout=finalGameAbout.replace('     ','<br>');
                        gameAbout+=finalGameAbout;
                        $('#game_area_description').empty();
                        $('#game_area_description').append(gameAbout);
                        that.showGameSystemNeed(data.msg.lowestSystem,'Left');
                        that.showGameSystemNeed(data.msg.recommendSystem,'Right');
                    }
                },
                error:function () {
                    layer.msg("获取游戏详情失败，请检查网络连接")
                }
            })
        },

    }