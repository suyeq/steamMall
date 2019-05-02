var featuredValue=12000;
var specialValue=12000;
var classRecommendValue=15000;
var detailValue=12000;
var salt="1q2w3e";
var steam=
    {
        //精选
        featuredLeft:$('#featured-left'),
        featuredRight:$('#featured-right'),
        //特殊推荐
        specialLeft:$('#specialleft'),
        specialRight:$('#specialright'),
        featuredTimer:null,

        classLeft:$('#genre_large_cluster .arrow.left'),
        classRight:$('#genre_large_cluster .arrow.right'),
        classTimer:null,

        detailLeft:$('#highlight_slider_left'),
        detailRight:$('#highlight_slider_right'),
        detailTimer:null,

        init:function() {
            var that = this;
            this.showFeturedData();
            this.showSpecialData();
            this.showNewReleaseData(that);
            this.showTopSellerData(that);
            this.featuredLeft.click(function () {
                that.featuredLeftCarouselLeft();
            });
            this.featuredRight.click(function () {
                that.featuredLeftCarouselRight();
            });
            this.specialLeft.click(function () {
                that.specialLeftCarousel();
            });
            this.specialRight.click(function () {
                that.specialRightCarousel();
            });
            this.classLeft.click(function () {
                that.classLeftCarousel();
            });
            this.classRight.click(function () {
                that.classRightCarousel();
            });
            this.detailLeft.click(function () {
                that.detailCarouselLeft();
            });
            this.detailRight.click(function () {
                that.detailCarouselRight();
            });
            this.featuredLeftCarouselStart(that);
            //console.log(this.timer);
            this.mouseFeturedCarouselPause(that);
            this.mouseChangeImage();
            this.mouseTabPause(that);
            this.classCarouselStart(that);
            this.mouseClassCarouselPause(that);
            this.mouseClassListPause();
            this.detailCarouselStart(that);
            this.mouseDetailPause(that);
        },

        showGameDetailLayer:function(id){
            $.ajax({
                url:"/gameDetail/"+id,
                type:"POST",
                async:false,
                success:function (data) {
                    data=eval("("+data+")");
                    console.log(data);
                    $('#tab_preview_container').empty();
                    var parent=$('<div class="tab_preview focus"></div>')
                    var title='<h2>'+data.msg.gameName+'</h2>';
                    title=$(title);
                    var review='<div class="tab_review_summary"><div class="title">总体用户评测：</div><span class="game_review_summary positive">特别好评</span><span>&nbsp;(440)</span></div>';
                    review=$(review);
                    var tag='<div class="tags">';
                    for (var i=0;i<data.msg.label.length;i++){
                        tag+='<a>'+data.msg.label[i]+'</a>';
                    }
                    tag+='</div>';
                    tag=$(tag);
                    var intro1='<div class="screenshot" style="background-image: url(&quot;';
                    intro1+=data.msg.imageIntro1;
                    intro1+='&quot;);"></div>';
                    var intro2='<div class="screenshot" style="background-image: url(&quot;';
                    intro2+=data.msg.imageIntro2;
                    intro2+='&quot;);"></div>';
                    var intro3='<div class="screenshot" style="background-image: url(&quot;';
                    intro3+=data.msg.imageIntro3;
                    intro3+='&quot;);"></div>';
                    var intro4='<div class="screenshot" style="background-image: url(&quot;';
                    intro4+=data.msg.imageIntro4;
                    intro4+='&quot;);"></div>';
                    intro1=$(intro1);
                    intro2=$(intro2);
                    intro3=$(intro3);
                    intro4=$(intro4);
                    parent.append(title,review,tag,intro1,intro2,intro3,intro4);
                    $('#tab_preview_container').append(parent);
                },
                error:function () {

                }
            })
        },

        showNewReleaseData:function(that){
            $.ajax({
                url:"/newRelease_index",
                type:"POST",
                async:false,
                success:function (data) {
                    data=eval("("+data+")");
                    $('#tab_newreleases_content').empty();
                    for (var i=0;i<data.msg.length;i++){
                        var parent='<a href="'
                        parent+='/app/'+data.msg[i].id+'"';
                        parent+='app-id="'+data.msg[i].id+'"';

                        if (i==0){
                            parent+=' class="tab_item app_impression_tracked focus"></a>';
                        } else {
                            parent+=' class="tab_item app_impression_tracked"></a>';
                        }
                        var child1='<div class="tab_item_cap"><img class="tab_item_cap_img" style="width: 184px;height: 69px;" src="';
                        child1+=data.msg[i].posterImage;
                        child1+='"></div>';
                        var child2='<div class="discount_block tab_item_discount">';
                        if (data.msg[i].discount>0){
                            child2+='<div class="discount_pct">';
                            child2+='-'+(100-data.msg[i].discount)+'%';
                            child2+='</div>';
                        }
                        child2+='<div class="discount_prices">';
                        if (data.msg[i].discount>0){
                            child2+='<div class="discount_original_price">';
                            child2+='¥ '+data.msg[i].gamePrice;
                            child2+='</div>';
                        }
                        if (data.msg[i].discount>0){
                            child2+='<div class="discount_final_price">';
                            child2+='¥ '+Math.ceil(data.msg[i].gamePrice*(data.msg[i].discount/100));
                        }else {
                            child2+='<div class="discount_final_price">';
                            child2+='¥ '+data.msg[i].gamePrice;
                        }
                        child2+='</div></div></div>';
                        var child3='<div class="tab_item_content"><div class="tab_item_name">';
                        child3+=data.msg[i].gameName;
                        child3+='</div><div class="tab_item_details"><span class="platform_img win"></span><span class="platform_img mac"></span><span class="platform_img linux"></span><div class="tab_item_top_tags">';
                        for (var j=0;j<data.msg[i].label.length;j++){
                            if (j==0){
                                child3+='<span class="top_tag">'+data.msg[i].label[j]+'</span>';
                            }else {
                                child3+='<span class="top_tag">, '+data.msg[i].label[j]+'</span>';
                            }
                        }
                        child3+='</div></div></div>';
                        var child4='<div style="clear: both;"></div>';
                        parent=$(parent);
                        child1=$(child1);
                        child2=$(child2);
                        child3=$(child3);
                        child4=$(child4);
                        parent.append(child1,child2,child3,child4);
                        $('#tab_newreleases_content').append(parent);
                    }
                    var seeMore='<div class="tab_see_more">查看更多： <a href="#" class="btnv6_white_transparent btn_small_tall"><span>新品</span></a></div>'
                    $('#tab_newreleases_content').append(seeMore);
                    var flag='#tab_app_'+data.msg[0].id;
                    if ($(flag).length==0){
                        that.showGameDetailLayer(data.msg[0].id);
                    }
                },
                error:function () {
                    layer.msg("网络错误");
                }
            });
        },

        showTopSellerData:function(th){
            $.ajax({
                url:"/hotSell_index",
                type:"POST",
                success:function (data) {
                    data=eval("("+data+")");
                    $('#tab_topsellers_content').empty();
                    for (var i=0;i<data.msg.length;i++){
                        var parent='<a href="'
                        parent+='/app/'+data.msg[i].id+'"';
                        parent+='app-id="'+data.msg[i].id+'"';
                        if (i==0){
                            parent+=' class="tab_item app_impression_tracked focus"></a>';
                        } else {
                            parent+=' class="tab_item app_impression_tracked"></a>';
                        }
                        var child1='<div class="tab_item_cap"><img class="tab_item_cap_img" style="width: 184px;height: 69px;" src="';
                        child1+=data.msg[i].posterImage;
                        child1+='"></div>';
                        var child2='<div class="discount_block tab_item_discount">';
                        if (data.msg[i].discount>0){
                            child2+='<div class="discount_pct">';
                            child2+='-'+(100-data.msg[i].discount)+'%';
                            child2+='</div>';
                        }
                        child2+='<div class="discount_prices">';
                        if (data.msg[i].discount>0){
                            child2+='<div class="discount_original_price">';
                            child2+='¥ '+data.msg[i].gamePrice;
                            child2+='</div>';
                        }
                        if (data.msg[i].discount>0){
                            child2+='<div class="discount_final_price">';
                            child2+='¥ '+Math.ceil(data.msg[i].gamePrice*(data.msg[i].discount/100));
                        }else {
                            child2+='<div class="discount_final_price">';
                            child2+='¥ '+data.msg[i].gamePrice;
                        }
                        child2+='</div></div></div>';
                        var child3='<div class="tab_item_content"><div class="tab_item_name">';
                        child3+=data.msg[i].gameName;
                        child3+='</div><div class="tab_item_details"><span class="platform_img win"></span><span class="platform_img mac"></span><span class="platform_img linux"></span><div class="tab_item_top_tags">';
                        for (var j=0;j<data.msg[i].label.length;j++){
                            if (j==0){
                                child3+='<span class="top_tag">'+data.msg[i].label[j]+'</span>';
                            }else {
                                child3+='<span class="top_tag">, '+data.msg[i].label[j]+'</span>';
                            }
                        }
                        child3+='</div></div></div>';
                        var child4='<div style="clear: both;"></div>';
                        parent=$(parent);
                        child1=$(child1);
                        child2=$(child2);
                        child3=$(child3);
                        child4=$(child4);
                        parent.append(child1,child2,child3,child4);
                        $('#tab_topsellers_content').append(parent);
                    }
                    var seeMore='<div class="tab_see_more">查看更多： <a href="#" class="btnv6_white_transparent btn_small_tall"><span>热销商品</span></a></div>'
                    $('#tab_topsellers_content').append(seeMore);
                    th.mouseTabPause(th);
                },
                error:function () {
                    layer.msg("网络错误");
                }
            });
        },

        // showGlobalData:function(data){
        //     for (var i=0;i<data.msg.length;i++){
        //         var parent='<a href="'
        //         parent+='/app/'+data.msg[i].id;
        //         if (i==0){
        //             parent+='" class="tab_item app_impression_tracked focus"></a>';
        //         } else {
        //             parent+='" class="tab_item app_impression_tracked"></a>';
        //         }
        //         var child1='<div class="tab_item_cap"><img class="tab_item_cap_img" style="width: 184px;height: 69px;" src="';
        //         child1+=data.msg[i].posterImage;
        //         child1+='"></div>';
        //         var child2='<div class="discount_block tab_item_discount">';
        //         if (data.msg[i].discount>0){
        //             child2+='<div class="discount_pct">';
        //             child2+='-'+(100-data.msg[i].discount)+'%';
        //             child2+='</div>';
        //         }
        //         child2+='<div class="discount_prices">';
        //         if (data.msg[i].discount>0){
        //             child2+='<div class="discount_original_price">';
        //             child2+='¥ '+data.msg[i].gamePrice;
        //             child2+='</div>';
        //         }
        //         if (data.msg[i].discount>0){
        //             child2+='<div class="discount_final_price">';
        //             child2+='¥ '+Math.ceil(data.msg[i].gamePrice*(data.msg[i].discount/100));
        //         }else {
        //             child2+='<div class="discount_final_price">';
        //             child2+='¥ '+data.msg[i].gamePrice;
        //         }
        //         child2+='</div></div></div>';
        //         var child3='<div class="tab_item_content"><div class="tab_item_name">';
        //         child3+=data.msg[i].gameName;
        //         child3+='</div><div class="tab_item_details"><span class="platform_img win"></span><span class="platform_img mac"></span><span class="platform_img linux"></span><div class="tab_item_top_tags">';
        //         for (var j=0;j<data.msg[i].label.length;j++){
        //             if (j==0){
        //                 child3+='<span class="top_tag">'+data.msg[i].label[j]+'</span>';
        //             }else {
        //                 child3+='<span class="top_tag">, '+data.msg[i].label[j]+'</span>';
        //             }
        //         }
        //         child3+='</div></div></div>';
        //         var child4='<div style="clear: both;"></div>';
        //         parent=$(parent);
        //         child1=$(child1);
        //         child2=$(child2);
        //         child3=$(child3);
        //         child4=$(child4);
        //         parent.append(child1,child2,child3,child4);
        //         return parent;
        // },

        showSpecialData:function(){
            $.ajax({
                url:"/specialCarousel",
                type:"POST",
                async:false,
                data:{

                },
                success:function (data) {
                    data=eval("("+data+")");
                    var element=new Array(data.msg.length);
                    for (var i=0;i<data.msg.length;i++){
                        element[i]='<div class="specials_target"><a class="store_capsule broadcast_capsule app_impression_tracked" href="';
                        element[i]+="/app/"+data.msg[i].id;
                        element[i]+='"> <div class="capsule header"><img style="width: 306px;height: 143.02px;" src="';
                        element[i]+=data.msg[i].posterImage;
                        element[i]+='"></div><div><div class="discount_block  daily_deal_discount discount_block_large"><div class="discount_pct">';
                        element[i]+="-"+(100-data.msg[i].discount)+"%";
                        element[i]+='</div><div class="discount_prices"><div class="discount_original_price">';
                        element[i]+='¥ '+data.msg[i].gamePrice;
                        element[i]+='</div><div class="discount_final_price">';
                        var finalPrice=Math.ceil(data.msg[i].gamePrice*(data.msg[i].discount/100));
                        element[i]+='¥ '+finalPrice;
                        element[i]+='</div></div></div></div></a></div>';
                    }
                    var child1=$('<div class="focus"></div>');
                    var child2=$('<div></div>');
                    var child1_element1=$('<div></div>');
                    var child1_element2=$('<div></div>');
                    var child1_element3=$('<div></div>');
                    var child2_element1=$('<div></div>');
                    var child2_element2=$('<div></div>');
                    var child2_element3=$('<div></div>');
                    child1_element1.append(element[0],element[1]);
                    child1_element2.append(element[2],element[3]);
                    child1_element3.append(element[4],element[5]);
                    child1.append(child1_element1,child1_element2,child1_element3);
                    child2_element1.append(element[6],element[7]);
                    child2_element2.append(element[8],element[9]);
                    child2_element3.append(element[10],element[11]);
                    child2.append(child2_element1,child2_element2,child2_element3);
                    $('#specialCarous').empty();
                    $('#specialCarous').append(child1,child2);
                },
                error:function () {
                    layer.msg('网络错误');
                }
            });
        },

        showFeturedData:function(){
            $.ajax({
                url:"/feturedCarousel",
                type:"POST",
                async:false,
                data:{

                },
                success:function (data) {
                    $('#featuredCarousel').empty();
                    data=eval("("+data+")");
                    if (data.code == 200){
                        for (var i=0;i<data.msg.length;i++){
                            var parent;
                            if (i == 0){
                                parent='<a class="store_main_capsule broadcast_capsule app_impression_tracked focus" href="';
                            }else{
                                parent='<a class="store_main_capsule broadcast_capsule app_impression_tracked" href="';
                            }
                            parent+='/app/'+data.msg[i].id;
                            parent+='"></a>';
                            //存贮四张介绍图信息
                            var elementChild1='<div class="capsule main_capsule" data-background-image-url="';
                            elementChild1+=data.msg[i].posterImage;
                            elementChild1+='" style="background-image: url(&quot;';
                            elementChild1+=data.msg[i].posterImage;
                            elementChild1+='&quot;);"><div class="screenshot" data-background-image-url="';
                            elementChild1+=data.msg[i].imageIntro1;
                            elementChild1+='" style="background-image: url(&quot;';
                            elementChild1+=data.msg[i].imageIntro1;
                            elementChild1+='&quot;);"></div><div class="screenshot" data-background-image-url="';
                            elementChild1+=data.msg[i].imageIntro2;
                            elementChild1+='" style="background-image: url(&quot;';
                            elementChild1+=data.msg[i].imageIntro2;
                            elementChild1+='&quot;);"></div><div class="screenshot" data-background-image-url="';
                            elementChild1+=data.msg[i].imageIntro3;
                            elementChild1+='" style="background-image: url(&quot;';
                            elementChild1+=data.msg[i].imageIntro3;
                            elementChild1+='&quot;);"></div><div class="screenshot"data-background-image-url="';
                            elementChild1+=data.msg[i].imageIntro4;
                            elementChild1+='" style="background-image: url(&quot;';
                            elementChild1+=data.msg[i].imageIntro4;
                            elementChild1+='&quot;);"></div></div>';
                            //游戏信息
                            var elementChild2='<div class="info"><div class="app_name"><div>';
                            elementChild2+=data.msg[i].gameName;
                            elementChild2+='</div></div></div>';
                            //四张介绍图
                            var intro='<div class="screenshots"><div><div data-background-image-url="';
                            intro+=data.msg[i].imageIntro1;
                            intro+='" style="background-image: url(&quot;';
                            intro+=data.msg[i].imageIntro1;
                            intro+='&quot;);"></div></div><div><div data-background-image-url="';
                            intro+=data.msg[i].imageIntro2;
                            intro+='" style="background-image: url(&quot;';
                            intro+=data.msg[i].imageIntro2;
                            intro+='&quot;);"></div></div><div><div data-background-image-url="';
                            intro+=data.msg[i].imageIntro3;
                            intro+='" style="background-image: url(&quot;';
                            intro+=data.msg[i].imageIntro3;
                            intro+='&quot;);"></div></div><div><div data-background-image-url="';
                            intro+=data.msg[i].imageIntro4;
                            intro+='" style="background-image: url(&quot;';
                            intro+=data.msg[i].imageIntro4;
                            intro+='&quot;);"></div></div></div>';
                            //热销
                            var hotseller='<div class="reason"><div class="main default">';
                            if (data.msg[i].issuedStatu==1){
                                hotseller+='现已推出';
                            }else {
                                hotseller+='立即预购';
                            }
                            hotseller+='</div><div class="additional"><div>热销商品</div></div></div>';
                            //折扣
                            var discount='<div class="discount_block  discount_block_inline" data-price-final="6500">';
                            if (data.msg[i].discount>0){
                                discount+='<div class="discount_pct">';
                                discount+='-'+(100-data.msg[i].discount)+'%';
                                discount+='</div>';
                            }
                            if (data.msg[i].discount>0){
                                discount+='<div class="discount_prices"><div class="discount_original_price">';
                                discount+='¥ '+data.msg[i].gamePrice;
                                discount+='</div>';
                            }
                            if (data.msg[i].discount>0){
                                discount+='<div class="discount_final_price">';
                                var finalPrice=Math.ceil(data.msg[i].gamePrice*(data.msg[i].discount/100));
                                discount+='¥ '+finalPrice;
                                discount+='</div>';
                            }else {
                                discount+='<div class="discount_final_price">';
                                discount+='¥ '+data.msg[i].gamePrice;
                                discount+='</div>';
                            }
                            discount+='</div></div>';
                            //平台
                            var platform='<div class="platforms"><span class="platform_img win"></span></div>'
                            var parent=$(parent);
                            var element1=$(elementChild1);
                            var element2=$(elementChild2);
                            var intro=$(intro);
                            var hotseller=$(hotseller);
                            var discount=$(discount);
                            var platform=$(platform);
                            element2.append(intro,hotseller,discount,platform);
                            parent.append(element1,element2);
                            $('#featuredCarousel').append(parent);
                        }

                    }

                },
                error:function () {
                    layer.msg('网络错误')
                }
            })
        },

        mouseDetailPause:function(th){
            $('#detailPause').hover(function () {
                window.clearInterval(th.detailTimer);
            },function () {
                th.detailCarouselStart(th);
            });
        },

        detailCarouselStart:function(th){
            th.detailTimer=setInterval(function () {
                th.detailCarouselRight();
            },7000);
        },

        detailCarouselRight:function(){
            $('#highlight_player_area div.highlight_player_item.highlight_screenshot')[detailValue%5].setAttribute('style','display: none;');
            $('#highlight_player_area div.highlight_player_item.highlight_screenshot')[(detailValue+1)%5].setAttribute('style','display: block;');
            $('#highlight_strip_scroll div.highlight_strip_item.highlight_strip_screenshot')[detailValue%5].setAttribute('class','highlight_strip_item highlight_strip_screenshot');
            $('#highlight_strip_scroll div.highlight_strip_item.highlight_strip_screenshot')[(detailValue+1)%5].setAttribute('class','highlight_strip_item highlight_strip_screenshot focus');
            var index=(detailValue+1)%5*120;
            index='left:'+index+'px;';
            $('#highlight_strip_scroll div.highlight_selector')[0].setAttribute('style',index);
            detailValue++;
        },

        detailCarouselLeft:function(){
            $('#highlight_player_area div.highlight_player_item.highlight_screenshot')[detailValue%5].setAttribute('style','display: none;');
            $('#highlight_player_area div.highlight_player_item.highlight_screenshot')[(detailValue-1)%5].setAttribute('style','display: block;');
            $('#highlight_strip_scroll div.highlight_strip_item.highlight_strip_screenshot')[detailValue%5].setAttribute('class','highlight_strip_item highlight_strip_screenshot');
            $('#highlight_strip_scroll div.highlight_strip_item.highlight_strip_screenshot')[(detailValue-1)%5].setAttribute('class','highlight_strip_item highlight_strip_screenshot focus');
            var index=(detailValue-1)%5*120;
            index='left:'+index+'px;';
            $('#highlight_strip_scroll div.highlight_selector')[0].setAttribute('style',index);
            detailValue--;
        },

        mouseClassListPause:function(){
            var height=null;
            $('#mouseClassListPause div div div>a[class *="tab_item"]').hover(function () {
                var newStyle=$('#global_hover')[0].getAttribute('style').replace('display: none;','display: block;');
                $('#global_hover')[0].setAttribute('style',newStyle);
                var index=$('#mouseClassListPause div div div>a[class *="tab_item"]').index(this);
                height=(index%15)*74+664;
                //top: 664px;
                height='top: '+height+'px;';
                newStyle=$('#global_hover')[0].getAttribute('style').replace('top: 664px;',height);
                $('#global_hover')[0].setAttribute('style',newStyle);
            },function () {
                var newStyle=$('#global_hover')[0].getAttribute('style').replace('display: block;','display: none;');
                newStyle=newStyle.replace(height,'top: 664px;');
                $('#global_hover')[0].setAttribute('style',newStyle);
            });
        },

        classCarouselStart:function(th){
            th.classTimer=setInterval(function () {
                th.classRightCarousel();
            },7000);
        },

        mouseClassCarouselPause:function(th){
            $('#genre_large_cluster').hover(function () {
                window.clearInterval(th.classTimer);
            },function () {
                th.classCarouselStart(th);
            });
        },

        classRightCarousel:function(){
            $('#genre_large_cluster .carousel_items.store_capsule_container>a')[classRecommendValue%15].setAttribute('class','');
            $('#genre_large_cluster .carousel_items.store_capsule_container>a')[(classRecommendValue+1)%15].setAttribute('class','focus');
            $('#genre_large_cluster .carousel_thumbs>div')[classRecommendValue%15].setAttribute('class','');
            $('#genre_large_cluster .carousel_thumbs>div')[(classRecommendValue+1)%15].setAttribute('class','focus');
            classRecommendValue++;
        },

        classLeftCarousel:function(){
            $('#genre_large_cluster .carousel_items.store_capsule_container>a')[classRecommendValue%15].setAttribute('class','');
            $('#genre_large_cluster .carousel_items.store_capsule_container>a')[(classRecommendValue-1)%15].setAttribute('class','focus');
            $('#genre_large_cluster .carousel_thumbs>div')[classRecommendValue%15].setAttribute('class','');
            $('#genre_large_cluster .carousel_thumbs>div')[(classRecommendValue-1)%15].setAttribute('class','focus');
            classRecommendValue--;
        },

        mouseTabPause:function(that){
            var indexPreNewReleases=0;
            var indexPreHotSeller=0;
            var indexUpComing=0;
            $('#tab_newreleases_content>a').hover(function () {
                var classValue=$(this)[0].getAttribute('class');
                var appId=$(this)[0].getAttribute('app-id');
                if (classValue.indexOf('focus')!=-1)
                    return;
                var indexNext=$('#tab_newreleases_content>a').index(this);
                $('#tab_newreleases_content>a').eq(indexPreNewReleases)[0].setAttribute('class','tab_item app_impression_tracked');
                $(this)[0].setAttribute('class','tab_item app_impression_tracked focus');
                indexPreNewReleases=indexNext;
                var flag='#tab_app_'+appId;
                if ($(flag).length==0){
                    that.showGameDetailLayer(appId);
                }
            },function () {
            });

            $('#tab_topsellers_content>a').hover(function () {
                var classValue=$(this)[0].getAttribute('class');
                if (classValue.indexOf('focus')!=-1)
                    return;
                var indexNext=$('#tab_topsellers_content>a').index(this);
                $('#tab_topsellers_content>a').eq(indexPreHotSeller)[0].setAttribute('class','tab_item app_impression_tracked');
                $(this)[0].setAttribute('class','tab_item app_impression_tracked focus');
                indexPreHotSeller=indexNext;
                var appId=$(this)[0].getAttribute('app-id');
                var flag='#tab_app_'+appId;
                if ($(flag).length==0){
                    that.showGameDetailLayer(appId);
                }
            },function () {
            });

            $('#tab_upcoming_content>a').hover(function () {
                var classValue=$(this)[0].getAttribute('class');
                if (classValue.indexOf('focus')!=-1)
                    return;
                var indexNext=$('#tab_upcoming_content>a').index(this);
                $('#tab_upcoming_content>a').eq(indexUpComing)[0].setAttribute('class','tab_item app_impression_tracked');
                $(this)[0].setAttribute('class','tab_item app_impression_tracked focus');
                indexUpComing=indexNext;
                var appId=$(this)[0].getAttribute('app-id');
                var flag='#tab_app_'+appId;
                if ($(flag).length==0){
                    that.showGameDetailLayer(appId);
                }
            },function () {
            });
        },

        homeTabSelect:function(tab){
            var newRelease=$('#tab_newreleases_content_trigger')[0].getAttribute('class');
            var hotSell=$('#tab_topsellers_content_trigger')[0].getAttribute('class');
            var upComing=$('#tab_upcoming_content_trigger')[0].getAttribute('class');
            if (newRelease.indexOf('active')!=-1){
                $('#tab_newreleases_content_trigger')[0].setAttribute('class','home_tab');
                $('#tab_newreleases_content')[0].setAttribute('style','display: none;');
                $('#tab_upcoming_content')[0].setAttribute('style','display: none;');
                $('#tab_topsellers_content')[0].setAttribute('style','display: none;');
            }
            if (hotSell.indexOf('active')!=-1){
                $('#tab_topsellers_content_trigger')[0].setAttribute('class','home_tab');
                $('#tab_newreleases_content')[0].setAttribute('style','display: none;');
                $('#tab_upcoming_content')[0].setAttribute('style','display: none;');
                $('#tab_topsellers_content')[0].setAttribute('style','display: none;');
            }
            if (upComing.indexOf('active')!=-1){
                $('#tab_upcoming_content_trigger')[0].setAttribute('class','home_tab');
                $('#tab_newreleases_content')[0].setAttribute('style','display: none;');
                $('#tab_upcoming_content')[0].setAttribute('style','display: none;');
                $('#tab_topsellers_content')[0].setAttribute('style','display: none;');
            }
            if (tab == 'tab_newreleases_content'){
                $('#tab_newreleases_content_trigger')[0].setAttribute('class','home_tab active');
                $('#tab_newreleases_content')[0].setAttribute('style','');
            }
            if (tab == 'tab_topsellers_content'){
                $('#tab_topsellers_content_trigger')[0].setAttribute('class','home_tab active');
                $('#tab_topsellers_content')[0].setAttribute('style','');
            }
            if (tab == 'tab_upcoming_content'){
                $('#tab_upcoming_content_trigger')[0].setAttribute('class','home_tab active');
                $('#tab_upcoming_content')[0].setAttribute('style','');
            }
        },

        mouseFeturedCarouselPause:function(th){
            $('#home_maincap_v7').hover(function () {
                window.clearInterval(th.featuredTimer)
            },function () {
                th.featuredLeftCarouselStart(th)
            });
        },

        mouseChangeImage:function(){
            //var length=$('#featuredCarousel a.store_main_capsule.broadcast_capsule.app_impression_tracked div.info .screenshots>div>div').length;
            $('#featuredCarousel a.store_main_capsule.broadcast_capsule.app_impression_tracked div.info .screenshots>div').mouseover(function(){
                var imageUrl=$(this).children('div')[0].getAttribute('data-background-image-url');
                var url='background-image:url("'+imageUrl+'")';
                //console.log($(this).children('div')[0].getAttribute('data-background-image-url'))
                $('#featuredCarousel a.store_main_capsule.broadcast_capsule.app_impression_tracked.focus>div.capsule.main_capsule')[0].setAttribute('style',url);
            }).mouseout(function(){
                var imageUrl=$('#featuredCarousel a.store_main_capsule.broadcast_capsule.app_impression_tracked.focus>div.capsule.main_capsule')[0].getAttribute('data-background-image-url');
                var url='background-image:url("'+imageUrl+'")';
                $('#featuredCarousel a.store_main_capsule.broadcast_capsule.app_impression_tracked.focus>div.capsule.main_capsule')[0].setAttribute('style',url);
            });
        },

        featuredLeftCarouselRight:function(){
           var right= $('#featuredCarousel a.store_main_capsule.broadcast_capsule.app_impression_tracked');
           var oldElement=right[(featuredValue)%12].getAttribute('class');
           var newElement=right[(featuredValue+1)%12].getAttribute('class');
           oldElement=oldElement.replace('focus','');
           newElement=newElement.concat(' focus')
           $('#featuredCarousel a.store_main_capsule.broadcast_capsule.app_impression_tracked')[(featuredValue)%12].setAttribute('class',oldElement);
           $('#featuredCarousel a.store_main_capsule.broadcast_capsule.app_impression_tracked')[(featuredValue+1)%12].setAttribute('class',newElement);
           var thumbs=$('#featuredCarousel_thumbs div');
           oldElement=thumbs[featuredValue%12].getAttribute('class');
           newElement=thumbs[(featuredValue+1)%12].getAttribute('class');
           oldElement=oldElement.replace('focus','');
           newElement=newElement.concat('focus');
           $('#featuredCarousel_thumbs div')[featuredValue%12].setAttribute('class',oldElement);
           $('#featuredCarousel_thumbs div')[(featuredValue+1)%12].setAttribute('class',newElement);
           featuredValue++;
        },


        featuredLeftCarouselLeft:function(){
            var right= $('#featuredCarousel a.store_main_capsule.broadcast_capsule.app_impression_tracked');
            var oldElement=right[(featuredValue)%12].getAttribute('class');
            var newElement=right[(featuredValue-1)%12].getAttribute('class');
            oldElement=oldElement.replace('focus','');
            newElement=newElement.concat(' focus')
            $('#featuredCarousel a.store_main_capsule.broadcast_capsule.app_impression_tracked')[(featuredValue)%12].setAttribute('class',oldElement);
            $('#featuredCarousel a.store_main_capsule.broadcast_capsule.app_impression_tracked')[(featuredValue-1)%12].setAttribute('class',newElement);
            var thumbs=$('#featuredCarousel_thumbs div');
            oldElement=thumbs[featuredValue%12].getAttribute('class');
            newElement=thumbs[(featuredValue-1)%12].getAttribute('class');
            oldElement=oldElement.replace('focus','');
            newElement=newElement.concat('focus');
            $('#featuredCarousel_thumbs div')[featuredValue%12].setAttribute('class',oldElement);
            $('#featuredCarousel_thumbs div')[(featuredValue-1)%12].setAttribute('class',newElement);
            featuredValue--;
        },


        featuredLeftCarouselStart:function (th) {
            th.featuredTimer=setInterval(this.featuredLeftCarouselRight,7000);
        },

        specialRightCarousel:function () {
            var right= $('#specialCarous>div');
            var oldElement=right[(specialValue)%2].getAttribute('class');
            var newElement=right[(specialValue+1)%2].getAttribute('class');
            if (newElement==undefined)
                newElement='';
            oldElement=oldElement.replace('focus','');
            newElement=newElement.concat('focus')
            $('#specialCarous>div')[(specialValue)%2].setAttribute('class',oldElement);
            $('#specialCarous>div')[(specialValue+1)%2].setAttribute('class',newElement);
            var thumbs=$('#special_thumbs div');
            oldElement=thumbs[specialValue%2].getAttribute('class');
            newElement=thumbs[(specialValue+1)%2].getAttribute('class');
            if (newElement==undefined)
                newElement='';
            oldElement=oldElement.replace('focus','');
            newElement=newElement.concat('focus');
            $('#special_thumbs div')[specialValue%2].setAttribute('class',oldElement);
            $('#special_thumbs div')[(specialValue+1)%2].setAttribute('class',newElement);
            specialValue++;
        },

        specialLeftCarousel:function () {
            var right= $('#specialCarous>div');
            var oldElement=right[(specialValue)%2].getAttribute('class');
            var newElement=right[(specialValue-1)%2].getAttribute('class');
            if (newElement==undefined)
                newElement='';
            oldElement=oldElement.replace('focus','');
            newElement=newElement.concat(' focus')
            $('#specialCarous>div')[(specialValue)%2].setAttribute('class',oldElement);
            $('#specialCarous>div')[(specialValue-1)%2].setAttribute('class',newElement);
            var thumbs=$('#special_thumbs div');
            oldElement=thumbs[specialValue%2].getAttribute('class');
            newElement=thumbs[(specialValue-1)%2].getAttribute('class');
            if (newElement==undefined)
                newElement='';
            oldElement=oldElement.replace('focus','');
            newElement=newElement.concat('focus');
            $('#special_thumbs div')[specialValue%2].setAttribute('class',oldElement);
            $('#special_thumbs div')[(specialValue-1)%2].setAttribute('class',newElement);
            specialValue--;
        },

    }

    steam.init();
    //steam.featuredLeftCarouselStart();

    //主页切换选项
    function HomeTabSelect(tab) {
        steam.homeTabSelect(tab);
    }

    //展示菜单
    function showMenu() {
        var value=$('#account_dropdown')[0].getAttribute('style');
        if (value.indexOf('display: none;')!=-1) {
            value=value.replace('display: none;','');
            $('#account_dropdown')[0].setAttribute('style',value);
        }else {
            value=value.concat('display: none;');
            $('#account_dropdown')[0].setAttribute('style',value);
        }
    }

    //倒计时
    function countDown(endTime,id){
        // console.log()
        var start = new Date();  //开始时间
        var end = new Date(endTime);//结束时间，可以设置时间
        //parseInt()取整
        var result = parseInt((end.getTime()-start.getTime())/1000);//计算秒
        //var d = parseInt(result/(24*60*60));//用总共的秒数除以1天的秒数
        var h = parseInt(result/(60*60));//精确小时，用去余
        var m = parseInt(result/60%60);//剩余分钟就是用1小时等于60分钟进行趣余
        var s = parseInt(result%60);
        var times=h+':'+m+':'+s;
       if (id=='captchaRefreshLink'){
           $('#captchaRefreshLink')[0].setAttribute('value',times);
       }
       if (id=='dailydeal_timer_b9523fb88ff59a2dd944e424') {
           //console.log($('#dailydeal_timer_b9523fb88ff59a2dd944e424'))
            $('#dailydeal_timer_b9523fb88ff59a2dd944e424')[0].innerHTML = times;
           //document.getElementById('dailydeal_timer_b9523fb88ff59a2dd944e424').innerHTML(times);
       }
        setTimeout(countDown,500,endTime,id);
        //当倒计时结束时，改变内容
        if(result<=0){

        }
    }

    function classTabSelect(selects) {
        $('#tab_select_NewReleases')[0].setAttribute('class','tab  tab_filler');
        $('#tab_select_TopSellers')[0].setAttribute('class','tab  tab_filler');
        $('#tab_select_ComingSoon')[0].setAttribute('class','tab  tab_filler');
        $('#tab_content_NewReleases')[0].setAttribute('style','display: none;');
        $('#tab_content_TopSellers')[0].setAttribute('style','display: none;');
        $('#tab_content_ComingSoon')[0].setAttribute('style','display: none;');
        if (selects == 'NewReleases'){
            $('#tab_select_NewReleases')[0].setAttribute('class','tab  tab_filler active');
            $('#tab_content_NewReleases')[0].setAttribute('style','display: block;');
        }
        if (selects == 'TopSellers'){
            $('#tab_select_TopSellers')[0].setAttribute('class','tab  tab_filler active');
            $('#tab_content_TopSellers')[0].setAttribute('style','display: block;');
        }
        if (selects == 'ComingSoon'){
            $('#tab_select_ComingSoon')[0].setAttribute('class','tab  tab_filler active');
            $('#tab_content_ComingSoon')[0].setAttribute('style','display: block;');
        }
    }

    //展示更多发行商
    function showMorePublisher() {
        $('#publisherValue .summary.column')[0].setAttribute('style','overflow: visible; white-space: normal;');
        $('#publisherValue .more_btn')[0].setAttribute('style','display:none;');
    }

    //登录操作
    function login() {
        var email=$('#input_username').val();
        var password=$('#input_password').val();
        password=""+salt.charAt(0)+salt.charAt(4)+password+salt.charAt(5)+salt.charAt(2);
        password=md5(password);
        var loadId=layer.load(1, {
            shade: [0.1,'#fff'] //0.1透明度的白色背景
        });
        $.ajax({
            url:"/userVerification",
            type:"POST",
            data:{
              email:email,
              password:password  
            },
            success:function (data) {
                layer.close(loadId);
                data=eval('('+data+')');
                if (data.code==201){
                    layer.msg(data.msg)
                    window.location.href="/";
                }
                if (data.code==501) {
                    layer.msg(data.msg)
                }
                if (data.code==502){
                    layer.msg(data.msg)
                }
                if (data.code==503) {
                    layer.msg(data.msg);
                }

            },
            error:function () {
                layer.close(loadId);
                layer.msg("网络错误");
            }
        });
    }