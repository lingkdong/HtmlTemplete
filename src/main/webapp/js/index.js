/**
 * Created with IntelliJ IDEA.
 * User: DT254
 * Date: 16-5-20
 * Time: 下午12:36
 * To change this template use File | Settings | File Templates.
 */
$(function(){
    $.ajax({
        type: "GET",
        url: "../document/index.csv",
        dataType: "text",
        success: function(data) {
            processData(data);
        }
    });
    $(".main-li-div-img").bind("click",function(){
        var url =$(this).attr("data-url");
        window.open(url,"_blank");
    });
})

function processData(allText) {
    var allTextLines = allText.split(/\r\n|\n/);
    var headers = allTextLines[0].split(',');
    var lines = [];

    for (var i=1; i<allTextLines.length; i++) {
        var data = allTextLines[i].split(',');
        if (data.length == headers.length) {
           var name=data[0];
           var url=data[1];
           var icon=data[2];
           var li=$('<li class="main-li"></li>');
           var div=$('<div class="main-li-div"></div>');
           var a=$('<a title="'+name+'" class="main-li-div-a" target="_blank" href="../Templetes/'+url+'">'+name+'</a>');
           var img=$(' <img class="main-li-div-img" src="../'+icon+'" data-url="../Templetes/'+url+'">')
           $('#list').append(li.append(div.append(a).append(img)));
        }else{
            console.log("data.length != headers.length  row number is "+i)
        }
    }
}
