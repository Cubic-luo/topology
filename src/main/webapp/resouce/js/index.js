$(function () {
    //    ajax访问服务器
    var result;
    $("#submit").click(function () {
        var start = "";
        var s=document.getElementsByName("start");
        for(var i=0;i<s.length;i++){
            if(s[i].checked){
                start += s[i].value+ ",";
            }
        }
        var jsonData = {
            nodeNum: $("input[name=nodeNum]").val(),
            topoName: $("#TopyType").find("option:selected").text(),
            outputType: $("#outputType").find("option:selected").text(),
            traversalStarts: start,
        }
        $.ajax({
            url: onlineUrl,
            data: jsonData,
            type: 'post',
            cache: false,
            dataType: 'json',
            traditional: true,
            success: function (data) {
                if (data != null) {
                    result = JSON.stringify(data);
                    $(".resultAre").show();
                    $("#result").JSONView(data);
                    alert("创建成功");
                }
            },
            error: function () {
                alert("出错了,请检查您的配置再重试")
            }
        });

    })
    //在线配置
    $("#online").click(function () {
        $(".onlineAre").show();
        $(".startAre").hide();
    })
    //上传配置文件
    $("#upload").click(function () {
        $("#form1").show();
        $("#style").hide();
    })

    $("#uploadButton").click(function () {
        $.ajaxFileUpload({
            url: uploadFileUrl,
            type: 'POST',
            fileElementId: 'file',
            dataType: 'json',
            cache: false,
            enctype: "multipart/form-data",
            traditional: true,
            success: function (data) {
                if (data != null) {
                    result = JSON.stringify(data)
                    $(".resultAre").show();
                    $("#result").JSONView(data);
                    alert("创建成功");
                }
            },
            error: function () {
                alert("出错了,请检查您的配置再重试")
            }
        });

    })

    //下载功能
    $("#download").click(function () {
        const data = result // 这里填CSV内容的字符串
        const blob = new Blob([data], {type: "text/plain"})
        const link = document.createElement("a")
        link.href = URL.createObjectURL(blob)
        link.download = "fiberhome.json" // 这里填保存成的文件名
        link.click()
        URL.revokeObjectURL(link.href)
    })
    // 根据输入的节点数量，动态创建可选的起点
    $("#nodeNum").change(function () {
        var num1 = $(this).val();
        var html = "";
        html += "<input type=checkbox checked=checked value='node0' name=start>" + "node0"
        for (i = 1; i < num1; i++) {
            html += "<input type=checkbox class=checkedStart  name=start value=node" + i + ">" + "node" + i + "</option>"
        }
        $("#start").html(html);
    })


})
$(function () {
    $(".fileLabel").click(function () {
        $(this).addClass("disabled");
    })
    $("#uploadButton").click(function () {
        $(".fileLabel").removeClass("disabled")

    })
})