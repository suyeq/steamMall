    var salt="1q2w3e";

    //游戏列表下一页
    function loadNextPageGameList() {
        var page=$('#game_list')[0].getAttribute("page");
        page=parseInt(page)+1;
        $('#game_list')[0].setAttribute("page",page);
        initGameList(page);
    }

    //游戏列表上一页
    function loadPrePageGameList() {
        var page=$('#game_list')[0].getAttribute("page");
        page=parseInt(page)-1;
        if (page<=0){
            layer.msg("不能在往前");
            return;
        } else {
            $('#game_list')[0].setAttribute("page",page);
            initGameList(page);
        }
    }

    //加载游戏列表
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
                        element+='<td class="td-manage">';
                        if (data.msg[i].issuedstatu==1){
                            element+='<a style="text-decoration:none" onClick="product_stop(this,'+data.msg[i].id+')" href="javascript:;" title="下架"><i class="Hui-iconfont">&#xe6de;</i></a> ';
                        } else {
                            element+='<a style="text-decoration:none" onClick="product_start(this,'+data.msg[i].id+')" href="javascript:;" title="发布"><i class="Hui-iconfont">&#xe6de;</i></a> ';
                        }
                        element+='<a style="text-decoration:none" class="ml-5" onClick="product_edit(\'产品编辑\',game-add.html'+data.msg[i].id+' href="javascript:;" title="编辑"><i class="Hui-iconfont">&#xe6df;</i></a>' ;
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

    /*产品-添加*/
    function product_add(title,url){
        var index = layer.open({
            type: 2,
            title: title,
            content: url
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
                url: '',
                dataType: 'json',
                success: function(data){
                    $(obj).parents("tr").remove();
                    layer.msg('已删除!',{icon:1,time:1000});
                },
                error:function(data) {
                    console.log(data.msg);
                },
            });
        });
    }
