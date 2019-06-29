    var salt="1q2w3e";
    var MAX_IMAGE_SIZE=10*1024*1024;

    //新建一个游戏
    function saveNewGame() {
        var newGameName=$('#new_game_name').val();
        var newGameIntroduction=$('#new_game_introduction').val();
        var newGameAbout=$('#new_game_about').val();
        var newGameKind=$('#new_game_kind option:selected').text();
        var newGamePrice=$('#new_game_price').val();
        var newGameDiscount=$('#new_game_discount').val();
        var newGameLowestCpu=$('#new_game_lowest_cpu').val();
        var newGameLowestOs=$('#new_game_lowest_os').val();
        var newGameLowestRam=$('#new_game_lowest_ram').val();
        var newGameLowestXianka=$('#new_game_lowest_xianka').val();
        var newGameLowestNetwork=$('#new_game_lowest_net').val();
        var newGameLowestDirectx=$('#new_game_lowest_directx').val();
        var newGameLowestRom=$('#new_game_lowest_rom').val();
        var newGameLowestShenka=$('#new_game_lowest_shenka').val();
        var newGameGoodCpu=$('#new_game_good_cpu').val();
        var newGameGoodOs=$('#new_game_good_os').val();
        var newGameGoodRam=$('#new_game_good_ram').val();
        var newGameGoodXianka=$('#new_game_good_xianka').val();
        var newGameGoodNetwork=$('#new_game_good_net').val();
        var newGameGoodDirectx=$('#new_game_good_directx').val();
        var newGameGoodRom=$('#new_game_good_rom').val();
        var newGameGoodShenka=$('#new_game_good_shenka').val();
        $.ajax({
            url:"/game/add",
            type:"POST",
            data:{
               newGameName:newGameName,
               newGameIntroduction:newGameIntroduction,
               newGameAbout:newGameAbout,
               newGameKind:newGameKind,
               newGamePrice:newGamePrice,
               newGameDiscount:newGameDiscount,
               newGameLowestCpu:newGameLowestCpu,
               newGameLowestOs:newGameLowestOs,
               newGameLowestRam:newGameLowestRam,
               newGameLowestXianka:newGameLowestXianka,
               newGameLowestNetwork:newGameLowestNetwork,
               newGameLowestDirectx:newGameLowestDirectx,
               newGameLowestRom:newGameLowestRom,
               newGameLowestShenka:newGameLowestShenka,
               newGameGoodCpu:newGameGoodCpu,
               newGameGoodOs:newGameGoodOs,
               newGameGoodRam:newGameGoodRam,
               newGameGoodXianka:newGameGoodXianka,
               newGameGoodNetwork:newGameGoodNetwork,
               newGameGoodDirectx:newGameGoodDirectx,
               newGameGoodRom:newGameGoodRom,
               newGameGoodShenka:newGameGoodShenka
            },
            success:function (data) {
                data=eval("("+data+")");
                layer.msg("增加成功");
                window.location.href="/admin/not-game-list"
            }
        })
    }

    //游戏列表下一页
    function loadNextPageGameList(statu) {
        if (statu=='issued'){
            var page=$('#game_list')[0].getAttribute("page");
            page=parseInt(page)+1;
            $('#game_list')[0].setAttribute("page",page);
            initGameList(page);
        } else {
            var page=$('#not_game_list')[0].getAttribute("page");
            page=parseInt(page)+1;
            $('#not_game_list')[0].setAttribute("page",page);
            initNotIssuedGameList(page);
        }

    }

    //游戏列表上一页
    function loadPrePageGameList(statu) {
        if (statu=='issued'){
            var page=$('#game_list')[0].getAttribute("page");
            page=parseInt(page)-1;
            $('#game_list')[0].setAttribute("page",page);
            initGameList(page);
        } else {
            var page=$('#not_game_list')[0].getAttribute("page");
            page=parseInt(page)-1;
            $('#not_game_list')[0].setAttribute("page",page);
            initNotIssuedGameList(page);
        }
    }

    function initNotIssuedGameList(page){
        $.ajax({
            url:"/upComing_index/"+page,
            async:false,
            success:function (data) {
                data=eval("("+data+")");
                $('#game_list_content').empty();
                for (var i=0;i<data.msg.length;i++){
                    var element='<tr class="text-c va-m">\n' +
                        '<td>'+data.msg[i].id+'</td>\n' +
                        '<td><img width="60" class="product-thumb" src="'+data.msg[i].posterImage+'"></a></td>\n' +
                        '<td class="text-l">'+data.msg[i].gameName+'</td>\n' +
                        '<td class="text-l">'+data.msg[i].gameIntroduction+'</td>\n' +
                        '<td class="text-l">'+data.msg[i].gameAbout+'</td>\n' +
                        '<td><span class="price">'+data.msg[i].gamePrice+'</span> 元</td>\n' +
                        '<td><span class="price">'+data.msg[i].discount+'</span> %</td>\n';
                    if (data.msg[i].issuedStatu==1){
                        element+='<td class="td-status"><span class="label label-success radius">已发布</span></td>\n';
                    } else {
                        element+='<td class="td-status"><span class="label label-default radius">未发布</span></td>\n';
                    }
                    var date=new Date(data.msg[i].issuedDate);
                    var year=date.getFullYear();
                    var month=date.getMonth()+1;
                    var day=date.getDate();
                    var showDate=year+'-'+month+'-'+day;
                    if (data.msg[i].issuedStatu==1) {
                        element+='<td class="text-l">'+showDate+'</td>';
                    }else {
                        element+='<td class="text-l">暂无</td>';
                    }
                    element+='<td class="td-manage">';
                    if (data.msg[i].issuedStatu==1){
                        element+='<a style="text-decoration:none" onClick="product_stop(this,'+data.msg[i].id+')" href="javascript:;" title="下架"><i class="Hui-iconfont">&#xe6de;</i></a> ';
                    } else {
                        element+='<a style="text-decoration:none" onClick="product_start(this,'+data.msg[i].id+')" href="javascript:;" title="发布"><i class="Hui-iconfont">&#xe6de;</i></a> ';
                    }
                    element+='<a style="text-decoration:none" class="ml-5" onClick="product_edit(\'产品编辑\',game-add.html'+data.msg[i].id+' href="javascript:;" title="编辑"><i class="Hui-iconfont">&#xe6df;</i></a>' ;
                    element+='<a  href="javascript:;" onclick="system_log_show(this,\'10001\')" class="ml-5" style="text-decoration:none" title="修改图片或者为该游戏新增图片介绍"><i class="Hui-iconfont"></i></a>';
                    element+='<a style="text-decoration:none" class="ml-5" onClick="product_del(this,'+data.msg[i].id+')" href="javascript:;" title="删除"><i class="Hui-iconfont">&#xe6e2;</i></a></td>\n' +
                        '</tr>';
                    $('#game_list_content').append(element);
                }
            }

        })
    }

    //加载已发布游戏列表
    function initGameList(page){
        $.ajax({
            url:"/newRelease_index/"+page,
            async:false,
            success:function (data) {
                data=eval("("+data+")");
                $('#game_list_content').empty();
                for (var i=0;i<data.msg.length;i++){
                    var element='<tr class="text-c va-m">\n' +
                        '<td>'+data.msg[i].id+'</td>\n' +
                        '<td><img width="60" class="product-thumb" src="'+data.msg[i].posterImage+'"></a></td>\n' +
                        '<td class="text-l">'+data.msg[i].gameName+'</td>\n' +
                        '<td class="text-l">'+data.msg[i].gameIntroduction+'</td>\n' +
                        '<td class="text-l">'+data.msg[i].gameAbout+'</td>\n' +
                        '<td><span class="price">'+data.msg[i].gamePrice+'</span> 元</td>\n' +
                        '<td><span class="price">'+data.msg[i].discount+'</span> %</td>\n';
                        if (data.msg[i].issuedStatu==1){
                            element+='<td class="td-status"><span class="label label-success radius">已发布</span></td>\n';
                        } else {
                            element+='<td class="td-status"><span class="label label-default radius">未发布</span></td>\n';
                        }
                        var date=new Date(data.msg[i].issuedDate);
                        var year=date.getFullYear();
                        var month=date.getMonth()+1;
                        var day=date.getDate();
                        var showDate=year+'-'+month+'-'+day;
                        if (data.msg[i].issuedStatu==1) {
                            element+='<td class="text-l">'+showDate+'</td>';
                        }else {
                            element+='<td class="text-l">暂无</td>';
                        }
                        element+='<td class="td-manage">';
                        if (data.msg[i].issuedStatu==1){
                            element+='<a style="text-decoration:none" onClick="product_stop(this,'+data.msg[i].id+')" href="javascript:;" title="下架"><i class="Hui-iconfont">&#xe6de;</i></a> ';
                        } else {
                            element+='<a style="text-decoration:none" onClick="product_start(this,'+data.msg[i].id+')" href="javascript:;" title="发布"><i class="Hui-iconfont">&#xe6de;</i></a> ';
                        }
                        element+='<a style="text-decoration:none" class="ml-5" onClick="product_edit(\'产品编辑\',game-add.html'+data.msg[i].id+' href="javascript:;" title="编辑"><i class="Hui-iconfont">&#xe6df;</i></a>' ;
                        element+='<a  href="javascript:;" onclick="system_log_show(this,\'10001\')" class="ml-5" style="text-decoration:none" title="修改图片或者为该游戏新增图片介绍"><i class="Hui-iconfont"></i></a>';
                        element+='<a style="text-decoration:none" class="ml-5" onClick="product_del(this,'+data.msg[i].id+')" href="javascript:;" title="删除"><i class="Hui-iconfont">&#xe6e2;</i></a></td>\n' +
                        '</tr>';
                        $('#game_list_content').append(element);
                }
            }

        })
    }

    //管理员登录
    function adminLogin() {
        var email=$('#email').val();
        var password=$('#password').val();
        password=""+salt.charAt(0)+salt.charAt(4)+password+salt.charAt(5)+salt.charAt(2);
        password=md5(password);
        var loadId=layer.load(1, {
            shade: [0.1,'#fff'] //0.1透明度的白色背景
        });
        $.ajax({
            url:"/admin/userVerification",
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
                if (data.code>500) {
                    layer.msg(data.msg)
                }

            },
            error:function () {
                layer.close(loadId);
                layer.msg("网络错误");
            }
        });
    }
    //上传游戏海报
    function uploadPosterImage() {
        var loadId=layer.load(1, {
            shade: [0.1,'#fff'] //0.1透明度的白色背景
        });
        var data = new FormData();
        var image=$("#file")[0].files[0];
        //console.log(image)
        data.append('file', image);
        //console.log(data)
        if (image.size>MAX_IMAGE_SIZE){
            layer.close(loadId);
            layer.msg("图片超过10M");
            return;
        }
        if (image.type.substring(0,5)!='image'){
            layer.close(loadId);
            layer.msg("不能上传非图片");
            return;
        }
       // console.log("allalal")
        $.ajax({
            url: '/file/upload',
            type: 'POST',
            async:false,//记住需要关闭异步处理
            data: data,
            cache: false,
            processData: false,
            contentType: false,
            success:function (data) {
                data=eval("("+data+")");
                layer.close(loadId);
                if (data.code==200){
                    $('#new_game_poster')[0].setAttribute("value",data.msg.url);
                    $('#new_poster_show')[0].setAttribute('src',data.msg.url);
                    $('#new_poster_show')[0].setAttribute('style','width: 50px;height: 50px;margin-left: -20px;display: block');
                    layer.msg("上传成功");
                }else {
                    layer.msg(data.msg);
                }
            }
        });
    }

    /*产品-添加*/
    function product_add(title,url){
        var index = layer.open({
            type: 2,
            title: title,
            content: url,
            area: '1000px',
        });
        layer.full(index);
    }
    /*产品-查看*/
    function product_show(title,url,id){
        var index = layer.open({
            type: 2,
            title: title,
            content: url
        });
        layer.full(index);
    }
    /*产品-审核*/
    function product_shenhe(obj,id){
        layer.confirm('审核文章？', {
                btn: ['通过','不通过'],
                shade: false
            },
            function(){
                $(obj).parents("tr").find(".td-manage").prepend('<a class="c-primary" onClick="product_start(this,id)" href="javascript:;" title="申请上线">申请上线</a>');
                $(obj).parents("tr").find(".td-status").html('<span class="label label-success radius">已发布</span>');
                $(obj).remove();
                layer.msg('已发布', {icon:6,time:1000});
            },
            function(){
                $(obj).parents("tr").find(".td-manage").prepend('<a class="c-primary" onClick="product_shenqing(this,id)" href="javascript:;" title="申请上线">申请上线</a>');
                $(obj).parents("tr").find(".td-status").html('<span class="label label-danger radius">未通过</span>');
                $(obj).remove();
                layer.msg('未通过', {icon:5,time:1000});
            });
    }
    /*产品-下架*/
    function product_stop(obj,id){
        layer.confirm('确认要下架吗？',function(index){
            $(obj).parents("tr").find(".td-manage").prepend('<a style="text-decoration:none" onClick="product_start(this,id)" href="javascript:;" title="发布"><i class="Hui-iconfont">&#xe603;</i></a>');
            $(obj).parents("tr").find(".td-status").html('<span class="label label-defaunt radius">已下架</span>');
            $(obj).remove();
            layer.msg('已下架!',{icon: 5,time:1000});
        });
    }

    /*产品-发布*/
    function product_start(obj,id){
        layer.confirm('确认要发布吗？',function(index){
            $(obj).parents("tr").find(".td-manage").prepend('<a style="text-decoration:none" onClick="product_stop(this,id)" href="javascript:;" title="下架"><i class="Hui-iconfont">&#xe6de;</i></a>');
            $(obj).parents("tr").find(".td-status").html('<span class="label label-success radius">已发布</span>');
            $(obj).remove();
            layer.msg('已发布!',{icon: 6,time:1000});
        });
    }

    /*产品-申请上线*/
    function product_shenqing(obj,id){
        $(obj).parents("tr").find(".td-status").html('<span class="label label-default radius">待审核</span>');
        $(obj).parents("tr").find(".td-manage").html("");
        layer.msg('已提交申请，耐心等待审核!', {icon: 1,time:2000});
    }

    /*产品-编辑*/
    function product_edit(title,url,id){
        var index = layer.open({
            type: 2,
            title: title,
            content: url
        });
        layer.full(index);
    }

    /*产品-删除*/
    function product_del(obj,id){
        layer.confirm('确认要删除吗？',function(index){
            $.ajax({
                type: 'POST',
                url: '/game/delete/'+id,
                success: function(){
                    $(obj).parents("tr").remove();
                    layer.msg('已删除!',{icon:1,time:1000});
                },
                error:function() {
                },
            });
        });
    }
