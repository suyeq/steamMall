var salt="1q2w3e";

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