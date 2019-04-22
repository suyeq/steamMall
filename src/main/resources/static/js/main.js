var featuredValue=12000;
var specialValue=12000;
var steam=
    {
        //精选
        featuredLeft:$('#featured-left'),
        featuredRight:$('#featured-right'),
        //特殊推荐
        specialLeft:$('#specialleft'),
        specialRight:$('#specialright'),
        timer:null,

        init:function() {
            var that = this;
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
            this.featuredLeftCarouselStart(that);
            //console.log(this.timer);
            this.mouseCarouselPause(that);
            this.mouseChangeImage();
            this.mouseTabPause();
        },

        mouseTabPause:function(){
            var indexPreNewReleases=0;
            var indexPreHotSeller=0;
            var indexUpComing=0;
            $('#tab_newreleases_content>a').hover(function () {
                var classValue=$(this)[0].getAttribute('class');
                if (classValue.indexOf('focus')!=-1)
                    return;
                var indexNext=$('#tab_newreleases_content>a').index(this);
                $('#tab_newreleases_content>a').eq(indexPreNewReleases)[0].setAttribute('class','tab_item app_impression_tracked');
                $(this)[0].setAttribute('class','tab_item app_impression_tracked focus');
                indexPreNewReleases=indexNext;
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

        mouseCarouselPause:function(th){
            $('#home_maincap_v7').hover(function () {
                window.clearInterval(th.timer)
            },function () {
                th.featuredLeftCarouselStart(th)
            });
        },

        mouseChangeImage:function(){
            var length=$('#featuredCarousel a.store_main_capsule.broadcast_capsule.app_impression_tracked div.info .screenshots>div>div').length;
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
            th.timer=setInterval(this.featuredLeftCarouselRight,7000);
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
    function HomeTabSelect(tab) {
        steam.homeTabSelect(tab);
    }

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