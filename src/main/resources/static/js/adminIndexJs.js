$(function () {
    adminIndexJs.bindEvent();
});
var adminIndexJs = {
    bindEvent: function () {
        adminIndexJs.event.imageUpload();
        adminIndexJs.event.userList();
    },
    event: {
        imageUpload: function() {
            layui.use('upload', function () {
                var $ = layui.jquery
                    , upload = layui.upload;
                //普通图片上传
                upload.render({
                    elem: '#add-cover-btn'
                    , url: '/admin/image-upload-oss'
                    , accept: 'images'
                    , before: function () {
                        layer.load();
                    }
                    , done: function (res) {
                        layer.closeAll('loading');
                        $("#add-cover-img").attr('src', res.image_url);
                    }
                    , error: function (index, upload) {
                        layer.msg("错误");
                    }
                });
            });
        },
        userList: function () {
            layui.use('table', function () {
                var table = layui.table;
                $("#user-list").removeClass('layui-hide');
                $("#arena-list").addClass('layui-hide');
                $("#court-list").addClass('layui-hide');
                //第一个实例
                table.render({
                    elem: '#user-list-table'
                    , height: 485
                    , url: '/admin/user-list'
                    , page: true //开启order-list分页
                    , limits: [5, 10, 20]
                    , limit: 10
                    , cols: [[ //表头
                        {field: 'id', title: 'ID', width: 70, sort: true, fixed: 'left'}
                        , {field: 'user_name', title: '用户名', width: 250}
                        , {field: 'wechat_number', title: '微信号', width: 300}
                        , {
                            field: 'portrait', title: '头像', width: 200, templet: function (d) {
                                return '<div onclick="adminIndexJs.method.show_img(this)" ><img src="' + d.portrait + '" alt="" width="50px" height="50px"></a></div>';
                            }
                        }
                        , {field: 'create_time', title: '创建时间', width: 300, templet:'<div>{{ layui.util.toDateString(d.create_time, "yyyy-MM-dd HH:mm:ss") }}</div>', sort: true}
                    ]]
                });
            });
        }
    },
    method: {
        userSearch: function () {
            var data = {};
            data.user_name = $("#search-user-name").val();
            data.wechat_number = $("#search-wechat-number").val();
            layui.use('table', function () {
                var table = layui.table;
                //第一个实例
                table.render({
                    elem: '#user-list-table'
                    , height: 485
                    , where: {
                        user_name: $("#search-user-name").val(),
                        wechat_number: $("#search-wechat-number").val(),
                    }
                    , method: 'post'
                    , contentType: 'application/json'
                    , url: '/admin/user-search'
                    , page: true //开启分页
                    , limits: [5, 10, 20]
                    , limit: 10
                    , cols: [[ //表头
                        {field: 'id', title: 'ID', width: 70, sort: true, fixed: 'left'}
                        , {field: 'user_name', title: '用户名', width: 250}
                        , {field: 'wechat_number', title: '微信号', width: 300}
                        , {
                            field: 'portrait', title: '头像', width: 200, templet: function (d) {
                                return '<div onclick="adminIndexJs.method.show_img(this)" ><img src="' + d.portrait + '" alt="" width="50px" height="50px"></a></div>';
                            }
                        }
                        , {field: 'create_time', title: '创建时间', width: 300, templet:'<div>{{ layui.util.toDateString(d.create_time, "yyyy-MM-dd HH:mm:ss") }}</div>', sort: true}
                    ]]
                });
            });
        },
        arenaList: function () {
            layui.use('table', function () {
                var table = layui.table;
                $("#arena-list").removeClass('layui-hide');
                $("#user-list").addClass('layui-hide');
                $("#court-list").addClass('layui-hide');
                //第一个实例
                table.render({
                    elem: '#arena-list-table'
                    , height: 485
                    , url: '/admin/arena-list'
                    , page: true //开启分页
                    , limits: [5, 10, 20]
                    , limit: 10
                    , cols: [[ //表头
                        {field: 'id', title: 'ID', width: 70, sort: true, fixed: 'left'}
                        , {field: 'boss_mobile', title: 'BOSS手机号', width: 120}
                        , {field: 'recommend_str', title: '是否推荐', width: 100}
                        , {field: 'name', title: '球馆名称', width: 100}
                        , {field: 'address', title: '球馆地址', width: 100}
                        , {field: 'status_str', title: '状态', width: 100}
                        , {
                            field: 'cover_image', title: '封面图', width: 100, templet: function (d) {
                                return '<div onclick="adminIndexJs.method.show_img(this)" ><img src="' + d.cover_image + '" alt="" width="50px" height="50px"></a></div>';
                            }
                        }
                        , {field: 'start_time', title: '营业时间', width: 180, templet:'<div>{{ layui.util.toDateString(d.start_time, "yyyy-MM-dd HH:mm:ss") }}</div>', sort: true}
                        , {field: 'end_time', title: '闭管时间', width: 180, templet:'<div>{{ layui.util.toDateString(d.end_time, "yyyy-MM-dd HH:mm:ss") }}</div>', sort: true}
                        , {
                            field: 'operate',
                            title: '操作',
                            width: 147,
                            fixed: 'right',
                            toolbar: "#arena-list-table-operate"
                        }
                    ]]
                });
                table.on('tool(arena-list-table-fit)', function (obj) {
                    if (obj.event === 'del') {
                        layer.confirm('确定下架该场地？', function (index) {
                            $.ajax({
                                url: '/admin/arena-delete',
                                data: {
                                    arenaId: obj.data.id
                                },
                                type: 'get',
                                success: function (result) {
                                    layer.msg("下架成功");
                                    adminIndexJs.method.arenaList();
                                },
                                error: function () {
                                    layer.msg("数据请求异常");
                                    layer.closeAll()
                                }
                            })
                        })
                    }
                })
            });
        },
        courtList: function () {
            layui.use('table', function () {
                var table = layui.table;
                $("#court-list").removeClass('layui-hide');
                $("#arena-list").addClass('layui-hide');
                $("#user-list").addClass('layui-hide');
                //第一个实例
                table.render({
                    elem: '#court-list-table'
                    , height: 485
                    , url: '/admin/court-list'
                    , page: true //开启分页
                    , limits: [5, 10, 20]
                    , limit: 10
                    , cols: [[ //表头
                        {field: 'id', title: 'ID', width: 70, sort: true, fixed: 'left'}
                        , {field: 'user_name', title: '用户名', width: 120}
                        , {field: 'mobile', title: '手机号', width: 120}
                        , {field: 'real_name', title: '姓名', width: 90}
                        , {field: 'role_name', title: '角色', width: 130}
                        , {field: 'email', title: '邮箱', width: 150}
                        , {
                            field: 'portrait', title: '头像', width: 90, templet: function (d) {
                                return '<div onclick="adminIndexJs.method.show_img(this)" ><img src="' + d.portrait + '" alt="" width="50px" height="50px"></a></div>';
                            }
                        }
                        , {field: 'create_time', title: '创建时间', width: 180, templet:'<div>{{ layui.util.toDateString(d.create_time, "yyyy-MM-dd HH:mm:ss") }}</div>', sort: true}
                        , {field: 'update_time', title: '修改时间', width: 180, templet:'<div>{{ layui.util.toDateString(d.update_time, "yyyy-MM-dd HH:mm:ss") }}</div>', sort: true}
                        , {
                            field: 'operate',
                            title: '操作',
                            width: 80,
                            toolbar: "#admin-list-table-operate",
                        }
                    ]]
                });
                table.on('tool(admin-list-table-fit)', function (obj) {
                    if (obj.event === 'del') {
                        layer.confirm('确定删除该管理员？', function (index) {
                            var dataRequest = {};
                            dataRequest.mobile = obj.data.mobile;
                            $.ajax({
                                url: '/admin/admin-delete',
                                data: JSON.stringify(dataRequest),
                                contentType: 'application/json',
                                type: 'post',
                                success: function () {
                                    layer.msg("删除成功");
                                    adminIndexJs.method.adminList();
                                }
                            })
                        })
                    }
                })
            });
        },
        addArena: function () {
            layui.use('layer', function (layer) {
                layer.open({
                    type: 1,
                    title: '上架球馆',
                    shift: 7,
                    area: 'auto',
                    maxWidth: 1000,
                    maxHeight: 800,
                    shadeClose: true,
                    content: $("#add-arena-panel"),
                    end: function () {
                        $("#add-arena-panel").css("display", "none");
                    }
                });
            });
        },
        addArenaBtn: function () {
            layer.close(layer.index);
            var data = {};
            data.arena_name = $("#add-name").val();
            data.address = $("#add-address").val();
            data.cover_image = $("#add-cover-img").attr("src");
            data.boss_mobile = $("#add-boss-mobile").val();
            $.ajax({
                url: '/admin/arena-add',
                type: 'post',
                data: JSON.stringify(data),
                contentType: 'application/json',
                success: function (result) {
                    if (result.status_code == 200) {
                        layer.msg('添加球馆成功');
                        adminIndexJs.method.arenaList();
                    } else {
                        layer.msg('添加球馆失败');
                    }
                    layer.closeAll();
                },
                error: function () {
                    layer.msg('数据异常');
                    layer.closeAll();
                }
            })
        },
        show_img: function (t) {
            var t = $(t).find("img");
            //页面层
            layer.open({
                type: 1,
                title: '头像',
                skin: 'layui-layer-rim', //加上边框
                area: ['80%', '80%'], //宽高
                shadeClose: true, //开启遮罩关闭
                end: function (index, layero) {
                    return false;
                },
                content: '<div style="text-align:center"><img src="' + $(t).attr('src') + '" /></div>'
            });
        }

    }
}
