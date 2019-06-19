var featuredValue=12000;
var specialValue=12000;
var classRecommendValue=12000;
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

        classNewReleasePrev:$('#NewReleases_btn_prev'),
        classNewReleaseNext:$('#NewReleases_btn_next'),

        classHotSellPre:$('#TopSellers_btn_prev'),
        classHotSellNext:$('#TopSellers_btn_next'),
        //ComingSoon_ctn
        classUpComingPre:$('#ComingSoon_btn_prev'),
        classUpComingNext:$('#ComingSoon_btn_next'),

        newReleaseIndexPre:$('#NewReleases_index_btn_prev'),
        newReleaseIndexNext:$('#NewReleases_index_btn_next'),

        hotSellIndexPre:$('#HotSell_index_btn_prev'),
        hotSellIndexNext:$('#HotSell_index_btn_next'),

        upComingIndexPre:$('#UpComing_index_btn_prev'),
        upComingIndexNext:$('#UpComing_index_btn_next'),



        init:function() {
            var that = this;
            this.showFeturedData();
            this.showSpecialData();
            this.newReleaseGameLoadMore(that);
            this.hotSellGameLoadMore(that);
            this.upComingGameLoadMore(that);
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
            this.newReleaseIndexPre.click(function () {
                var page=$('#NewReleases_index')[0].getAttribute('page-id');
                page=parseInt(page);
                if (page-1<0){
                    layer.msg('不能再往前了');
                }else{
                    page--;
                    $('#NewReleases_index')[0].setAttribute('page-id',page);
                    that.newReleaseGameLoadMore(that);
                }
            });
            this.newReleaseIndexNext.click(function () {
                var page=$('#NewReleases_index')[0].getAttribute('page-id');
                page=parseInt(page);
                page++;
                $('#NewReleases_index')[0].setAttribute('page-id',page);
                that.newReleaseGameLoadMore(that);
            });

            this.hotSellIndexPre.click(function () {
                var page=$('#HotSell_index')[0].getAttribute('page-id');
                page=parseInt(page);
                if (page-1<0){
                    layer.msg('不能再往前了');
                }else{
                    page--;
                    $('#HotSell_index')[0].setAttribute('page-id',page);
                    that.hotSellGameLoadMore(that);
                }
            });
            this.hotSellIndexNext.click(function () {
                var page=$('#HotSell_index')[0].getAttribute('page-id');
                page=parseInt(page);
                page++;
                $('#HotSell_index')[0].setAttribute('page-id',page);
                that.hotSellGameLoadMore(that);
            });

            this.upComingIndexPre.click(function () {
                var page=$('#UpComing_index')[0].getAttribute('page-id');
                page=parseInt(page);
                if (page-1<0){
                    layer.msg('不能再往前了');
                }else{
                    page--;
                    $('#UpComing_index')[0].setAttribute('page-id',page);
                    that.upComingGameLoadMore(that);
                }
            });
            this.upComingIndexNext.click(function () {
                var page=$('#UpComing_index')[0].getAttribute('page-id');
                page=parseInt(page);
                page++;
                $('#UpComing_index')[0].setAttribute('page-id',page);
                that.upComingGameLoadMore(that);
            });

            this.featuredLeftCarouselStart(that);
            //console.log(this.timer);
            this.mouseFeturedCarouselPause(that);
            this.mouseChangeImage();
        },

        initDetail:function(){
            var that=this;
            var id=$('#gameDetail')[0].getAttribute('game-id');
            this.showGameDetail(that,id);
            this.detailLeft.click(function () {
                that.detailCarouselLeft();
            });
            this.detailRight.click(function () {
                that.detailCarouselRight();
            });
            this.detailCarouselStart(that);
            this.mouseDetailPause(that);
            this.scrollLoadComment(that);
        },

        initClass:function(){
            var that=this;
            var typeName=$('#type')[0].getAttribute('type-name');
            console.log(typeName)
            this.showClassCarouselData(typeName);
            this.loadSpikeGame();
            this.loadTypeLabel();
            this.classNewReleaseGameLoadMore(typeName,that);
            this.classHotSellGameLoadMore(typeName,that);
            this.classUpComingGameLoadMore(typeName,that);
            this.classLeft.click(function () {
                that.classLeftCarousel();
            });
            this.classRight.click(function () {
                that.classRightCarousel();
            });
            this.classNewReleasePrev.click(function () {
                var page=$('#NewReleases_ctn')[0].getAttribute('page-id');
                page=parseInt(page);
                if (page-1<0){
                    layer.msg('不能再向前了')
                }else {
                    page--;
                    $('#NewReleases_ctn')[0].setAttribute('page-id',page);
                    that.classNewReleaseGameLoadMore(typeName,that);
                }
            });
            this.classNewReleaseNext.click(function () {
                var page=$('#NewReleases_ctn')[0].getAttribute('page-id');
                page=parseInt(page);
                page++;
                $('#NewReleases_ctn')[0].setAttribute('page-id',page);
                that.classNewReleaseGameLoadMore(typeName,that);
            });
            this.classHotSellPre.click(function () {
                var page=$('#TopSellers_ctn')[0].getAttribute('page-id');
                page=parseInt(page);
                if (page-1<0){
                    layer.msg('不能再向前了')
                }else {
                    page--;
                    $('#TopSellers_ctn')[0].setAttribute('page-id',page);
                    that.classHotSellGameLoadMore(typeName,that);
                }
            });
            this.classHotSellNext.click(function () {
                var page=$('#TopSellers_ctn')[0].getAttribute('page-id');
                page=parseInt(page);
                page++;
                $('#TopSellers_ctn')[0].setAttribute('page-id',page);
                that.classHotSellGameLoadMore(typeName,that);
            });
            this.classUpComingPre.click(function () {
                var page=$('#ComingSoon_ctn')[0].getAttribute('page-id');
                page=parseInt(page);
                if (page-1<0){
                    layer.msg('不能再向前了')
                }else {
                    page--;
                    $('#ComingSoon_ctn')[0].setAttribute('page-id',page);
                    that.classUpComingGameLoadMore(typeName,that);
                }
            });
            this.classUpComingNext.click(function () {
                var page=$('#ComingSoon_ctn')[0].getAttribute('page-id');
                page=parseInt(page);
                page++;
                $('#ComingSoon_ctn')[0].setAttribute('page-id',page);
                that.classUpComingGameLoadMore(typeName,that);
            });
            this.classCarouselStart(that);
            this.mouseClassCarouselPause(that);
        },

        initCart:function(){
            var that=this;
            this.loadShoppingCart();
            this.loadSpikeGame();
        },

        initSearch:function(){
            this.loadSearchResult();
        },

        loadCommentDecriptionByGameId:function(gameId){
            var result;
            $.ajax({
                url:"/comment/statu/"+gameId,
                type:"POST",
                async:false,
                success:function (data) {
                    data=eval("("+data+")");
                    result= data.msg;
                }
            });
            console.log(result)
            return result;
        },

        loadCommentTotalByGameId:function(gameId){
            var result;
            $.ajax({
                url:"/comment/num/"+gameId,
                type:"POST",
                async:false,
                success:function (data) {
                    data=eval("("+data+")");
                    result=data.msg;
                }
            });
            return result;
        },

        loadSearchResult:function(){
            var content=$('#search')[0].getAttribute('content');
            $.ajax({
                url:"/searchresult",
                type:"POST",
                async:false,
                data:{
                  content:content
                },
                success:function (data) {
                    data=eval("("+data+")");
                    console.log(data)
                    $('#search_result_container').empty();
                    for (var i=0;i<data.msg.length;i++){
                        var parent='<a href="'+'/detail/'+data.msg[i].id+'"  class="search_result_row ds_collapse_flag  app_impression_tracked"></a>';
                        var img='<div class="col search_capsule"><img style="width: 123px;height: 45px;" src="'+data.msg[i].posterImage+'"></div>';
                        var gameMsg='<div class="responsive_search_name_combined"></div>';
                        var namePlatForm='<div class="col search_name ellipsis"><span class="title">'+data.msg[i].gameName+'</span><p><span class="platform_img win"></span></p></div>';
                        var time=parseInt(data.msg[i].issuedDate);
                        time = new Date(time);
                        Y = time.getFullYear() + '年';
                        M = (time.getMonth()+1 < 10 ? '0'+(time.getMonth()+1) : time.getMonth()+1) + '月';
                        D = time.getDate() + '日';
                        var date=Y+M+D;
                        date='<div class="col search_released responsive_secondrow">'+date+'</div>';
                        var price='<div class="col search_price_discount_combined responsive_secondrow">';
                        if (data.msg[i].discount>0){
                            price+='<div class="col search_discount responsive_secondrow"><span>-'+(100-data.msg[i].discount)+'%</span></div>';
                        }
                        price+='<div class="col search_price discounted responsive_secondrow">'
                        if (data.msg[i].discount>0){
                            price+='<span style="color: #888888;"><strike>¥'+data.msg[i].gamePrice+'</strike></span><br>¥ '+Math.ceil(data.msg[i].gamePrice*(data.msg[i].discount/100))+'</div></div>';
                        }else {
                            price+='¥ '+data.msg[i].gamePrice+'</div></div>';
                        }
                        parent=$(parent);
                        gameMsg=$(gameMsg);
                        gameMsg.append(namePlatForm,date,price);
                        parent.append(img,gameMsg);
                        $('#search_result_container').append(parent);
                    }
                }
            })
        },

        loadShoppingCart:function(){
          var userId=$('#account_pulldown')[0].getAttribute('user-id');
          $.ajax({
             url:"cart/"+userId,
             type:"POST",
             async:false,
             success:function (data) {
                 $('#cart_row').empty();
                data=eval("("+data+")");
                for (var i=0;i<data.msg.length;i++){
                    var element='<div class="cart_item"'+' id="cart-id-'+data.msg[i].id+'"'+'>'
                    element+='<div class="cart_item_price_container">\n' +
                        '<div class="cart_item_price ">\n' +
                        '<div class="price">¥ ';
                    element+=data.msg[i].gamePrice;
                    element+='</div>\n' +
                        '                                            <a class="remove_link" href="javascript:void(0);" onclick="removeGameOnCart(';
                    element+=data.msg[i].id;
                    element+=')">移除</a>\n' +
                        '                                        </div>\n' +
                        '                                    </div>\n' +
                        '                                    <div class="cart_item_img ">\n' +
                        '                                    \t<a><img style="width: 125px" src="';
                    element+=data.msg[i].gamePoster;
                    element+='" border="0"></a>\n' +
                        '                                    </div>\n' +
                        '                                    <div class="cart_item_desc">\n' +
                        '                                        <div class="cart_item_platform">\n' +
                        '                                            <span class="platform_img win"></span>\t\t\t\t\n' +
                        '                                            </div>';

                    element+= '<a href="/detail/'+data.msg[i].gameId+'">';
                    element+=data.msg[i].gameName;
                    element+='</a><br>\n' +
                        '                                    </div>\n' +
                        '                                    <div style="clear: left"></div>\n' +
                        '                                </div>';
                    element=$(element);
                    $('#cart_row').append(element);
                }
                 $('#cart_row').append('<div class="ds_options"><div></div></div>');
             },
              error:function () {
                layer.msg("网络错误");
              }
          });
        },

        loadTypeLabel:function(){
            $.ajax({
                url:"/type/all",
                type:"POST",
                async:false,
                success:function (data) {
                    data=eval("("+data+")");
                    $('#type_id').empty();
                    $('#type_id').append('<h2>依分类选择</h2>');
                    for (var i=0;i<data.msg.length;i++){
                        var element='<a class="btnv6_blue_hoverfade btn_small_tall" style="margin-left: 2px;width: 48%;" href="'+'/classification/'+data.msg[i]+'"><span>'+data.msg[i]+'</span></a>';
                        element=$(element);
                        $('#type_id').append(element);
                    }
                    $('#type_id').append('<div style="clear: both; margin-bottom: 20px;"></div>');
                }
            })
        },

        loadSpikeGame:function(){
            $.ajax({
                url:"/spikegame",
                type:"POST",
                async:false,
                success:function (data) {
                    data=eval("("+data+")");
                    $('#spike_img a')[0].setAttribute('href','/detail/'+data.msg.gameId);
                    $('#spike_img a img')[0].setAttribute('src',data.msg.posterImage);
                    $('#spike_img a img')[0].setAttribute('style','width: 306px;height: 143px;');
                    $('#spike_price a')[0].setAttribute('href','javascript:void(0);');
                    $('#spike_price a')[0].setAttribute('onclick','spike('+data.msg.id+')');
                    $('#spike_price div div.discount_original_price')[0].innerHTML='¥ '+data.msg.gamePrice;
                    $('#spike_price div div.discount_final_price')[0].innerHTML='¥ '+data.msg.spikePrice;
                    $('#dailydeal_timer')[0].setAttribute('date-time',data.msg.endTime);
                    $('#dailydeal_timer')[0].setAttribute('spike-start-time',data.msg.startTime);
                    $('#dailydeal_timer')[0].setAttribute('spike-end-time',data.msg.endTime);
                },
                error:function () {

                }
            })
        },

        upComingGameLoadMore:function(that){
            var page=$('#UpComing_index')[0].getAttribute('page-id');
            var sum=null;
            $.ajax({
                url:"/upcoming/sum",
                type:"POST",
                async:false,
                success:function (data) {
                    data=eval("("+data+")");
                    sum=data.msg;
                }
            });
            var start=page*10+1;
            var end=page*10+10>sum?sum:page*10+10;
            var pageSum=Math.ceil(sum/10)-1;
            if (page>pageSum){
                layer.msg('不能再往后了')
                var temp=page-1;
                $('#UpComing_index')[0].setAttribute('page-id',temp);
            }else {
                $('#UpComing_index_start')[0].innerHTML=start;
                $('#UpComing_index_end')[0].innerHTML=end;
                $('#UpComing_index_total')[0].innerHTML=sum;
                that.showUpComingData(page,that);
            }
        },

        hotSellGameLoadMore:function(that){
            var page=$('#HotSell_index')[0].getAttribute('page-id');
            var sum=null;
            $.ajax({
                url:"/issued/sum",
                type:"POST",
                async:false,
                success:function (data) {
                    data=eval("("+data+")");
                    sum=data.msg;
                }
            });
            var start=page*10+1;
            var end=page*10+10>sum?sum:page*10+10;
            var pageSum=Math.ceil(sum/10)-1;
            if (page>pageSum){
                layer.msg('不能再往后了')
                var temp=page-1;
                $('#HotSell_index')[0].setAttribute('page-id',temp);
            }else {
                $('#HotSell_index_start')[0].innerHTML=start;
                $('#HotSell_index_end')[0].innerHTML=end;
                $('#HotSell_index_total')[0].innerHTML=sum;
                that.showTopSellerData(page,that);
            }
        },

        newReleaseGameLoadMore:function(that){
          var page=$('#NewReleases_index')[0].getAttribute('page-id');
          var sum=null;
          $.ajax({
              url:"/issued/sum",
              type:"POST",
              async:false,
              success:function (data) {
                  data=eval("("+data+")");
                  sum=data.msg;
              }
          });
            var start=page*10+1;
            var end=page*10+10>sum?sum:page*10+10;
            var pageSum=Math.ceil(sum/10)-1;
            if (page>pageSum){
                layer.msg('不能再往后了')
                var temp=page-1;
                $('#NewReleases_index')[0].setAttribute('page-id',temp);
            }else {
                $('#NewReleases_index_start')[0].innerHTML=start;
                $('#NewReleases_index_end')[0].innerHTML=end;
                $('#NewReleases_index_total')[0].innerHTML=sum;
                that.showNewReleaseData(page,that);
            }
        },

        classUpComingGameLoadMore:function(type,that){
            var page=$('#ComingSoon_ctn')[0].getAttribute('page-id');
            page=parseInt(page);
            var sum=null;
            $.ajax({
                url:"/upComing/classGame/"+type,
                type:"POST",
                async:false,
                success:function (data) {
                    data=eval("("+data+")");
                    if (data.code==200){
                        sum=data.msg;
                    }
                }
            });
            if (sum==0){
                $('#ComingSoon_no_results')[0].setAttribute('style','display: block');
                $('#ComingSoon_ctn')[0].setAttribute('style','display: none');
            }
            var start=page*10+1;
            var end=page*10+10>sum?sum:page*10+10;
            var pageSum=Math.ceil(sum/10)-1;
            if (page>pageSum){
                if (sum!=0){
                    layer.msg('不能再往后了')
                }
                var temp=page-1;
                $('#ComingSoon_ctn')[0].setAttribute('page-id',temp);
            }else {
                $('#ComingSoon_start')[0].innerHTML=start;
                $('#ComingSoon_end')[0].innerHTML=end;
                $('#ComingSoon_total')[0].innerHTML=sum;
                that.showClassupComing(type,page,that);
                $('#ComingSoon_no_results')[0].setAttribute('style','display: none');
            }
        },

        classHotSellGameLoadMore:function(type,that){
            var page=$('#TopSellers_ctn')[0].getAttribute('page-id');
            page=parseInt(page);
            var sum=null;
            $.ajax({
                url:"/issued/classGame/"+type,
                type:"POST",
                async:false,
                success:function (data) {
                    data=eval("("+data+")");
                    if (data.code==200){
                        sum=data.msg;
                    }
                }
            });
            if (sum==0){
                $('#TopSellers_no_results')[0].setAttribute('style','display: block');
                $('#TopSellers_ctn')[0].setAttribute('style','display: none');
            }
            var start=page*10+1;
            var end=page*10+10>sum?sum:page*10+10;
            var pageSum=Math.ceil(sum/10)-1;
            if (page>pageSum){
                if (sum!=0){
                    layer.msg('不能再往后了')
                }
                var temp=page-1;
                $('#TopSellers_ctn')[0].setAttribute('page-id',temp);
            }else {
                $('#TopSellers_start')[0].innerHTML=start;
                $('#TopSellers_end')[0].innerHTML=end;
                $('#TopSellers_total')[0].innerHTML=sum;
                that.showClassHotSell(type,page,that);
                $('#TopSellers_no_results')[0].setAttribute('style','display: none');
            }
        },

         classNewReleaseGameLoadMore:function(type,that){
            var page=$('#NewReleases_ctn')[0].getAttribute('page-id');
            page=parseInt(page);
            var sum=null;
            $.ajax({
                url:"/issued/classGame/"+type,
                type:"POST",
                async:false,
                success:function (data) {
                    data=eval("("+data+")");
                    if (data.code==200){
                        sum=data.msg;
                    }
                }
            });
            if (sum==0){
                $('#NewReleases_no_results')[0].setAttribute('style','display: block');
                $('#NewReleases_ctn')[0].setAttribute('style','display: none');
            }
            var start=page*10+1;
            var end=page*10+10>sum?sum:page*10+10;
            var pageSum=Math.ceil(sum/10)-1;
            if (page>pageSum){
                if (sum!=0){
                    layer.msg('不能再往后了')
                }
                var temp=page-1;
                $('#NewReleases_ctn')[0].setAttribute('page-id',temp);
            }else {
                $('#NewReleases_start')[0].innerHTML=start;
                $('#NewReleases_end')[0].innerHTML=end;
                $('#NewReleases_total')[0].innerHTML=sum;
                that.showClassNewRelease(type,page,that);
                $('#NewReleases_no_results')[0].setAttribute('style','display: none');
            }
        },

        recentCommentLoad:function(){
            var page=$('#ViewAllReviewssummary')[0].getAttribute('page');
            var gameId=$('#ViewAllReviewssummary')[0].getAttribute('gameId');
            $.ajax({
                url:"/commentDetail/"+gameId+"/time/"+page,
                type:"POST",
                async:false,
                success:function (data) {
                    data=eval("("+data+")");
                    for (var i=0;i<data.msg.length;i++){
                        var parent=$('<div class="review_box short"></div>');
                        var element=$('<div></div>');
                        var child1='<a href="" class="short_header tooltip"><img class="review_source tooltip" src="https://store.st.dl.bscstorage.net/public/shared/images/userreviews/icon_review_steam.png">';
                        child1+='<div class="thumb"><img src="';
                        if (data.msg[i].recommendStatu==1){
                            child1+='https://store.st.dl.bscstorage.net/public/shared/images/userreviews/icon_thumbsUp_v6.png';
                        }else {
                            child1+='https://store.st.dl.bscstorage.net/public/shared/images/userreviews/icon_thumbsDown_v6.png';
                        }
                        child1+='" width="40" height="40"></div>';
                        child1+='<div class="persona_name"><span>'+data.msg[i].nickName+'</span></div>';
                        child1+='<div class="hours ellipsis"> '+data.msg[i].playTime+' 小时</div></a>';
                        child1=$(child1);
                        var shortcol=$('<div class="shortcol"></div>');
                        var commentDate='<div class="postedDate">发布于：'
                        var date = new Date(data.msg[i].commentDate);
                        Y = date.getFullYear() + '年';
                        M = date.getMonth()+1 + '月';
                        D = date.getDate() + '日';
                        commentDate+=M+D;
                        commentDate+='</div>';
                        commentDate=$(commentDate);
                        var content='<div class="content">'+data.msg[i].content+'</div>';
                        content=$(content);
                        var space=$('<div class="posted">\n' +
                            '                                        <div class="view_more"><a href="#" onclick="UserReviewShowMore(\'50108315\', \'summary\' ); return false;">展开阅读</a></div>\n' +
                            '                                        &nbsp;\n' +
                            '                                    </div>');
                        var hr=$('<div class="hr"></div>');
                        var control_block='<div class="control_block"><span class="text">这篇评测是否有价值？</span>';
                        control_block+='<a href="javascript:void(0)" onclick="commentZan( \''+data.msg[i].id+'\' )" class="btnv6_grey_black btn_small_thin ico_hover "><span><i class="ico16 thumb_upv6"></i> 是 </span></a>';
                        control_block+='<a href="javascript:void(0)" onclick="commentCai( \''+data.msg[i].id+'\' )" class="btnv6_grey_black btn_small_thin ico_hover " style="margin-left: 5px"><span><i class="ico16 thumb_downv6"></i> 否 </span></a>';
                        control_block+='<a href="javascript:void(0)" onclick="commentHappy( \''+data.msg[i].id+'\' )" class="btnv6_grey_black btn_small_thin ico_hover " style="margin-left: 5px"><span><i class="ico16 funny"></i> 欢乐 </span></a>';
                        control_block+='</div>';
                        control_block=$(control_block);
                        shortcol.append(commentDate,content,space,hr,control_block);
                        var last=$('<div style="clear: left;"></div>');
                        element.append(child1,shortcol,last);
                        parent.append(element);
                        $('#comment_short').append(parent)
                    }
                },
                error:function () {

                }
            })
        },

        scrollLoadComment:function(that){
            $(window).scroll(function (){
                if ($(window).scrollTop() == $(document).height() - $(window).height()) {
                    $('#Reviews_loading')[0].setAttribute('style','display: block');
                    var page=$('#ViewAllReviewssummary')[0].getAttribute('page');
                    var gameId=$('#ViewAllReviewssummary')[0].getAttribute('gameId');
                    $.ajax({
                        url:"/commentDetail/"+gameId+"/zan/"+page,
                        type:"POST",
                        async:false,
                        success:function (data) {
                            data=eval("("+data+")")
                            var parent=new Array(5);
                            for (var i=0;i<data.msg.length;i++){
                                parent[i]=$('<div class="review_box "></div>');
                                var element=$('<div id="commentInfo" commentId="1"></div>');
                                var left=$('<div class="leftcol"></div>');
                                var avatar='<div class="avatar"><a href="';
                                avatar+='/userDetails/'+data.msg[i].userId;
                                avatar+='"><div class="playerAvatar online"><img src="';
                                avatar+=data.msg[i].avatar;
                                avatar+='"></div></a></div>';
                                avatar=$(avatar);
                                var name='<div class="persona_name"><a href="javascript:void(0);" >'+data.msg[i].nickName+'</a></div>';
                                name=$(name);
                                var owned_games='<div class="num_owned_games"><a href="javascript:void(0);" >帐户内拥有 '+data.msg[i].buyGames+' 项产品</a></div>';
                                owned_games=$(owned_games);
                                var num_reviews='<div class="num_reviews"><a href="javascript:void(0);" >'+data.msg[i].commmentNum+'篇评测</a></div>';
                                num_reviews=$(num_reviews);
                                left.append(avatar,name,owned_games,num_reviews);
                                var right=$('<div class="rightcol"></div>');
                                var recommendStatu='<a href="javascript:void(0);" class="vote_header tooltip">';
                                recommendStatu+='<div class="thumb"><img src="';
                                if (data.msg[i].recommendStatu==1){
                                    recommendStatu+='https://store.st.dl.bscstorage.net/public/shared/images/userreviews/icon_thumbsUp_v6.png';
                                }else {
                                    recommendStatu+='https://store.st.dl.bscstorage.net/public/shared/images/userreviews/icon_thumbsDown_v6.png';
                                }
                                recommendStatu+='" width="40" height="40"></div><img class="review_source tooltip"  src="https://store.st.dl.bscstorage.net/public/shared/images/userreviews/icon_review_steam.png">';
                                recommendStatu+='<div class="title ellipsis">';
                                if (data.msg[i].recommendStatu==1){
                                    recommendStatu+='推荐';
                                } else {
                                    recommendStatu+='不推荐';
                                }
                                recommendStatu+='</div><div class="hours ellipsis">总时数 '+data.msg[i].playTime+' 小时</div></a>';
                                recommendStatu=$(recommendStatu);
                                var commentDate='<div class="postedDate">发布于：'
                                var date = new Date(data.msg[i].commentDate);
                                Y = date.getFullYear() + '年';
                                M = date.getMonth()+1 + '月';
                                D = date.getDate() + '日';
                                commentDate+=M+D;
                                commentDate+='</div>';
                                commentDate=$(commentDate);
                                var content='<div class="content">'+data.msg[i].content+'</div>';
                                content=$(content);
                                var space=$('<div class="posted">\n' +
                                    '                                        <div class="view_more"><a href="#" onclick="UserReviewShowMore(\'50108315\', \'summary\' ); return false;">展开阅读</a></div>\n' +
                                    '                                        &nbsp;\n' +
                                    '                                    </div>');
                                var hr=$('<div class="hr"></div>');
                                var control_block='<div class="control_block"><span class="text">这篇评测是否有价值？</span>';
                                control_block+='<a href="javascript:void(0)" onclick="commentZan( \''+data.msg[i].id+'\' )" class="btnv6_grey_black btn_small_thin ico_hover "><span><i class="ico16 thumb_upv6"></i> 是 </span></a>';
                                control_block+='<a href="javascript:void(0)" onclick="commentCai( \''+data.msg[i].id+'\' )" class="btnv6_grey_black btn_small_thin ico_hover " style="margin-left: 5px"><span><i class="ico16 thumb_downv6"></i> 否 </span></a>';
                                control_block+='<a href="javascript:void(0)" onclick="commentHappy( \''+data.msg[i].id+'\' )" class="btnv6_grey_black btn_small_thin ico_hover " style="margin-left: 5px"><span><i class="ico16 funny"></i> 欢乐 </span></a>';
                                control_block+='</div>';
                                control_block=$(control_block);
                                var vote_info='<div class="vote_info">有 ';
                                vote_info+=data.msg[i].zanNum;
                                vote_info+=' 人觉得这篇评测有价值<br>';
                                vote_info+='有 '+data.msg[i].happyNum+' 人觉得这篇评测很欢乐</div>';
                                vote_info=$(vote_info);
                                var last=$('<div style="clear: left;"></div>');
                                right.append(recommendStatu,commentDate,content,space,hr,control_block,vote_info);
                                element.append(left,right,last);
                                parent[i].append(element);
                            }
                            that.recentCommentLoad();
                            console.log(data.msg.length+"cjhsdhjs")
                            if (data.msg.length >= 1){
                                setTimeout(function () {
                                    $('#Reviews_loading')[0].setAttribute('style','display: none');
                                    $('#Reviews_summary')[0].setAttribute('style','display: block;');
                                    $('#comment_detail').append(parent[0],parent[1],parent[2],parent[3],parent[4]);
                                },2000)
                                page=parseInt(page)+1;
                                $('#ViewAllReviewssummary')[0].setAttribute('page',page);
                            }else {
                                $('#Reviews_summary')[0].setAttribute('style','display: block;');
                                $('#Reviews_loading')[0].setAttribute('style','display: none');
                                $('#ViewAllReviewssummary')[0].setAttribute('style','display: block');
                            }
                        },
                        error:function () {

                        }
                    })
                }
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

        showGameDetail:function(that,id){
            $.ajax({
                url:"/app/"+id,
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
                        tags+='<div class="app_tag add_button" onclick="showTagModel()">+</div>';
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
                        if (data.msg.issuedStatu==1){
                            $('#gameBuyName')[0].innerHTML='购买 '+data.msg.gameName;
                        }else {
                            $('#gameBuyName')[0].innerHTML='等待 '+data.msg.gameName+'的发售';
                        }
                        // $('#gameBuyName')[0].innerHTML='购买 '+data.msg.gameName;
                        if (data.msg.issuedStatu==1){
                            var price='';
                            if (data.msg.discount>0){
                                price+='<div class="discount_pct">-'+(100-data.msg.discount)+'%</div>';
                                price+='<div class="discount_prices"><div class="discount_original_price">¥ '+data.msg.gamePrice+'</div>';
                                price+='<div class="discount_final_price">¥ '+Math.ceil(data.msg.gamePrice*(data.msg.discount/100))+'</div></div>';
                            }else {
                                price+='<div class="game_purchase_price price">¥ '+data.msg.gamePrice+'</div>'
                                //price+='<div class="discount_final_price">¥ '+data.msg.gamePrice+'</div>';
                            }
                            $('#gamePrice').empty();
                            $('#gamePrice').append(price);
                        }else {
                            $('#game_price').empty();
                        }

                        var gameAbout='';
                        gameAbout+='<h2>关于这款游戏</h2>';
                        var finalGameAbout=data.msg.gameAbout.replace('/n/r','<br>');
                        finalGameAbout=finalGameAbout.replace('     ','<br>');
                        gameAbout+=finalGameAbout;
                        $('#game_area_description').empty();
                        $('#game_area_description').append(gameAbout);
                        that.showGameSystemNeed(data.msg.lowestSystem,'Left');
                        that.showGameSystemNeed(data.msg.recommendSystem,'Right');
                        $('#ViewAllReviewssummary')[0].setAttribute('gameId',id);
                        var commentDescription=that.loadCommentDecriptionByGameId(id);
                        var commentTotal=that.loadCommentTotalByGameId(id);
                        console.log(commentDescription+" "+commentTotal)
                        $('#comment_description span.game_review_summary').text(commentDescription);
                        $('#comment_description span.responsive_hidden').text("("+commentTotal+")");
                        $('#comment_top_description span.game_review_summary').text(commentDescription);
                        $('#comment_top_description span.comment_total').text("("+commentTotal+" 篇评测)");
                    }
                },
                error:function () {
                    layer.msg("获取游戏详情失败，请检查网络连接")
                }
            })
        },

        showClassLayerData:function(id,that){
            $.ajax({
                url:"/app/"+id,
                type:"POST",
                async:false,
                success:function (data) {
                    data=eval("("+data+")");
                    var item1=$('<div class="hover_top_area" style="display: none;"></div>');
                    var item2='<h4>'+data.msg.gameName+'</h4>';
                    item2=$(item2);
                    var item3='<div class="hover_screenshots">';
                    item3+='<div class="screenshot" style="background-image: url('+data.msg.imageIntro1+')"></div>';
                    item3+='<div class="screenshot" style="background-image: url('+data.msg.imageIntro2+')"></div>';
                    item3+='<div class="screenshot" style="background-image: url('+data.msg.imageIntro3+')"></div>';
                    item3+='<div class="screenshot" style="background-image: url('+data.msg.imageIntro4+')"></div>';
                    item3+='</div>';
                    item3=$(item3);
                    var item4='<div class="hover_body">\n' +
                        '                            <div class="hover_review_summary" id="comment_class_description">\n' +
                        '                                <div class="title">总体用户评测：</div>\n' +
                        '                                <span class="game_review_summary mixed">褒贬不一</span>\n' +
                        '                                (41 篇评测)\n' +
                        '                            </div>\n' +
                        '                            <div style="clear: left;"></div>\n' +
                        '                        </div>';
                    item4=$(item4);
                    var item5='<div class="hover_body">用户标签：<div class="hover_tag_row">';
                    var length=data.msg.label.length>7?7:data.msg.label.length;
                    for (var i=0;i<length;i++){
                        item5+='<div class="app_tag">'+data.msg.label[i]+'</div>';
                    }
                    item5+='</div></div>';
                    item5=$(item5);
                    $('#hover_app').empty();
                    $('#hover_app').append(item1,item2,item3,item4,item5);
                    var commentDescription=that.loadCommentDecriptionByGameId(id);
                    var commentTotal=that.loadCommentTotalByGameId(id);
                    $('#comment_class_description').empty();
                    $('#comment_class_description').append('<div class="title">总体用户评测：</div>\n' +
                    '                                <span class="game_review_summary mixed">'+commentDescription+'</span>\n' +
                    '                                ('+commentTotal+' 篇评测)\n');
                },
                error:function () {
                    layer.msg("网络错误");
                }
            })
        },

        showClassupComing:function(typeName,page,that){
            $.ajax({
                url:"/classGame/upComing/"+typeName+"/"+page,
                type:"POST",
                async:true,
                success:function (data) {
                    data=eval("("+data+")");
                    $('#ComingSoonRows').empty();
                    for (var i=0;i<data.msg.length;i++){
                        var parent='<a href="/detail/'+data.msg[i].id+'" class="tab_item  " appId="'+data.msg[i].id+'"></a>';
                        parent=$(parent);
                        var tab_item_cap='<div class="tab_item_cap"><img class="tab_item_cap_img" style="width: 184px;height: 69px;" src="'+data.msg[i].posterImage+'"></div>';
                        tab_item_cap=$(tab_item_cap);
                        // var discount='<div class="discount_block tab_item_discount">';
                        // if (data.msg[i].discount>0){
                        //     discount+='<div class="discount_pct">-'+data.msg[i].discount+'%</div>';
                        // }
                        // discount+='<div class="discount_prices">';
                        // if (data.msg[i].discount>0){
                        //     discount+='<div class="discount_original_price">¥ '+data.msg[i].gamePrice+'</div>'
                        //     discount+='<div class="discount_final_price">¥ '+Math.ceil(data.msg[i].gamePrice*(data.msg[i].discount/100))+'</div>';
                        // }else {
                        //     discount+='<div class="discount_final_price">¥ '+data.msg[i].gamePrice+'</div>';
                        // }
                        // discount+='</div></div>';
                        // discount=$(discount);
                        var tab_item_content='<div class="tab_item_content">';
                        tab_item_content+='<div class="tab_item_name">'+data.msg[i].gameName+'</div>';
                        tab_item_content+='<div class="tab_item_details"><span class="platform_img win"></span>';
                        tab_item_content+='<div class="tab_item_top_tags">';
                        var length=data.msg[i].label.length>6?6:data.msg[i].label.length;
                        for (var j=0;j<length;j++){
                            if (j==0){
                                tab_item_content+='<span class="top_tag">'+data.msg[i].label[j]+'</span>';
                            } else {
                                tab_item_content+='<span class="top_tag">, '+data.msg[i].label[j]+'</span>';
                            }
                        }
                        tab_item_content+='</div></div></div>';
                        tab_item_content=$(tab_item_content);
                        var lastItem=$('<div style="clear: both;"></div>');
                        parent.append(tab_item_cap,tab_item_content,lastItem);
                        $('#ComingSoonRows').append(parent);
                    }
                    that.mouseClassUpComingListPause(that);
                },
                error:function () {
                    layer.msg("网络错误");
                }
            })
        },

        showClassHotSell:function(typeName,page,that){
            $.ajax({
                url:"/classGame/hotSell/"+typeName+"/"+page,
                type:"POST",
                async:true,
                success:function (data) {
                    data=eval("("+data+")");
                    $('#TopSellersRows').empty();
                    for (var i=0;i<data.msg.length;i++){
                        var parent='<a href="/detail/'+data.msg[i].id+'" class="tab_item  " appId="'+data.msg[i].id+'"></a>';
                        parent=$(parent);
                        var tab_item_cap='<div class="tab_item_cap"><img class="tab_item_cap_img" style="width: 184px;height: 69px;" src="'+data.msg[i].posterImage+'"></div>';
                        tab_item_cap=$(tab_item_cap);
                        var discount='<div class="discount_block tab_item_discount">';
                        if (data.msg[i].discount>0){
                            discount+='<div class="discount_pct">-'+data.msg[i].discount+'%</div>';
                        }
                        discount+='<div class="discount_prices">';
                        if (data.msg[i].discount>0){
                            discount+='<div class="discount_original_price">¥ '+data.msg[i].gamePrice+'</div>'
                            discount+='<div class="discount_final_price">¥ '+Math.ceil(data.msg[i].gamePrice*(data.msg[i].discount/100))+'</div>';
                        }else {
                            discount+='<div class="discount_final_price">¥ '+data.msg[i].gamePrice+'</div>';
                        }
                        discount+='</div></div>';
                        discount=$(discount);
                        var tab_item_content='<div class="tab_item_content">';
                        tab_item_content+='<div class="tab_item_name">'+data.msg[i].gameName+'</div>';
                        tab_item_content+='<div class="tab_item_details"><span class="platform_img win"></span>';
                        tab_item_content+='<div class="tab_item_top_tags">';
                        var length=data.msg[i].label.length>6?6:data.msg[i].label.length;
                        for (var j=0;j<length;j++){
                            if (j==0){
                                tab_item_content+='<span class="top_tag">'+data.msg[i].label[j]+'</span>';
                            } else {
                                tab_item_content+='<span class="top_tag">, '+data.msg[i].label[j]+'</span>';
                            }
                        }
                        tab_item_content+='</div></div></div>';
                        tab_item_content=$(tab_item_content);
                        var lastItem=$('<div style="clear: both;"></div>');
                        parent.append(tab_item_cap,discount,tab_item_content,lastItem);
                        $('#TopSellersRows').append(parent);
                    }
                    that.mouseClassHotSellListPause(that);

                },
                error:function () {
                    layer.msg("网络错误");
                }
            })
        },

        showClassNewRelease:function(typeName,page,that){
            console.log(3)
            $.ajax({
                url:"/classGame/newRelease/"+typeName+"/"+page,
                type:"POST",
                async:false,
                success:function (data) {
                    data=eval("("+data+")");
                    $('#NewReleasesRows').empty();
                    for (var i=0;i<data.msg.length;i++){
                        var parent='<a href="/detail/'+data.msg[i].id+'" class="tab_item  " appId="'+data.msg[i].id+'"></a>';
                        parent=$(parent);
                        var tab_item_cap='<div class="tab_item_cap"><img class="tab_item_cap_img" style="width: 184px;height: 69px;" src="'+data.msg[i].posterImage+'"></div>';
                        tab_item_cap=$(tab_item_cap);
                        var discount='<div class="discount_block tab_item_discount">';
                        if (data.msg[i].discount>0){
                            discount+='<div class="discount_pct">-'+data.msg[i].discount+'%</div>';
                        }
                        discount+='<div class="discount_prices">';
                        if (data.msg[i].discount>0){
                            discount+='<div class="discount_original_price">¥ '+data.msg[i].gamePrice+'</div>'
                            discount+='<div class="discount_final_price">¥ '+Math.ceil(data.msg[i].gamePrice*(data.msg[i].discount/100))+'</div>';
                        }else {
                            discount+='<div class="discount_final_price">¥ '+data.msg[i].gamePrice+'</div>';
                        }
                        discount+='</div></div>';
                        discount=$(discount);
                        var tab_item_content='<div class="tab_item_content">';
                        tab_item_content+='<div class="tab_item_name">'+data.msg[i].gameName+'</div>';
                        tab_item_content+='<div class="tab_item_details"><span class="platform_img win"></span>';
                        tab_item_content+='<div class="tab_item_top_tags">';
                        var length=data.msg[i].label.length>6?6:data.msg[i].label.length;
                        for (var j=0;j<length;j++){
                            if (j==0){
                                tab_item_content+='<span class="top_tag">'+data.msg[i].label[j]+'</span>';
                            } else {
                                tab_item_content+='<span class="top_tag">, '+data.msg[i].label[j]+'</span>';
                            }
                        }
                        tab_item_content+='</div></div></div>';
                        tab_item_content=$(tab_item_content);
                        var lastItem=$('<div style="clear: both;"></div>');
                        parent.append(tab_item_cap,discount,tab_item_content,lastItem);
                        $('#NewReleasesRows').append(parent);
                    }
                    that.mouseClassNewReleaseListPause(that);

                },
                error:function () {
                    layer.msg("网络错误");
                }
            })
            console.log(4)
        },

        showClassCarouselData:function(typeName){
            $.ajax({
                url:"/classGame/"+typeName,
                type:"POST",
                async:false,
                success:function (data) {
                    data=eval("("+data+")");
                    $('#genre_large_cluster div.carousel_items.store_capsule_container').empty();
                    for (var i=0;i<data.msg.length;i++){
                        var parent='<a href="';
                        parent+='/detail/'+data.msg[i].id;
                        if (i==0){
                            parent+='" class="focus"></a>';
                        } else {
                            parent+='" class=""></a>';
                        }
                        parent=$(parent);
                        var child=$('<div class="main"></div>');
                        var maincap='<img class="maincap" style="width:465px;height:266px;" src="';
                        maincap+=data.msg[i].posterImage;
                        maincap+='">';
                        maincap=$(maincap);
                        var bg='<div class="bg">';
                        bg+='<div style="background-image: url('+data.msg[i].imageIntro1+')"></div>';
                        bg+='<div style="background-image: url('+data.msg[i].imageIntro2+')"></div>';
                        bg+='<div style="background-image: url('+data.msg[i].imageIntro3+')"></div>';
                        bg+='<div style="background-image: url('+data.msg[i].imageIntro4+')"></div>';
                        bg+='</div>';
                        bg=$(bg);
                        var recommendation_reason='<div class="recommendation_reason default">';
                        if (data.msg[i].discount>0){
                            recommendation_reason+='<div><p>打折商品</p></div>';
                        }else {
                            recommendation_reason+='<div><p>热销商品</p></div>';
                        }
                        recommendation_reason+='</div>';
                        recommendation_reason=$(recommendation_reason);
                        var appTitle='<div class="appTitle">';
                        appTitle+='<h2>'+data.msg[i].gameName+'</h2>';
                        appTitle+='<div class="discount_block_large"><div class="discount_block">';
                        if (data.msg[i].discount>0){
                            appTitle+='<div class="discount_pct">'+'-'+data.msg[i].discount+'%'+'</div>';
                        }
                        appTitle+='<div class="discount_prices">';
                        if (data.msg[i].discount>0){
                            appTitle+='<div class="discount_original_price">¥ '+data.msg[i].gamePrice+'</div>';
                            appTitle+='<div class="discount_final_price">¥'+Math.ceil(data.msg[i].gamePrice*(data.msg[i].discount/100))+'</div>';
                        }else {
                            appTitle+='<div class="discount_final_price">¥'+(data.msg[i].gamePrice)+'</div>';
                        }
                        appTitle+='</div></div></div></div>';
                        appTitle=$(appTitle);
                        child.append(maincap,bg,recommendation_reason,appTitle);
                        parent.append(child);
                        $('#genre_large_cluster div.carousel_items.store_capsule_container').append(parent);
                    }
                },
                error:function () {
                    layer.msg("网络错误");
                }
            })
        },

        showGameDetailLayer:function(id,that){
            $.ajax({
                url:"/app/"+id,
                type:"POST",
                async:false,
                success:function (data) {
                    data=eval("("+data+")");
                    console.log(data);
                    $('#tab_preview_container').empty();
                    var parent=$('<div class="tab_preview focus"></div>')
                    var title='<h2>'+data.msg.gameName+'</h2>';
                    title=$(title);
                    var review='<div class="tab_review_summary" id="comment_index_description"><div class="title">总体用户评测：</div><span class="game_review_summary positive">特别好评</span><span class="comment_total">&nbsp;(440)</span></div>';
                    review=$(review);
                    var tag='<div class="tags">';
                    var length=data.msg.label.length>6?6:data.msg.label.length;
                    for (var i=0;i<length;i++){
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
                    var commentDescription=that.loadCommentDecriptionByGameId(id);
                    var commentTotal=that.loadCommentTotalByGameId(id);
                    $('#comment_index_description span.game_review_summary').text(commentDescription);
                    $('#comment_index_description span.comment_total').text(" ("+commentTotal+")");
                },
                error:function () {

                }
            })
        },

        showNewReleaseData:function(page,that){
            $.ajax({
                url:"/newRelease_index/"+page,
                type:"POST",
                async:false,
                success:function (data) {
                    data=eval("("+data+")");
                    $('#newReleaseRow').empty();
                    for (var i=0;i<data.msg.length;i++){
                        var parent='<a href="'
                        parent+='/detail/'+data.msg[i].id+'"';
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
                        var length=data.msg[i].label.length>6?6:data.msg[i].label.length;
                        for (var j=0;j<length;j++){
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
                        $('#newReleaseRow').append(parent);
                    }
                    var flag='#tab_app_'+data.msg[0].id;
                    if ($(flag).length==0){
                        that.showGameDetailLayer(data.msg[0].id,that);
                    }
                    that.mouseNewReleasePause(that);
                },
                error:function () {
                    layer.msg("网络错误");
                }
            });
        },

        showTopSellerData:function(page,th){
            $.ajax({
                url:"/hotSell_index/"+page,
                type:"POST",
                success:function (data) {
                    data=eval("("+data+")");
                    $('#hotSellRow').empty();
                    for (var i=0;i<data.msg.length;i++){
                        var parent='<a href="'
                        parent+='/detail/'+data.msg[i].id+'"';
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
                        var length=data.msg[i].label.length>6?6:data.msg[i].label.length;
                        for (var j=0;j<length;j++){
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
                        $('#hotSellRow').append(parent);
                    }
                    // var seeMore='<div class="tab_see_more">查看更多： <a href="#" class="btnv6_white_transparent btn_small_tall"><span>热销商品</span></a></div>'
                    // $('#tab_topsellers_content').append(seeMore);
                    th.mouseHotSellPause(th);
                },
                error:function () {
                    layer.msg("网络错误");
                }
            });
        },

        showUpComingData:function(page,th){
            $.ajax({
                url:"/upComing_index/"+page,
                type:"POST",
                success:function (data) {
                    data=eval("("+data+")");
                    $('#upComingRow').empty();
                    for (var i=0;i<data.msg.length;i++){
                        var parent='<a href="'
                        parent+='/detail/'+data.msg[i].id+'"';
                        parent+='app-id="'+data.msg[i].id+'"';
                        if (i==0){
                            parent+=' class="tab_item app_impression_tracked focus"></a>';
                        } else {
                            parent+=' class="tab_item app_impression_tracked"></a>';
                        }
                        var child1='<div class="tab_item_cap"><img class="tab_item_cap_img" style="width: 184px;height: 69px;" src="';
                        child1+=data.msg[i].posterImage;
                        child1+='"></div>';
                        // var child2='<div class="discount_block tab_item_discount">';
                        // if (data.msg[i].issuedStatu==1){
                        //     hotseller+='现已推出';
                        // }else {
                        //     child2+='敬请期待';
                        // }
                        // if (data.msg[i].discount>0){
                        //     child2+='<div class="discount_pct">';
                        //     child2+='-'+(100-data.msg[i].discount)+'%';
                        //     child2+='</div>';
                        // }
                        // child2+='<div class="discount_prices">';
                        // if (data.msg[i].discount>0){
                        //     child2+='<div class="discount_original_price">';
                        //     child2+='¥ '+data.msg[i].gamePrice;
                        //     child2+='</div>';
                        // }
                        // if (data.msg[i].discount>0){
                        //     child2+='<div class="discount_final_price">';
                        //     child2+='¥ '+Math.ceil(data.msg[i].gamePrice*(data.msg[i].discount/100));
                        // }else {
                        //     child2+='<div class="discount_final_price">';
                        //     child2+='¥ '+data.msg[i].gamePrice;
                        // }
                        // child2+='</div></div></div>';
                        var child3='<div class="tab_item_content"><div class="tab_item_name">';
                        child3+=data.msg[i].gameName;
                        child3+='</div><div class="tab_item_details"><span class="platform_img win"></span><span class="platform_img mac"></span><span class="platform_img linux"></span><div class="tab_item_top_tags">';
                        var length=data.msg[i].label.length>6?6:data.msg[i].label.length;
                        for (var j=0;j<length;j++){
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
                        //child2=$(child2);
                        child3=$(child3);
                        child4=$(child4);
                        //parent.append(child1,child2,child3,child4);
                        parent.append(child1,child3,child4);
                        $('#upComingRow').append(parent);
                    }
                    // var seeMore='<div class="tab_see_more">查看更多： <a href="#" class="btnv6_white_transparent btn_small_tall"><span>即将推出</span></a></div>'
                    // $('#tab_upcoming_content').append(seeMore);
                    th.mouseUpComingPause(th);
                },
                error:function () {
                    layer.msg("网络错误");
                }
            });
        },


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
                        element[i]+="/detail/"+data.msg[i].id;
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
                            parent+='/detail/'+data.msg[i].id;
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
                                hotseller+='敬请期待';
                            }
                            if (data.msg[i].issuedStatu==1){
                                hotseller+='</div><div class="additional"><div>热销商品</div></div></div>';
                            }else {
                                hotseller+='</div><div class="additional"><div>即将推出</div></div></div>';
                            }
                            //hotseller+='</div><div class="additional"><div>热销商品</div></div></div>';
                            //折扣
                            var discount='<div class="discount_block  discount_block_inline">';
                            if (data.msg[i].issuedStatu==1){
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
                            }else {
                                discount='';
                            }
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

        mouseClassHotSellListPause:function(that){
            var height=null;
            $('#TopSellersRows a').hover(function () {
                var appId=$(this)[0].getAttribute('appId');
                that.showClassLayerData(appId,that);
                var newStyle=$('#global_hover')[0].getAttribute('style').replace('display: none;','display: block;');
                $('#global_hover')[0].setAttribute('style',newStyle);
                var index=$('#TopSellersRows a').index(this);
                console.log(index)
                height=(index%10)*74+664;
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

        mouseClassNewReleaseListPause:function(that){
            var height=null;
            $('#NewReleasesRows a').hover(function () {
                var appId=$(this)[0].getAttribute('appId');
                that.showClassLayerData(appId,that);
                var newStyle=$('#global_hover')[0].getAttribute('style').replace('display: none;','display: block;');
                $('#global_hover')[0].setAttribute('style',newStyle);
                var index=$('#NewReleasesRows a').index(this);
                console.log(index)
                height=(index%10)*74+664;
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

        mouseClassUpComingListPause:function(that){
            var height=null;
            $('#ComingSoonRows a').hover(function () {
                var appId=$(this)[0].getAttribute('appId');
                that.showClassLayerData(appId,that);
                var newStyle=$('#global_hover')[0].getAttribute('style').replace('display: none;','display: block;');
                $('#global_hover')[0].setAttribute('style',newStyle);
                var index=$('#ComingSoonRows a').index(this);
                console.log(index)
                height=(index%10)*74+664;
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
            $('#genre_large_cluster .carousel_items.store_capsule_container>a')[classRecommendValue%10].setAttribute('class','');
            $('#genre_large_cluster .carousel_items.store_capsule_container>a')[(classRecommendValue+1)%10].setAttribute('class','focus');
            $('#genre_large_cluster .carousel_thumbs>div')[classRecommendValue%10].setAttribute('class','');
            $('#genre_large_cluster .carousel_thumbs>div')[(classRecommendValue+1)%10].setAttribute('class','focus');
            classRecommendValue++;
        },

        classLeftCarousel:function(){
            $('#genre_large_cluster .carousel_items.store_capsule_container>a')[classRecommendValue%10].setAttribute('class','');
            $('#genre_large_cluster .carousel_items.store_capsule_container>a')[(classRecommendValue-1)%10].setAttribute('class','focus');
            $('#genre_large_cluster .carousel_thumbs>div')[classRecommendValue%10].setAttribute('class','');
            $('#genre_large_cluster .carousel_thumbs>div')[(classRecommendValue-1)%10].setAttribute('class','focus');
            classRecommendValue--;
        },

        mouseNewReleasePause:function(that){
            var indexPreNewReleases=0;
            $('#newReleaseRow>a').hover(function () {
                var classValue=$(this)[0].getAttribute('class');
                var appId=$(this)[0].getAttribute('app-id');
                if (classValue.indexOf('focus')!=-1)
                    return;
                var indexNext=$('#newReleaseRow>a').index(this);
                $('#newReleaseRow>a').eq(indexPreNewReleases)[0].setAttribute('class','tab_item app_impression_tracked');
                $(this)[0].setAttribute('class','tab_item app_impression_tracked focus');
                indexPreNewReleases=indexNext;
                var flag='#tab_app_'+appId;
                if ($(flag).length==0){
                    that.showGameDetailLayer(appId,that);
                }
            },function () {
            });
        },

        mouseHotSellPause:function(that){
            var indexPreHotSeller=0;
            $('#hotSellRow>a').hover(function () {
                var classValue=$(this)[0].getAttribute('class');
                if (classValue.indexOf('focus')!=-1)
                    return;
                var indexNext=$('#hotSellRow>a').index(this);
                $('#hotSellRow>a').eq(indexPreHotSeller)[0].setAttribute('class','tab_item app_impression_tracked');
                $(this)[0].setAttribute('class','tab_item app_impression_tracked focus');
                indexPreHotSeller=indexNext;
                var appId=$(this)[0].getAttribute('app-id');
                var flag='#tab_app_'+appId;
                if ($(flag).length==0){
                    that.showGameDetailLayer(appId,that);
                }
            },function () {
            });
        },

        mouseUpComingPause:function(that){
            var indexUpComing=0;
            $('#upComingRow>a').hover(function () {
                var classValue=$(this)[0].getAttribute('class');
                if (classValue.indexOf('focus')!=-1)
                    return;
                var indexNext=$('#upComingRow>a').index(this);
                $('#upComingRow>a').eq(indexUpComing)[0].setAttribute('class','tab_item app_impression_tracked');
                $(this)[0].setAttribute('class','tab_item app_impression_tracked focus');
                indexUpComing=indexNext;
                var appId=$(this)[0].getAttribute('app-id');
                var flag='#tab_app_'+appId;
                if ($(flag).length==0){
                    that.showGameDetailLayer(appId,that);
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

    //steam.init();
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
    function countDown(startTime,endTime,id){
        // console.log()
        var now = new Date();  //现在时间
        var spikeEndTime = new Date(endTime);//秒杀结束时间，可以设置时间
        var spikeStartTime = new Date(startTime);//秒杀结束时间，可以设置时间
        //parseInt()取整
        var endResult = parseInt((spikeEndTime.getTime()-now.getTime())/1000);//计算秒
        var startResult = parseInt((spikeStartTime.getTime()-now.getTime())/1000);//计算秒
        console.log("startTime="+spikeStartTime);
        console.log("endTime="+spikeEndTime);
        console.log("now="+now);
        //var d = parseInt(result/(24*60*60));//用总共的秒数除以1天的秒数
        if (startResult>=0){
            $('#spike_statu').text("秒杀倒计时！")
            var sh = parseInt(startResult/(60*60));//精确小时，用去余
            var sm = parseInt(startResult/60%60);//剩余分钟就是用1小时等于60分钟进行趣余
            var ss = parseInt(startResult%60);
            var startTimes=sh+':'+sm+':'+ss;
            if (id=='dailydeal_timer') {
                $('#dailydeal_timer')[0].innerHTML = startTimes;
            }
        }else if (endResult>0){
            $('#spike_statu').text("秒杀中！")
            var eh = parseInt(endResult/(60*60));//精确小时，用去余
            var em = parseInt(endResult/60%60);//剩余分钟就是用1小时等于60分钟进行趣余
            var es = parseInt(endResult%60);
            var endTimes=eh+':'+em+':'+es;
            if (id=='dailydeal_timer') {
                //console.log($('#dailydeal_timer_b9523fb88ff59a2dd944e424'))
                $('#dailydeal_timer')[0].innerHTML = endTimes;
                //document.getElementById('dailydeal_timer_b9523fb88ff59a2dd944e424').innerHTML(times);
            }
        }
        //当倒计时结束时，改变内容
        if(endResult<=0){
            $('#spike_statu').text("秒杀结束！")
            $('#dailydeal_timer')[0].innerHTML="00:00:00";
            return;
        }
        setTimeout(countDown,500,startTime,endTime,id);
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
    //展示标签
    function showTagModel() {
        //app_tagging_modal
        var gameId=$('#gameDetail')[0].getAttribute("game-id");
        if (getCookie("token")!=null){
            $.ajax({
                url:"/label/gameid",
                type:"POST",
                async:false,
                data:{
                    gameId:gameId
                },
                success:function (data) {
                    data=eval("("+data+")");
                    console.log(data);
                    $('#show_tags').empty();
                    for (var i=0;i<data.msg.length;i++){
                        var element='<div class="app_tag_control popular"><a class="app_tag_checkbox" title="赞同该标签" onclick="zanLabel('+data.msg[i].id+')"></a>'
                        element+='<a class="app_tag" href="javascript:void(0);">'+data.msg[i].name+'</a></div>';
                        $('#show_tags').append(element);
                    }
                }
            })
        }
        $('#model_name').empty();
        $('#model_name').append("标签");
        $('#model_bg')[0].setAttribute('style','opacity: 0.8; display: block;');
        $('#model')[0].setAttribute('style','position: fixed; z-index: 1000; max-width: 1269px; left: 424px; top: 67px;display: block;');
        $('#app_tagging_modal')[0].setAttribute('style','display: block;');
    }
    //展示分享
    function showShareModel() {
        $('#model_bg')[0].setAttribute('style','opacity: 0.8; display: block;');
        $('#model')[0].setAttribute('style','position: fixed; z-index: 1000; max-width: 1269px; left: 424px; top: 67px;display: block;');
        $('#ShareModal')[0].setAttribute('style','display: block;');
        $('#model_name').empty();
        $('#model_name').append("分享");
    }
    //关闭弹窗
    function closeModel() {
        $('#model_bg')[0].setAttribute('style','opacity: 0.8; display: none;');
        $('#model')[0].setAttribute('style','position: fixed; z-index: 1000; max-width: 1269px; left: 424px; top: 67px;display: none;');
        $('#ShareModal')[0].setAttribute('style','display: none;');
        $('#app_tagging_modal')[0].setAttribute('style','display: none;');
        $('#app_tagging_modal')[0].setAttribute('style','display: none;');
    }

    function sleep(numberMillis) {
        var now = new Date();
        var exitTime = now.getTime() + numberMillis;
        while (true) {
            now = new Date();
            if (now.getTime() > exitTime)
                return;
        }
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

    //登出
    function logout() {
        var email=$('#account_pulldown')[0].getAttribute("email");
        console.log(email)
        $.ajax({
            url:"/logout",
            type:"POST",
            async:false,
            data:{
               email:email
            },
            success:function (data) {
                data=eval("("+data+")");
                console.log(data)
                clearCookie('token')
                window.location.href="/";
                layer.msg("成功注销");
            },
            error:function () {

            }
        })
    }

    //设置cookie
    function setCookie(cname, cvalue, exdays) {
        var d = new Date();
        d.setTime(d.getTime() + (exdays*24*60*60*1000));
        var expires = "expires="+d.toUTCString();
        document.cookie = cname + "=" + cvalue + "; " + expires;
    }
    //获取cookie
    function getCookie(name)
    {
        var arr,reg=new RegExp("(^| )"+name+"=([^;]*)(;|$)");
        if(arr=document.cookie.match(reg))
            return unescape(arr[2]);
        else
            return null;
    }

    //清除cookie
    function clearCookie(name) {
        setCookie(name, "", -1);
    }
    //判断是否包含此游戏
    function isContainGame(email,gameId) {
        var result=null;
        $.ajax({
            url:"/iscontains",
            type:"POST",
            async:false,
            data:{
              email:email,
              gameId:gameId
            },
            success:function (data) {
                data=eval("("+data+")");
                result=data.msg;
            }
        });
        return result;
    }
    //购物车里是否已有
    function cartIsContainsGame(userId,gameId) {
        var result=null;
        $.ajax({
            url:"/cart/iscontain",
            type:"POST",
            async:false,
            data:{
                userId:userId,
                gameId:gameId
            },
            success:function (data) {
                data=eval("("+data+")");
                result=data.msg;
            }
        });
        return result;
    }

    //添加置购物车
    function addCart() {
        if ($('#account_pulldown').length<1){
            layer.msg("尚未登录");
            return;
        }
        var email=$('#account_pulldown')[0].getAttribute('email');
        var userId=$('#account_pulldown')[0].getAttribute('user-id');
        var gameId=$('#gameDetail')[0].getAttribute('game-id');
        var cartResult=cartIsContainsGame(userId,gameId);
        console.log(cartResult)
        if (cartResult!=false){
            layer.msg("该游戏已在购物车，不能重复添加");
            return;
        }
        var result=isContainGame(email,gameId);
        if (result!=false){
            layer.msg("该游戏已购买，不能重复购买");
            return;
        }
        console.log(userId+" "+gameId)
        $.ajax({
            url:"/cart/add",
            type:"POST",
            async:false,
            data:{
                userId:userId,
                gameId:gameId
            },
            success:function (data) {
                //data=eval("("+data+")");
                layer.msg("添加成功")
            },
            error:function () {

            }
        });
    }

    //移除某个用户购物车里的某一个游戏
    function removeGameOnCart(id) {
        $.ajax({
            url:"/cart/remove/"+id,
            type:"POST",
            async:true,
            success:function () {
                layer.msg("删除成功")
            },
            error:function () {
            }
        });
        var cartid='#cart-id-'+id;
        $(cartid).remove();
    }
    
    //移除某用户的所有购物车里的游戏
    function removeAllGameOnCart() {
    var userId=$('#account_pulldown')[0].getAttribute('user-id');
        $.ajax({
            url:"/cart/removeall/"+userId,
            type:"POST",
            async:true,
            success:function () {
                layer.msg("删除成功")
            },
            error:function () {
            }
        });
        $('#cart_row').empty();
        $('#cart_row').append('<div class="ds_options"><div></div></div>');
    }

    //邮箱验证码倒计时
    function EmailVerificationCountDown(endTime) {
        var now=new Date();
        var result = parseInt((endTime-now.getTime())/1000);
        $('#captchaRefreshLink')[0].setAttribute('value',result+'s');
        if(result<=0){
            $('#captchaRefreshLink')[0].setAttribute('value',"发送");
            $('#captchaRefreshLink').on("click",function(){
                sendEmailverification();
            });
            return;
        }
        setTimeout(EmailVerificationCountDown,500,endTime);
    }

    //发送验证码
    function sendEmailverification() {
        $('#captchaRefreshLink').unbind("click");
        var email=$("#email").val();
        var now=new Date();
        var endTime=now.getTime()+90*1000;
        EmailVerificationCountDown(endTime);
        $.ajax({
            url:"/verificationCode",
            type:"POST",
            data:{
                email:email
            },
            success:function (data) {
                layer.msg("发送成功")
            }
        })
    }

    //注册信息提交
    function registerSubmit() {
        var email=$('#email').val();
        var password=$('#password').val();
        var code=$('#code').val();
        $.ajax({
            url:"/registerVerification",
            type:"POST",
            data:{
              email:email,
              password:password,
              code:code
            },
            success:function (data) {
                data=eval("("+data+")");
                //console.log(data)
                layer.msg(data.msg);
                if (data.code<300){
                    window.location.href="/login";
                }
            },
            error:function () {
                layer.msg("网络错误");
            }
        })
    }
    //评论推荐
    function SetOwnerRatingPositive() {
        $('#VoteUpBtn')[0].setAttribute("style","background:#417a9b");
        $('#VoteDownBtn')[0].setAttribute("style","background:rgba( 103, 193, 245, 0.2 )");
        $('#recommendStatu')[0].setAttribute('value','1');
    }
    //评论不推荐
    function SetOwnerRatingNegative() {
        $('#VoteDownBtn')[0].setAttribute("style","background:#417a9b");
        $('#VoteUpBtn')[0].setAttribute("style","background:rgba( 103, 193, 245, 0.2 )");
        $('#recommendStatu')[0].setAttribute('value','0');
    }

    //增加评论
    function addComment() {
        var content=$('#game_recommendation').val();
        var email=$('#account_pulldown')[0].getAttribute("email");
        var gameId=$('#gameDetail')[0].getAttribute('game-id');
        var recommendStatu=$('#recommendStatu')[0].getAttribute('value');
        if (content.length==0){
            layer.msg("评测不能为空")
        } else {
            if (recommendStatu.length==0){
                recommendStatu=1;
            }
            $.ajax({
                url:"/comment/add",
                type:"POST",
                data:{
                    content:content,
                    email:email,
                    gameId:gameId,
                    recommendStatu:recommendStatu
                },
                success:function () {
                    layer.msg("发表成功")
                }
            });
        }
    }

    //展示分类
    function showClassGameList() {
        var element=$('#genre_flyout')[0].getAttribute('style');
        //console.log('kkkkk')
        if (element=='display: none;'){
            $('#genre_flyout')[0].setAttribute('style','display: block;');
        } else {
            $('#genre_flyout')[0].setAttribute('style','display: none;');
        }
    }

    //秒杀功能
    function spike(spikeId) {
        var loadId=layer.load(1, {
            shade: [0.1,'#fff'] //0.1透明度的白色背景
        });
        var spikeStatu=$('#spike_statu').text();
        console.log(spikeStatu);
        if (spikeStatu=='秒杀结束！'){
            layer.close(loadId);
            layer.msg('秒杀结束');
            return;
        }
        if (spikeStatu=='秒杀倒计时！'){
            layer.close(loadId);
            layer.msg('秒杀尚未开始');
            return;
        }
        $.ajax({
            url:"/spike/"+spikeId,
            type:"POST",
            async:false,
            success:function (data) {
                data=eval("("+data+")");
                //console.log(data)
                if (data.code>500) {
                    layer.close(loadId);
                    layer.msg(data.msg)
                }
                if (data.code==200){
                    spikeResult(data.msg.userId,data.msg.spikeGameId,loadId);
                }
            }
        })
    }

    //秒杀结果轮询操作
    function spikeResult(userId,spikeId,loadId) {
        $.ajax({
            url:"/spike/result",
            type:"POST",
            async:false,
            data:{
                userId:userId,
                spikeId:spikeId
            },
            success:function (data) {
                data=eval("("+data+")");
                if (data.code==510){
                    spikeResult(userId,spikeId);
                }
                if (data.code==203) {
                    layer.close(loadId);
                    layer.msg(data.msg)
                    window.location.href="/cart";
                }
            }
        })
    }

    //搜索
    function searchResult() {
        var content=$('#content_search').val();
        if (content.length==0){
            layer.msg("搜索不能为空")
        }else {
            $('#searchform').submit();
        }
    }
    
    //在搜索页面搜索
    function searchOnPage() {
        var content=$('#term').val();
        console.log(content)
        window.location.href="/search?content="+content;

    }

    //给某个游戏的标签加一个赞同，展示的标签根据赞的多少显示前5个
    function zanLabel(labelId) {
        var gameId=$('#gameDetail')[0].getAttribute('game-id');
        $.ajax({
            url:"/label/updatehot",
            type:"POST",
            data:{
                gameId:gameId,
                labelId:labelId
            },
            success:function () {
                layer.msg("赞同成功")
            },
            error:function () {
                layer.msg("网络错误")
            }
        })
    }
    //增加一个标签
    function addLabel() {
        var gameId=$('#gameDetail')[0].getAttribute('game-id');
        var labelName=$('#addLabel').val();
        $.ajax({
            url:"/label/add",
            type:"POST",
            data:{
                gameId:gameId,
                labelName:labelName
            },
            success:function (data) {
                data=eval("("+data+")");
                if (data.code>500){
                    layer.msg(data.msg)
                }else {
                    var element='<div class="app_tag_control popular"><a class="app_tag_checkbox" title="赞同该标签" onclick="zanLabel('+data.msg+')"></a>'
                    element+='<a class="app_tag" href="javascript:void(0);">'+labelName+'</a></div>';
                    $('#show_tags').append(element);
                    layer.msg("添加成功")
                }
            },
            error:function () {
                layer.msg("网络错误")
            }
        })
    }

    //买游戏
    function finalBuyGame() {
        var email=$('#account_pulldown')[0].getAttribute("email");
        var userId=$('#account_pulldown')[0].getAttribute("user-id");
        $.ajax({
            url:"/buygame",
            type:"POST",
            data:{
                email:email,
                userId:userId
            },
            success:function () {
                $('#cart_row').empty();
                layer.msg("购买成功")
            },
            error:function () {

            }
        })
    }
    //点赞
    function commentZan(commentId) {
        $.ajax({
            url:"/comment/zan/"+commentId,
            type:"POST",
            success:function () {
                layer.msg("点赞成功");
            },
            error:function () {
                layer.msg("网络错误");
            }
        })
    }
    //踩
    function commentCai(commentId) {
        $.ajax({
            url:"/comment/cai/"+commentId,
            type:"POST",
            success:function () {
                layer.msg("踩成功");
            },
            error:function () {
                layer.msg("网络错误");
            }
        })
    }
    //happy
    function commentHappy(commentId) {
        $.ajax({
            url:"/comment/happy/"+commentId,
            type:"POST",
            success:function () {
                layer.msg("欢乐成功");
            },
            error:function () {
                layer.msg("网络错误");
            }
        })
    }

